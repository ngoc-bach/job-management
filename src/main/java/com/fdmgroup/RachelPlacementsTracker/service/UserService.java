package com.fdmgroup.RachelPlacementsTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.RachelPlacementsTracker.dal.UserRepository;
import com.fdmgroup.RachelPlacementsTracker.model.User;

@Service
public class UserService {
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public List<User> findAll(){
		return this.userRepository.findAll();
	}

	public User findById(int userId) {
		return this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with " + userId + " cannot be found"));
	}

	public void save(User newUser) {
		this.userRepository.save(newUser);
	}

	public void update(User newUser) {
		if(this.userRepository.existsById(newUser.getId())) {
			this.userRepository.save(newUser);
		}
		throw new RuntimeException("Invalid ID: " + newUser.getId());
	}

	public void deleteById(int userId) {
		if(this.userRepository.existsById(userId)) {
			this.userRepository.deleteById(userId);
		}
		throw new RuntimeException("Invalid ID: " + userId);
	}
	
	
}
