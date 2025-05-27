package com.selva.demo.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "interviews")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime scheduledTime;

    @ManyToOne
    @JoinColumn(name = "application_id")
    @JsonBackReference(value = "application-interviews")
    private Application application;

    @ManyToOne
    @JoinColumn(name = "User_id")
//    @JsonBackReference(value = "user-interviews")
    private User interviewer;

    @OneToOne(mappedBy = "interview", cascade = CascadeType.ALL)
    private Feedback feedback;

	public Interview() {
		super();
	}

	public Interview(Long id, LocalDateTime scheduledTime, Application application, User interviewer,
			Feedback feedback) {
		super();
		this.id = id;
		this.scheduledTime = scheduledTime;
		this.application = application;
		this.interviewer = interviewer;
		this.feedback = feedback;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(LocalDateTime scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public User getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(User interviewer) {
		this.interviewer = interviewer;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
    
    
    
}


