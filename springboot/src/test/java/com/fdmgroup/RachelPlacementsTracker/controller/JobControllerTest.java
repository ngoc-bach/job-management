package com.fdmgroup.RachelPlacementsTracker.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.RachelPlacementsTracker.coreModel.Job;
import com.fdmgroup.RachelPlacementsTracker.service.JobService;

@ExtendWith(MockitoExtension.class)
public class JobControllerTest {
	private JobController jobController;

	@Mock
	private JobService jobServiceMock;

	@BeforeEach
	void setup() {
		jobController = new JobController(jobServiceMock);
	}

	@Test
	public void test_findAll_callsFindAllMethodOfJobService_whenCalled() {
		jobController.findAll("1");
		verify(jobServiceMock, times(1)).findAll(1);
	}

	@Test
	public void test_findJobsByUserId_callsFindJobsByUserIdMethodOfJobService_whenCalled() {
		jobController.findJobsByUserId("1");
		verify(jobServiceMock, times(1)).findJobsByUserId(1);
	}

	@Test
	public void test_findJobByUserId_callsfindJobByUserIdMethodOfJobService_whenCalled() {
		jobController.findJobByUserId(1, "1");
		verify(jobServiceMock, times(1)).findJobByUserId(1, 1);
	}

	@Test
	public void test_createNewJob_callsSaveMethodOfJobService_whenCalled() {
		Job job = new Job();
		jobController.createNewJob("1", job);
		verify(jobServiceMock, times(1)).save(1, job);
	}
	
	@Test
	public void test_updateJob_callsUpdateMethodOfJobService_whenCalled() {
		Job job = new Job();
		jobController.updateJob("1", job);
		verify(jobServiceMock, times(1)).update(1, job);
	}
	
	@Test
	public void test_applyJob_callsApplyMethodOfJobService_whenCalled() {
		Job job = new Job();
		jobController.applyJob("1", job);
		verify(jobServiceMock, times(1)).apply(1, job);
	}
	
	@Test
	public void test_deleteJob_callsDeleteMethodOfJobService_whenCalled() {
		jobController.deleteJob("1", 1);
		verify(jobServiceMock, times(1)).delete(1, 1);
	}
	
	@Test
	public void test_searchByPosition_callsFindPartialMatchMethodOfJobService_whenCalled() {
		jobController.searchByPosition("position");
		verify(jobServiceMock, times(1)).findPartialMatch("position");
	}
}
