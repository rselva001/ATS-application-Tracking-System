package com.selva.demo.controller;

import com.selva.demo.entity.Job;
import com.selva.demo.entity.JobStatus;
import com.selva.demo.repository.JobRepository;
import com.selva.demo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;
    @Autowired 
    public JobRepository jobRepository;

    // ✅ CREATE a new job
    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job createdJob = jobService.createJob(job);
        return ResponseEntity.ok(createdJob);
    }

    // ✅ GET all jobs
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    // ✅ GET job by ID
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Optional<Job> jobOpt = jobService.getJobById(id);
        return jobOpt.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    // ✅ UPDATE job
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        Job job = jobService.updateJob(id, updatedJob);
        if (job != null) {
            return ResponseEntity.ok(job);
        }
        return ResponseEntity.notFound().build();
    }

    // ✅ DELETE job
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        if (jobService.deleteJob(id)) {
            return ResponseEntity.ok("Job deleted successfully");
        } else {
            return ResponseEntity.ok("job id not found");
        }
    }
    // Get jobs by status (e.g., OPEN, CLOSED)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Job>> getJobsByStatus(@PathVariable JobStatus status) {
        List<Job> jobs = jobService.getJobsByStatus(status);
        return ResponseEntity.ok(jobs);
    }
}
