package com.selva.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selva.demo.entity.Job;
import com.selva.demo.entity.JobStatus;
import com.selva.demo.entity.RoleStatus;

public interface JobRepository extends JpaRepository<Job, Long>{

	
	 List<Job> findByStatus(JobStatus status);
}
