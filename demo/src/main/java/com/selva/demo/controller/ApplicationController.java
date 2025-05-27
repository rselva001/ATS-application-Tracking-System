package com.selva.demo.controller;

import com.selva.demo.entity.Application;
import com.selva.demo.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // ✅ CREATE a new application
    @PostMapping
    public ResponseEntity<Application> createApplication(@RequestBody Application application) {
        Application created = applicationService.createApplication(application);
        return ResponseEntity.ok(created);
    }

    // ✅ READ all applications
    @GetMapping
    public ResponseEntity<List<Application>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    // ✅ READ application by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getApplicationById(@PathVariable Long id) {
        Optional<Application> application = applicationService.getApplicationById(id);
        return application.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Application not found"));
    }

    // ✅ UPDATE application
    @PutMapping("/{id}")
    public ResponseEntity<?> updateApplication(@PathVariable Long id, @RequestBody Application application) {
        Application updated = applicationService.updateApplication(id, application);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(404).body("Application not found for update");
    }

    // ✅ DELETE application
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long id) {
        boolean deleted = applicationService.deleteApplication(id);
        if (deleted) {
            return ResponseEntity.ok("Application deleted successfully");
        }
        return ResponseEntity.status(404).body("Application not found");
    }

    // ✅ Get applications by Job ID
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Application>> getApplicationsByJobId(@PathVariable Long jobId) {
        List<Application> applications = applicationService.getApplicationsByJobId(jobId);
        return ResponseEntity.ok(applications);
    }

    // ✅ Get applications by Candidate ID
    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<Application>> getApplicationsByCandidateId(@PathVariable Long candidateId) {
        List<Application> applications = applicationService.getApplicationsByCandidateId(candidateId);
        return ResponseEntity.ok(applications);
    }
}
