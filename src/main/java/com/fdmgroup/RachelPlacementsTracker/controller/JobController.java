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
	public List<Job> findAll(@RequestHeader("user_id") String userIdString) {
		int userId = Integer.parseInt(userIdString);
		return this.jobService.findAll(userId);
	}

	@GetMapping("jobs/user")
	public List<Job> findJobsByUserId(@RequestHeader("user_id") String userIdString) {
		int userId = Integer.parseInt(userIdString);
		return this.jobService.findJobsByUserId(userId);
	}

	@GetMapping("jobs/{jobId}")
	public Job findById(@PathVariable int jobId) {
		return this.jobService.findById(jobId);
	}

	@PostMapping("jobs")
	public void createNewJob(@RequestHeader("user_id") String userIdString, @RequestBody Job existingJob) {
		int userId = Integer.parseInt(userIdString);
		this.jobService.save(userId, existingJob);
	}

	@PutMapping("jobs")
	public void updateJob(
			@RequestHeader("user_id") String userIdString, 
			@RequestBody Job newJob) {
		int userId = Integer.parseInt(userIdString);
		this.jobService.update(userId, newJob);
	}
	
	@PutMapping("jobs/apply")
	public void applyJob(
			@RequestHeader("user_id") String userIdString, @RequestBody Job existingJob) {
		int userId = Integer.parseInt(userIdString);
		this.jobService.apply(userId, existingJob);
	}

	@DeleteMapping("jobs/{jobId}")
	public void deleteJob(@RequestHeader("user_id") String userIdString, @PathVariable int jobId) {
		int userId = Integer.parseInt(userIdString);
		this.jobService.delete(userId , jobId);
	}

}
