package com.example.userservice.cron;

import com.example.shared.model.Subscription;
import com.example.userservice.repository.SubscriptionRepository;
import com.example.userservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DailyNotificationJob {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionService subscriptionService;

    @Scheduled(cron = "${jobs.daily-notifications.cron}")
    public void sendDailyNotifications() {
        LocalDate today = LocalDate.now();
        List<Subscription> subscriptions = subscriptionRepository.findByLastNotificationDateBeforeOrLastNotificationDateIsNull(today);
        subscriptions
                .forEach(subscription -> {
                    subscriptionService.publishSubscriptionEvent(subscription);
                    subscription.setLastNotificationDate(today);
                    subscriptionRepository.save(subscription);
                });
    }
}
