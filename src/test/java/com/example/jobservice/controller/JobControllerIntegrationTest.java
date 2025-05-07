package com.example.jobservice.controller;


import com.example.jobservice.repository.JobRepository;
import com.example.shared.model.Job;
import com.example.spring_modulith.CustomTestcontainersConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(CustomTestcontainersConfiguration.class)
public class JobControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        jobRepository.deleteAll();
    }

    @Test
    void testCreateJob() throws Exception {
        Job job = new Job();
        job.setTitle("Software Engineer");
        job.setDescription("Develop and maintain software applications.");
        job.setLocation("New York");
        job.setCompanyName("TechCorp");
        job.setSalary(120000.0);

        mockMvc.perform(post("/api/jobs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(job)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Software Engineer"))
                .andDo(print());
    }

    @Test
    void testGetAllJobs() throws Exception {
        Job job = new Job();
        job.setTitle("Software Engineer");
        job.setDescription("Develop and maintain software applications.");
        job.setLocation("New York");
        job.setCompanyName("TechCorp");
        job.setSalary(120000.0);
        jobRepository.save(job);

        mockMvc.perform(get("/api/jobs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Software Engineer"))
                .andDo(print());
    }

    @Test
    void testUpdateJob() throws Exception {
        Job job = new Job();
        job.setTitle("Software Engineer");
        job.setDescription("Develop and maintain software applications.");
        job.setLocation("New York");
        job.setCompanyName("TechCorp");
        job.setSalary(120000.0);
        job = jobRepository.save(job);

        job.setTitle("Senior Software Engineer");

        mockMvc.perform(put("/api/jobs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(job)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Senior Software Engineer"))
                .andDo(print());
    }

    @Test
    void testDeleteJob() throws Exception {
        Job job = new Job();
        job.setTitle("Software Engineer");
        job.setDescription("Develop and maintain software applications.");
        job.setLocation("New York");
        job.setCompanyName("TechCorp");
        job.setSalary(120000.0);
        job = jobRepository.save(job);

        mockMvc.perform(delete("/api/jobs/" + job.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
