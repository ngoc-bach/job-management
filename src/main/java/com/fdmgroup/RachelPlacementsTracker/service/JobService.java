package com.fdmgroup.RachelPlacementsTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.RachelPlacementsTracker.coreModel.Job;
import com.fdmgroup.RachelPlacementsTracker.coreModel.Trainee;
import com.fdmgroup.RachelPlacementsTracker.dal.JobRepository;
import com.fdmgroup.RachelPlacementsTracker.dal.TraineeRepository;

@Service
public class JobService {
	private JobRepository jobRepository;
	private TraineeRepository traineeRepository;

	@Autowired
	public JobService(JobRepository jobRepository, TraineeRepository traineeRepository) {
		super();
		this.jobRepository = jobRepository;
		this.traineeRepository = traineeRepository;
	}

	public List<Job> findAll() {
		return this.jobRepository.findAll();
	}

	public List<Job> findJobsByUserId(int userId) {
		List<Trainee> trainees = this.traineeRepository.findAll();
		List<Job> jobs = this.jobRepository.findAll();

		// Check hasApplied if user is a trainee
		List<Job> foundJobsByTrainee = null;
		for (Trainee trainee : trainees) {
			if (trainee.getUser().getId() == userId) {
				foundJobsByTrainee = trainee.getJobs();
			}
		}
		if (foundJobsByTrainee != null) {
			for (Job job : foundJobsByTrainee) {
				job.setHasApplied(true);
			}
		}

		// Check isEditable if user is an accountManager
		for (Job job : jobs) {
			if (job.getAccountManager().getUser().getId() == userId) {
				job.setEditable(true);
			}
		}

		// Update hasApplied for common jobs between trainee and all jobs
		if (foundJobsByTrainee != null) {
			for (Job job : jobs) {
				for (Job job2 : foundJobsByTrainee) {
					if (job.getId() == job2.getId()) {
						job.setHasApplied(true);
					}
				}
			}
		}
		return jobs;
	}

	public Job findById(int jobId) {
		return this.jobRepository.findById(jobId)
				.orElseThrow(() -> new RuntimeException("Job with ID: " + jobId + " cannot be found"));
	}

	public void save(Job newJob) {
		this.jobRepository.save(newJob);

	}

	public void update(Job newJob) {
		if (this.jobRepository.existsById(newJob.getId())) {
			this.jobRepository.save(newJob);
			return;
		}
		throw new RuntimeException("Invalid ID: " + newJob.getId());

	}

	public void delete(int jobId) {
		if (this.jobRepository.existsById(jobId)) {
			this.jobRepository.deleteById(jobId);
			;
			return;
		}
		throw new RuntimeException("Invalid ID: " + jobId);

	}

}
