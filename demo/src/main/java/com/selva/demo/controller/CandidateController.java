package com.selva.demo.controller;

import com.selva.demo.entity.Candidate;
import com.selva.demo.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    // ✅ Create a new candidate
    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) {
        return ResponseEntity.ok(candidateService.createCandidate(candidate));
    }

    // ✅ Get all candidates
    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    // ✅ Get candidate by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCandidateById(@PathVariable Long id) {
        Optional<Candidate> candidate = candidateService.getCandidateById(id);
        return candidate.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Candidate not found with ID: " + id));
    }

    // ✅ Update candidate
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
        Candidate updated = candidateService.updateCandidate(id, candidate);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(404).body("Candidate not found for update with ID: " + id);
        }
    }

    // ✅ Delete candidate
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long id) {
        boolean deleted = candidateService.deleteCandidate(id);
        if (deleted) {
            return ResponseEntity.ok("Candidate deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Candidate not found with ID: " + id);
        }
    }

    // ✅ Get candidate by email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getCandidateByEmail(@PathVariable String email) {
        Optional<Candidate> candidate = candidateService.getCandidateByEmail(email);
        return candidate.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Candidate not found with email: " + email));
    }
}
