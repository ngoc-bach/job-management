package com.fdmgroup.RachelPlacementsTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.RachelPlacementsTracker.coreModel.Job;
import com.fdmgroup.RachelPlacementsTracker.dal.JobRepository;

@Service
public class JobService {
	private JobRepository jobRepository;

	@Autowired
	public JobService(JobRepository jobRepository) {
		super();
		this.jobRepository = jobRepository;
	}

	public List<Job> findAll() {
		return this.jobRepository.findAll();
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
