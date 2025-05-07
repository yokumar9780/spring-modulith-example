package com.example.notificationservice.service;

import com.example.shared.model.Subscription;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    public void sendEmail(Subscription subscription) {
        // Logic to send email notification
        log.info("Sending email to user {} for job type:{}, location: {}", subscription.getUserId()
                , subscription.getJobType(), subscription.getLocation());
    }

    public void sendSms(Subscription subscription) {
        // Logic to send SMS notification
        log.info("Sending SMS to user {} for job type: {}, location: {}",
                subscription.getUserId(), subscription.getJobType(), subscription.getLocation());
    }
}
