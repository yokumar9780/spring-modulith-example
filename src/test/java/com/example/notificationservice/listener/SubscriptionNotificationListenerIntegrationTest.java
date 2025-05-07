package com.example.notificationservice.listener;

import com.example.notificationservice.service.NotificationService;
import com.example.shared.event.SubscriptionNotificationEvent;
import com.example.shared.model.Subscription;
import com.example.spring_modulith.CustomTestcontainersConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static org.mockito.Mockito.*;

@SpringBootTest
@Import(CustomTestcontainersConfiguration.class)
public class SubscriptionNotificationListenerIntegrationTest {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @MockitoSpyBean
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        reset(notificationService); // Reset interactions with the spy before each test
    }

    @Test
    void testHandleSubscriptionNotificationEvent() {
        // Arrange
        Subscription subscription = Subscription.builder()
                .id(1L)
                .userId(1L)
                .jobType("Software Engineer")
                .location("New York")
                .minSalary(100000.0)
                .lastNotificationDate(null)
                .build();
        SubscriptionNotificationEvent event = new SubscriptionNotificationEvent(subscription);

        // Act
        eventPublisher.publishEvent(event);

        // Assert
        verify(notificationService, times(1)).sendEmail(subscription);
        verify(notificationService, times(1)).sendSms(subscription);
    }
}
