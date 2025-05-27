package com.selva.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selva.demo.entity.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long>{

	 List<Interview> findByApplicationId(Long applicationId);
	    List<Interview> findByInterviewerId(Long interviewerId);
}
