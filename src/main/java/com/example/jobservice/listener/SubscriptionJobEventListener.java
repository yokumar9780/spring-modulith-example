package com.example.jobservice.listener;

import com.example.jobservice.repository.JobRepository;
import com.example.shared.event.SubscriptionJobEvent;
import com.example.shared.model.Job;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SubscriptionJobEventListener {

    private final JobRepository jobRepository;

    @ApplicationModuleListener
    public void handleSubscriptionJobEvent(SubscriptionJobEvent event) {
        // Get subscription criteria
        var subscription = event.subscription();

        // Filter jobs based on subscription criteria
        List<Job> matchingJobs = jobRepository.findAll().stream()
                .filter(job -> job.getJobType().equalsIgnoreCase(subscription.getJobType()))
                .filter(job -> job.getLocation().equalsIgnoreCase(subscription.getLocation()))
                .filter(job -> job.getSalary() >= subscription.getMinSalary())
                .toList();

        // Send matching jobs to notification service
        log.info("Matching jobs for subscription: {}", subscription.getId());
        matchingJobs
                .forEach(job -> log.info("Job: {}", job.getTitle()));
    }
}
