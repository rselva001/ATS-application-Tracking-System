package com.selva.demo.service;

	import com.selva.demo.entity.User;
	import com.selva.demo.repository.UserRepository;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

	import java.util.List;
	import java.util.Optional;

	@Service
	public class UserService {

	    @Autowired
	    private UserRepository userRepository;

	    // CREATE
	    public User createUser(User user) {
	        return userRepository.save(user);
	    }

	    // READ - All users
	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }

	    // READ - By ID
	    public Optional<User> getUserById(Long id) {
	        return userRepository.findById(id);
	    }

	    // READ - By Email
	    public Optional<User> getUserByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }

	    //update
	    public User updateUser(Long id, User newUser) {
	        User existingUser = userRepository.findById(id).orElse(null);
	        if (existingUser != null) {
	            existingUser.setName(newUser.getName());
	            existingUser.setEmail(newUser.getEmail());
	            existingUser.setPhoneNumber(newUser.getPhoneNumber());
	            existingUser.setPassword(newUser.getPassword());
	            existingUser.setRole(newUser.getRole());
	            // interviews should not be reset unless explicitly intended
	            return userRepository.save(existingUser);
	        }
	        return null;
	    }

	    // DELETE
	    public void deleteUser(Long id) {
	        if (!userRepository.existsById(id)) {
	            throw new RuntimeException("User not found with id " + id);
	        }
	        userRepository.deleteById(id);
	    }
	}



