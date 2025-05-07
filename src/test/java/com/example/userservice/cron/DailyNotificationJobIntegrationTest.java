package com.example.userservice.cron;

import com.example.shared.model.Subscription;
import com.example.spring_modulith.CustomTestcontainersConfiguration;
import com.example.userservice.repository.SubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@EnableScheduling
@Import(CustomTestcontainersConfiguration.class)
public class DailyNotificationJobIntegrationTest {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private DailyNotificationJob dailyNotificationJob;

    @BeforeEach
    void setUp() {
        subscriptionRepository.deleteAll();
    }

    @Test
    void testSendDailyNotifications() {
        // Arrange
        Subscription subscription1 = Subscription.builder()
                .userId(1L)
                .jobType("Software Engineer")
                .location("New York")
                .minSalary(100000.0)
                .lastNotificationDate(LocalDate.now().minusDays(1))
                .build();

        Subscription subscription2 = Subscription.builder()
                .userId(2L)
                .jobType("Data Scientist")
                .location("San Francisco")
                .minSalary(120000.0)
                .lastNotificationDate(null)
                .build();

        subscriptionRepository.saveAll(List.of(subscription1, subscription2));

        // Act
        dailyNotificationJob.sendDailyNotifications();

        // Assert
        List<Subscription> updatedSubscriptions = subscriptionRepository.findAll();
        assertThat(updatedSubscriptions).allMatch(subscription -> subscription.getLastNotificationDate().equals(LocalDate.now()));
    }
}
