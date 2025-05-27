package com.selva.demo.service;

import com.selva.demo.entity.Candidate;
import com.selva.demo.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    // ✅ CREATE a new candidate
    public Candidate createCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    // ✅ READ all candidates
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    // ✅ READ candidate by ID
    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    // ✅ UPDATE candidate
    public Candidate updateCandidate(Long id, Candidate updatedCandidate) {
        Optional<Candidate> existingOpt = candidateRepository.findById(id);
        if (existingOpt.isPresent()) {
            Candidate existing = existingOpt.get();
            existing.setName(updatedCandidate.getName());
            existing.setEmail(updatedCandidate.getEmail());
            existing.setPhone(updatedCandidate.getPhone());
            existing.setResumeUrl(updatedCandidate.getResumeUrl());
            return candidateRepository.save(existing);
        }
        return null;
    }

    // ✅ DELETE candidate
    public boolean deleteCandidate(Long id) {
        if (candidateRepository.existsById(id)) {
            candidateRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ✅ Optional: Get candidate by email
    public Optional<Candidate> getCandidateByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }
}
