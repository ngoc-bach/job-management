package com.fdmgroup.RachelPlacementsTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.RachelPlacementsTracker.coreModel.AccountManager;
import com.fdmgroup.RachelPlacementsTracker.coreModel.Trainee;
import com.fdmgroup.RachelPlacementsTracker.dal.AMRepository;
import com.fdmgroup.RachelPlacementsTracker.dal.TraineeRepository;
import com.fdmgroup.RachelPlacementsTracker.dal.UserRepository;
import com.fdmgroup.RachelPlacementsTracker.model.User;

@Service
public class UserService {
	private UserRepository userRepository;
	private TraineeRepository traineeRepository;
	private AMRepository aMRepository;

	@Autowired
	public UserService(UserRepository userRepository, TraineeRepository traineeRepository, AMRepository aMRepository) {
		super();
		this.userRepository = userRepository;
		this.traineeRepository = traineeRepository;
		this.aMRepository = aMRepository;
	}

	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	public User findById(int userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User with " + userId + " cannot be found"));
	}
	
	public Trainee findTraineeByUserId(int userId) { 
		// TODO: Get trainee by user id
		return null;
	}

	public void saveTrainee(User newUser) {
		Trainee newTrainee = new Trainee();
		newTrainee.setFirstName(newUser.getFirstName());
		newTrainee.setLastName(newUser.getLastName());
		newTrainee.setEmail(newUser.getEmail());
		newTrainee.setLocation(newUser.getLocation());
		newTrainee.setUser(newUser);
		this.traineeRepository.save(newTrainee);
		this.userRepository.save(newUser);
	}
	
	public void saveAccountManager(User newUser) {
		AccountManager newAccountManager = new AccountManager(); 
		newAccountManager.setFirstName(newUser.getFirstName());
		newAccountManager.setLastName(newUser.getLastName());
		newAccountManager.setEmail(newUser.getEmail());
		newAccountManager.setLocation(newUser.getLocation());
		newAccountManager.setUser(newUser);
		this.aMRepository.save(newAccountManager);
		this.userRepository.save(newUser);
	}

	public void update(User newUser) {
		if (this.userRepository.existsById(newUser.getId())) {
			this.userRepository.save(newUser);
		}
		throw new RuntimeException("Invalid ID: " + newUser.getId());
	}

	public void deleteById(int userId) {
		if (this.userRepository.existsById(userId)) {
			this.userRepository.deleteById(userId);
		}
		throw new RuntimeException("Invalid ID: " + userId);
	}



}
