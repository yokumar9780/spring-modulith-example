package com.example.userservice.repository;

import com.example.shared.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);

    // Find subscriptions where notifications haven't been sent today
    List<Subscription> findByLastNotificationDateBeforeOrLastNotificationDateIsNull(LocalDate date);
}
