package com.fdmgroup.RachelPlacementsTracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.RachelPlacementsTracker.coreModel.Trainee;
import com.fdmgroup.RachelPlacementsTracker.dal.TraineeRepository;

@ExtendWith(MockitoExtension.class)
public class TraineeServiceTest {
	private TraineeService traineeService;

	@Mock
	TraineeRepository traineeRepositoryMock;

	@BeforeEach
	void setup() {
		traineeService = new TraineeService(traineeRepositoryMock);
	}

	@Test
	public void test_findAll_returnsEmptyArray_whenNoTraineesInThere() {
		List<Trainee> foundTrainees = traineeRepositoryMock.findAll();
		assertEquals(foundTrainees.size(), 0);
		verify(traineeRepositoryMock).findAll();
	}

	@Test
	public void test_findAll_returnsListOfTraineesItReceivesFromFindAllMethodOfTraineeRepository_whenCalled() {
		List<Trainee> trainees = new ArrayList<>();
		trainees.add(new Trainee());
		trainees.add(new Trainee());

		when(traineeRepositoryMock.findAll()).thenReturn(trainees);

		List<Trainee> foundTrainees = traineeService.findAll();
		assertEquals(trainees, foundTrainees);
		verify(traineeRepositoryMock, times(1)).findAll();
	}

	@Test
	void test_findById_returnsTraineeItReceivesFromFindByIdMethodOfTraineeRepository_whenCalled() {
		Trainee trainee = new Trainee();
		trainee.setId(1);
		when(traineeRepositoryMock.findById(1)).thenReturn(Optional.of(trainee));

		Trainee foundTrainee = traineeService.findById(1);
		assertEquals(trainee, foundTrainee);
		verify(traineeRepositoryMock, times(1)).findById(1);
	}

	@Test
	void test_createNewTrainee_callsSaveMethodOfTraineeRepository_whenCalled() {
		Trainee trainee = new Trainee();

		traineeService.createNewTrainee(trainee);
		verify(traineeRepositoryMock, times(1)).save(trainee);
	}

	@Test
	void test_updateTrainee_callsSaveMethodOfTraineeRepository_whenCalled() {
		Trainee trainee = new Trainee();
		trainee.setId(1);
		when(traineeRepositoryMock.existsById(trainee.getId())).thenReturn(true);

		traineeService.updateTrainee(trainee);
		verify(traineeRepositoryMock, times(1)).save(trainee);
	}

	@Test
	void test_deleteTrainee_callsDeleteByIdMethodOfTraineeRepository_whenCalled() {
		int traineeId = 1;
		when(traineeRepositoryMock.existsById(traineeId)).thenReturn(true);

		traineeService.deleteTrainee(traineeId);
		verify(traineeRepositoryMock, times(1)).deleteById(traineeId);
	}

}
