package com.fdmgroup.RachelPlacementsTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.RachelPlacementsTracker.coreModel.Trainee;
import com.fdmgroup.RachelPlacementsTracker.dal.TraineeRepository;
import com.fdmgroup.RachelPlacementsTracker.exceptions.NotFoundException;

@Service
public class TraineeService {
	private TraineeRepository traineeRepository;

	@Autowired
	public TraineeService(TraineeRepository traineeRepository) {
		super();
		this.traineeRepository = traineeRepository;
	}

	public List<Trainee> findAll() {
		return this.traineeRepository.findAll();
	}

	public Trainee findById(int traineeId) {
		return this.traineeRepository.findById(traineeId)
				.orElseThrow(() -> new NotFoundException("Trainee with ID: " + traineeId + " cannot be found"));
	}

	public void createNewTrainee(Trainee newTrainee) {
		this.traineeRepository.save(newTrainee);
	}

	public void updateTrainee(Trainee newTrainee) {
		if (this.traineeRepository.existsById(newTrainee.getId())) {
			this.traineeRepository.save(newTrainee);
			return;
		}
		throw new NotFoundException("Invalid ID: " + newTrainee.getId());

	}

	public void deleteTrainee(int traineeId) {
		if (this.traineeRepository.existsById(traineeId)) {
			this.traineeRepository.deleteById(traineeId);
			return;
		}
		throw new NotFoundException("Invalid ID: " + traineeId);

	}

}
