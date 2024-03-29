package com.fdmgroup.RachelPlacementsTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.RachelPlacementsTracker.coreModel.AccountManager;
import com.fdmgroup.RachelPlacementsTracker.coreModel.Trainee;
import com.fdmgroup.RachelPlacementsTracker.dal.AMRepository;
import com.fdmgroup.RachelPlacementsTracker.dal.TraineeRepository;
import com.fdmgroup.RachelPlacementsTracker.dal.UserRepository;
import com.fdmgroup.RachelPlacementsTracker.exceptions.NotFoundException;
import com.fdmgroup.RachelPlacementsTracker.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
	private UserRepository userRepository;
	private TraineeRepository traineeRepository;
	private AMRepository aMRepository;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, TraineeRepository traineeRepository, AMRepository aMRepository,
			PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.traineeRepository = traineeRepository;
		this.aMRepository = aMRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	public User findById(int userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("User with ID: " + userId + " cannot be found"));
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
		} else {
			throw new NotFoundException("User with ID: " + newUser.getId() + " cannot be found");
		}

	}

	public void deleteById(int userId) {
		if (this.userRepository.existsById(userId)) {
			this.userRepository.deleteById(userId);
		} else {
			throw new NotFoundException("User with ID: " + userId + " cannot be found");
		}

	}

	public void register(User user) {
		// Hash(encode) the password
		String hashedPAssword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPAssword);
		if(user.getRole().equals("trainee")) {
			this.saveTrainee(user);
		}else {
			this.saveAccountManager(user);
		}
	}

}
