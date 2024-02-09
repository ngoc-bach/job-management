package com.fdmgroup.RachelPlacementsTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.RachelPlacementsTracker.coreModel.Trainee;
import com.fdmgroup.RachelPlacementsTracker.service.TraineeService;

@RestController
public class TraineeController {
	private TraineeService traineeService;
	
	@Autowired
	public TraineeController(TraineeService traineeService) {
		super();
		this.traineeService = traineeService;
	}
	
	@GetMapping("trainees")
	public List<Trainee> findAll(){
		return this.traineeService.findAll();
	}
	
	@GetMapping("trainees/{traineeId}")
	public Trainee findById(@PathVariable int traineeId) {
		return this.traineeService.findById(traineeId);
	}
	
	@PostMapping("trainees")
	public void createNewTrainee(@RequestBody Trainee newTrainee) {
		this.traineeService.createNewTrainee(newTrainee);
	}
	
	@PutMapping("trainees")
	public void updateTrainee(@RequestBody Trainee newTrainee) {
		this.traineeService.updateTrainee(newTrainee);
	}

	@DeleteMapping("trainees/{traineeId}")
	public void deleteTrainee(@PathVariable int traineeId) {
		this.traineeService.deleteTrainee(traineeId);
	
	}
}
