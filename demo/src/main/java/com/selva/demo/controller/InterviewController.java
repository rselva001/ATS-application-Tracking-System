package com.selva.demo.controller;

import com.selva.demo.entity.Interview;
import com.selva.demo.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    // ✅ Create interview
    @PostMapping
    public ResponseEntity<Interview> createInterview(@RequestBody Interview interview) {
        Interview created = interviewService.createInterview(interview);
        return ResponseEntity.ok(created);
    }

    // ✅ Get all interviews
    @GetMapping
    public ResponseEntity<List<Interview>> getAllInterviews() {
        return ResponseEntity.ok(interviewService.getAllInterviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInterviewById(@PathVariable Long id) {
        Optional<Interview> interview = interviewService.getInterviewById(id);
        return interview.<ResponseEntity<?>>map(ResponseEntity::ok)
               .orElse(ResponseEntity.status(404).body("Interview ID not found!!"));
    }


    // ✅ Update interview
    @PutMapping("/{id}")
    public ResponseEntity<Interview> updateInterview(@PathVariable Long id, @RequestBody Interview interview) {
        Interview updated = interviewService.updateInterview(id, interview);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // ✅ Delete interview
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInterview(@PathVariable Long id) {
        boolean deleted = interviewService.deleteInterview(id);
        if (deleted) {
            return ResponseEntity.ok("Interview deleted successfully");
        }
        return ResponseEntity.ok("id not found!!!!");
    }

    // ✅ Get interviews by Application ID
    @GetMapping("/application/{applicationId}")
    public ResponseEntity<List<Interview>> getInterviewsByApplicationId(@PathVariable Long applicationId) {
        List<Interview> interviews = interviewService.getInterviewsByApplicationId(applicationId);
        return ResponseEntity.ok(interviews);
    }

    // ✅ Get interviews by Interviewer ID
    @GetMapping("/interviewer/{interviewerId}")
    public ResponseEntity<List<Interview>> getInterviewsByInterviewerId(@PathVariable Long interviewerId) {
        List<Interview> interviews = interviewService.getInterviewsByInterviewerId(interviewerId);
        return ResponseEntity.ok(interviews);
    }
}
