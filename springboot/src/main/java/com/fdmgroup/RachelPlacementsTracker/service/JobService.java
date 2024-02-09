package com.fdmgroup.RachelPlacementsTracker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.RachelPlacementsTracker.coreModel.AccountManager;
import com.fdmgroup.RachelPlacementsTracker.coreModel.Job;
import com.fdmgroup.RachelPlacementsTracker.coreModel.Trainee;
import com.fdmgroup.RachelPlacementsTracker.dal.AMRepository;
import com.fdmgroup.RachelPlacementsTracker.dal.JobRepository;
import com.fdmgroup.RachelPlacementsTracker.dal.TraineeRepository;
import com.fdmgroup.RachelPlacementsTracker.dal.UserRepository;
import com.fdmgroup.RachelPlacementsTracker.exceptions.AccessDeniedException;
import com.fdmgroup.RachelPlacementsTracker.exceptions.MethodNotAllowedException;
import com.fdmgroup.RachelPlacementsTracker.exceptions.NotFoundException;
import com.fdmgroup.RachelPlacementsTracker.model.User;

@Service
public class JobService {
	private JobRepository jobRepository;
	private TraineeRepository traineeRepository;
	private UserRepository userRepository;
	private AMRepository aMRepository;

	@Autowired
	public JobService(JobRepository jobRepository, TraineeRepository traineeRepository, UserRepository userRepository,
			AMRepository aMRepository) {
		super();
		this.jobRepository = jobRepository;
		this.traineeRepository = traineeRepository;
		this.userRepository = userRepository;
		this.aMRepository = aMRepository;
	}

	public List<Job> findAll(int userId) {
		List<Job> jobs = this.jobRepository.findAll();
		User foundUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("User with ID: " + userId + " cannot be found"));
		if (foundUser.getRole().equals("admin")) { 
			for (Job job : jobs) { 
				job.setEditableByUserId(userId);
			}
		} 
		return jobs;
	}

	public List<Job> findJobsByUserId(int userId) {
		List<Job> jobs = this.jobRepository.findAll();
		User foundUser = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with ID: " + userId + " cannot be found"));
		if(foundUser.getRole().equals("admin")) {
			for (Job job : jobs) { 
				job.setEditableByUserId(userId);
			}
			return jobs.stream()
					.filter(job -> job.getAccountManager().getUser().getId() == userId)
					.collect(Collectors.toList());
		} else { 
			Trainee trainee = this.traineeRepository.findTraineeByUserId(userId)
					.orElseThrow(() -> new NotFoundException("Trainee with ID: " + userId + " cannot be found"));
			return trainee.getJobs();
		}
	}

	public Job findById(int jobId) {
		return this.jobRepository.findById(jobId)
				.orElseThrow(() -> new NotFoundException("Job with ID: " + jobId + " cannot be found"));
	}

	public void save(int userId, Job newJob) {
		User foundUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("User with ID: " + userId + " cannot be found"));
		if (foundUser.getRole().equals("admin")) {
			AccountManager am = this.aMRepository.findByUserId(userId);
			newJob.setAccountManager(am);
			this.jobRepository.save(newJob);
		} else {
			throw new AccessDeniedException("Access denied");
		}

	}

	public void update(int userId, Job existingJob) {
		Job foundJob = findById(existingJob.getId());
		if (foundJob.getAccountManager().getUser().getId() == userId) {
			existingJob.setAccountManager(foundJob.getAccountManager());
			this.jobRepository.save(existingJob);
		} else {
			throw new AccessDeniedException("Access denied");
		}
	}

	public void apply(int userId, Job existingJob) {
		Trainee trainee = this.traineeRepository.findTraineeByUserId(userId)
				.orElseThrow(() -> new NotFoundException("Trainee with ID: " + userId + " cannot be found"));
		Job appliedJob = findById(existingJob.getId());
		List<Job> currentJobs = trainee.getJobs();
		if (!currentJobs.contains(appliedJob)) {
			currentJobs.add(appliedJob);
			trainee.setJobs(currentJobs);
			this.traineeRepository.save(trainee);
		} else {
			throw new RuntimeException("You already applied for this job!");
		}

	}

	public void delete(int userId, int jobId) {
		User foundUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("User with ID: " + userId + " cannot be found"));
		if (foundUser.getRole().equals("admin")) {
			Job foundJob = findById(jobId);
			if (foundJob.getAccountManager().getUser().getId() == userId && foundJob.getTrainees().size() == 0) {
				this.jobRepository.deleteById(jobId);
			} else {
				throw new MethodNotAllowedException("Deletion failed. The job has one or more applicants");
			}
		} else {
			throw new AccessDeniedException("Access denied");
		}

	}
}
