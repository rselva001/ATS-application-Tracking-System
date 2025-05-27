package com.selva.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status; // applied, interviewed, offered, hired

    @ManyToOne
    @JoinColumn(name = "job_id")
    @JsonBackReference(value = "job-application")  // must match the managed side's value
    private Job job;  // NOT Application!


    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @JsonBackReference(value = "candidate-application")  // on single Candidate field
    private Candidate candidate;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "application-interviews")
    private List<Interview> interviews;
   
	public Application() {
		super();
	}
	public Application(Long id, String status, Job job, Candidate candidate, List<Interview> interviews) {
		super();
		this.id = id;
		this.status = status;
		this.job = job;
		this.candidate = candidate;
		this.interviews = interviews;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public List<Interview> getInterviews() {
		return interviews;
	}
	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}
    
	
    
}
