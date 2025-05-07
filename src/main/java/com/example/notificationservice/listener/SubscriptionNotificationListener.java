package com.example.notificationservice.listener;

import com.example.notificationservice.service.NotificationService;
import com.example.shared.event.SubscriptionNotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionNotificationListener {

    private final NotificationService notificationService;

    @EventListener
    public void handleSubscriptionNotificationEvent(SubscriptionNotificationEvent event) {
        // Send email and SMS notifications
        notificationService.sendEmail(event.subscription());
        notificationService.sendSms(event.subscription());
    }
}
