package com.fdmgroup.RachelPlacementsTracker.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.RachelPlacementsTracker.coreModel.Trainee;
import com.fdmgroup.RachelPlacementsTracker.service.TraineeService;

@ExtendWith(MockitoExtension.class)
public class TraineeControllerTest {
	private TraineeController traineeController;
	private Trainee trainee;

	@Mock
	TraineeService traineeServiceMock;

	@BeforeEach
	void setup() {
		traineeController = new TraineeController(traineeServiceMock);
	}

	@Test
	public void test_findAll_callsfindAllMethodOfTraineeService_whenCalled() {
		traineeController.findAll();
		verify(traineeServiceMock, times(1)).findAll();
	}

	@Test
	public void test_findById_callsFindByIdMethodOfTraineeService_whenCalled() {
		traineeController.findById(1);
		verify(traineeServiceMock, times(1)).findById(1);
	}

	@Test
	public void test_createNewTrainee_callsCreateNewTraineeMethodOfTraineeService_whenCalled() {
		traineeController.createNewTrainee(trainee);
		verify(traineeServiceMock, times(1)).createNewTrainee(trainee);
	}
	
	@Test
	public void test_updateTrainee_callsUpdateTraineeMethodOfTraineeService_whenCalled() {
		traineeController.updateTrainee(trainee);
		verify(traineeServiceMock, times(1)).updateTrainee(trainee);
	}
	
	@Test
	public void test_deleteTrainee_callsDeleteTraineeMethodOfTraineeService_whenCalled() {
		traineeController.deleteTrainee(1);
		verify(traineeServiceMock, times(1)).deleteTrainee(1);
	}
}
