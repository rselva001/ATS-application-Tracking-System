package com.selva.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selva.demo.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	  Optional<Candidate> findByEmail(String email); 

}
