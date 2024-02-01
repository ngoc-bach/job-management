package com.fdmgroup.RachelPlacementsTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.RachelPlacementsTracker.coreModel.Job;
import com.fdmgroup.RachelPlacementsTracker.service.JobService;

@RestController
public class JobController {
	private JobService jobService;

	@Autowired
	public JobController(JobService jobService) {
		super();
		this.jobService = jobService;
	}

	@GetMapping("jobs")
	public List<Job> findAll() {
		return this.jobService.findAll();
	}

	@GetMapping("jobs/user-jobs")
	public List<Job> findAll(@RequestHeader("user_id") String userIdString) {
		int userId = Integer.parseInt(userIdString);
		return this.jobService.findJobsByUserId(userId);
	}

	@GetMapping("jobs/{jobId}")
	public Job findById(@PathVariable int jobId) {
		return this.jobService.findById(jobId);
	}

	@PostMapping("jobs")
	public void createNewJob(@RequestBody Job newJob) {
		this.jobService.save(newJob);
	}

	@PutMapping("jobs")
	public void updateJob(@RequestBody Job newJob) {
		this.jobService.update(newJob);
	}

	@DeleteMapping("jobs/{jobId}")
	public void deleteJob(@PathVariable int jobId) {
		this.jobService.delete(jobId);
	}

}
