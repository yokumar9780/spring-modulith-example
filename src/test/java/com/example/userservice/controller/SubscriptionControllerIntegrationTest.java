package com.example.userservice.controller;

import com.example.shared.model.Subscription;
import com.example.spring_modulith.CustomTestcontainersConfiguration;
import com.example.userservice.repository.SubscriptionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@Import(CustomTestcontainersConfiguration.class)
public class SubscriptionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        subscriptionRepository.deleteAll();
    }

    @Test
    void testCreateSubscription() throws Exception {
        Subscription subscription = Subscription.builder()
                .userId(1L)
                .jobType("Software Engineer")
                .location("New York")
                .minSalary(100000.0)
                .lastNotificationDate(LocalDate.now().minusDays(1))
                .build();

        mockMvc.perform(post("/api/subscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subscription)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.jobType").value("Software Engineer"))
                .andDo(print());
    }

    @Test
    void testGetSubscriptionsByUserId() throws Exception {
        Subscription subscription = Subscription.builder()
                .userId(1L)
                .jobType("Software Engineer")
                .location("New York")
                .minSalary(100000.0)
                .lastNotificationDate(LocalDate.now().minusDays(1))
                .build();
        subscriptionRepository.save(subscription);

        mockMvc.perform(get("/api/subscriptions/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(1L))
                .andExpect(jsonPath("$[0].jobType").value("Software Engineer"))
                .andDo(print());
    }

    @Test
    void testDeleteSubscription() throws Exception {
        Subscription subscription = Subscription.builder()
                .userId(1L)
                .jobType("Software Engineer")
                .location("New York")
                .minSalary(100000.0)
                .lastNotificationDate(LocalDate.now().minusDays(1))
                .build();
        subscription = subscriptionRepository.save(subscription);

        mockMvc.perform(delete("/api/subscriptions/" + subscription.getId()))
                .andExpect(status().isOk())
                .andDo(print());

        List<Subscription> subscriptions = subscriptionRepository.findAll();
        assert subscriptions.isEmpty();
    }
}
