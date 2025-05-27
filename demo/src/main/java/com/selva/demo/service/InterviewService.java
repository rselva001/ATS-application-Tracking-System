package com.selva.demo.service;

import com.selva.demo.entity.Interview;
import com.selva.demo.entity.Application;
import com.selva.demo.entity.User;
import com.selva.demo.repository.InterviewRepository;
import com.selva.demo.repository.ApplicationRepository;
import com.selva.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Create Interview
    public Interview createInterview(Interview interview) {
    	interview.setScheduledTime(LocalDateTime.now());
        return interviewRepository.save(interview);
    }

    // ✅ Get all interviews
    public List<Interview> getAllInterviews() {
        return interviewRepository.findAll();
    }

    // ✅ Get interview by ID
    public Optional<Interview> getInterviewById(Long id) {
        return interviewRepository.findById(id);
    }

    // ✅ Update interview
    public Interview updateInterview(Long id, Interview updatedInterview) {
        Optional<Interview> opt = interviewRepository.findById(id);
        if (opt.isPresent()) {
            Interview existing = opt.get();
            existing.setScheduledTime(updatedInterview.getScheduledTime());
            existing.setApplication(updatedInterview.getApplication());
            existing.setInterviewer(updatedInterview.getInterviewer());
            return interviewRepository.save(existing);
        }
        return null;
    }

    // ✅ Delete interview
    public boolean deleteInterview(Long id) {
        if (interviewRepository.existsById(id)) {
            interviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ✅ Get all interviews for a specific Application
    public List<Interview> getInterviewsByApplicationId(Long applicationId) {
        return interviewRepository.findByApplicationId(applicationId);
    }

    // ✅ Get all interviews assigned to a specific Interviewer (User)
    public List<Interview> getInterviewsByInterviewerId(Long interviewerId) {
        return interviewRepository.findByInterviewerId(interviewerId);
    }
}
