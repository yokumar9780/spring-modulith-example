package com.example.shared.event;

import com.example.shared.model.Subscription;
import lombok.Builder;


@Builder
public record SubscriptionJobEvent(Subscription subscription) {
}