package com.example.jobservice.repository;

import com.example.shared.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
    // Additional query methods can be added here
}
