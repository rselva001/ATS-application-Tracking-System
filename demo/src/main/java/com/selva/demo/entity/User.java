package com.selva.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    @Column(unique = true)
	    private String email;

	    
	    private String phoneNumber;
	    private String password;
	    @Enumerated(EnumType.STRING)
	    private RoleStatus role;  // Admin, Recruiter, Hiring Manager, Interviewer

	    // Relations
	    @OneToMany(mappedBy = "interviewer")
	    @JsonIgnore
	    private List<Interview> interviews;

		public User() {
			super();
		}

		public User(Long id, String name, String email, String phoneNumber, String password, RoleStatus role,
				List<Interview> interviews) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.phoneNumber = phoneNumber;
			this.password = password;
			this.role = role;
			this.interviews = interviews;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public RoleStatus getRole() {
			return role;
		}

		public void setRole(RoleStatus role) {
			this.role = role;
		}

		public List<Interview> getInterviews() {
			return interviews;
		}

		public void setInterviews(List<Interview> interviews) {
			this.interviews = interviews;
		}
	    
	    
	    
	    
	    
	}


