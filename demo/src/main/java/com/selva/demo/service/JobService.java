package com.selva.demo.service;

import com.selva.demo.entity.Job;
import com.selva.demo.entity.JobStatus;
import com.selva.demo.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service class that handles business logic for Job entity.
 */
@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    /**
     * Create a new job posting.
     * Automatically sets the current timestamp as postedAt.
     */
    public Job createJob(Job job) {
        job.setPostedAt(LocalDateTime.now());
        return jobRepository.save(job);
    }

    /**
     * Get all job postings.
     */
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    /**
     * Get a job by its ID.
     */
    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    /**
     * Update a job if it exists.
     */
    public Job updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOpt = jobRepository.findById(id);
        if (jobOpt.isPresent()) {
            Job existingJob = jobOpt.get();
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setDepartment(updatedJob.getDepartment());
            existingJob.setLocation(updatedJob.getLocation());
            existingJob.setStatus(updatedJob.getStatus());
            // postedAt is usually not updated unless reposted
            return jobRepository.save(existingJob);
        }
        return null;
    }

    /**
     * Delete a job by ID if it exists.
     */
    public boolean deleteJob(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Get jobs by status (optional extra filtering method).
     */
    public List<Job> getJobsByStatus(JobStatus status) {
        return jobRepository.findByStatus(status);
    }}
