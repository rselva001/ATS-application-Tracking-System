package com.selva.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selva.demo.entity.Feedback;

public interface FeedbackRepository extends JpaRepository< Feedback, Long>{
	 Optional<Feedback> findByInterviewId(Long interviewId);
	 

}
