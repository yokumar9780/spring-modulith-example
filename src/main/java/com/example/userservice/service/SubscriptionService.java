package com.example.userservice.service;

import com.example.shared.event.SubscriptionJobEvent;
import com.example.shared.model.Subscription;
import com.example.userservice.repository.SubscriptionRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final ApplicationEventPublisher eventPublisher;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, ApplicationEventPublisher eventPublisher) {
        this.subscriptionRepository = subscriptionRepository;
        this.eventPublisher = eventPublisher;
    }

    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }

    public void publishSubscriptionEvent(Subscription subscription) {
        eventPublisher.publishEvent(new SubscriptionJobEvent(subscription));
    }
}
