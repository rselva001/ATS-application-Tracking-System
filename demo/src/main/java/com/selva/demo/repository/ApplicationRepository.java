package com.selva.demo.repository;

import java.util.List;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selva.demo.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application , Long> {
	
	  List<Application> findByJobId(Long jobId);
	    List<Application> findByCandidateId(Long candidateId);

}
