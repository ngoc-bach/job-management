package com.fdmgroup.RachelPlacementsTracker.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
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

import com.fdmgroup.RachelPlacementsTracker.coreModel.AccountManager;
import com.fdmgroup.RachelPlacementsTracker.coreModel.Trainee;
import com.fdmgroup.RachelPlacementsTracker.dal.AMRepository;
import com.fdmgroup.RachelPlacementsTracker.dal.TraineeRepository;
import com.fdmgroup.RachelPlacementsTracker.dal.UserRepository;
import com.fdmgroup.RachelPlacementsTracker.model.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	private UserService userService;

	@Mock
	private UserRepository userRepositoryMock;

	@Mock
	private TraineeRepository traineeRepositoryMock;

	@Mock
	private AMRepository aMRepositoryMock;

	@BeforeEach
	void setup() {
		userService = new UserService(userRepositoryMock, traineeRepositoryMock, aMRepositoryMock);
	}

	@Test
	public void test_findAll_returnsEmptyArray_whenNoUsersInThere() {
		List<User> foundUsers = userRepositoryMock.findAll();
		assertEquals(foundUsers.size(), 0);
		verify(userRepositoryMock).findAll();
	}

	@Test
	public void test_findAll_callsFindAllMethodOfUserRepository_whenCalled() {
		userService.findAll();
		verify(userRepositoryMock, times(1)).findAll();
	}

	@Test
	public void test_findAll_returnsListOfUsersItReceivesFromFindAllMethodOfUserRepository_whenCalled() {
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());

		when(userRepositoryMock.findAll()).thenReturn(users);

		List<User> foundUsers = userService.findAll();
		assertEquals(users, foundUsers);
		verify(userRepositoryMock, times(1)).findAll();
	}

	@Test
	void test_findById_returnsUserItReceivesFromFindByIdMethodOfUserRepository_whenCalled() {
		Optional<User> user = Optional.of(new User(1, "rachel.bach", "rb123", "trainee", "Rachel", "Bach",
				"rachel.bach@fdmgroup.com", "Melbourne"));
		when(userRepositoryMock.findById(1)).thenReturn(user);

		Optional<User> foundUser = Optional.of(userService.findById(1));
		assertEquals(user, foundUser);
		verify(userRepositoryMock, times(1)).findById(1);
	}

	@Test
	void test_saveTrainee_callsSaveMethodOfTraineeRepositoryAndUserRepository_whenCalled() {
		User newUser = new User();
		userService.saveTrainee(newUser);
		verify(userRepositoryMock, times(1)).save(newUser);
		verify(traineeRepositoryMock, times(1)).save(any(Trainee.class));
	}

	@Test
	void test_saveAccountManager_callsSaveMethodOfAMRepositoryAndUserRepository_whenCalled() {
		User newUser = new User();
		userService.saveAccountManager(newUser);
		verify(userRepositoryMock, times(1)).save(newUser);
		verify(aMRepositoryMock, times(1)).save(any(AccountManager.class));
	}

	@Test
	void test_update_callsSaveMethodOfUserRepository_whenCalled() {
		User newUser = new User();

		when(userRepositoryMock.existsById(newUser.getId())).thenReturn(true);

		// Assert that no exception is thrown
		assertDoesNotThrow(() -> userService.update(newUser));

		verify(userRepositoryMock, times(1)).save(newUser);
	}

	@Test
	void test_update_throwsRuntimeException_whenInvalidId() {
		User newUser = new User();

		when(userRepositoryMock.existsById(newUser.getId())).thenReturn(false);

		// Assert that a RuntimeException is thrown
		RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.update(newUser));

		// Assert the exception message
		assertEquals("Invalid ID: " + newUser.getId(), exception.getMessage());

		verify(userRepositoryMock, never()).save(newUser);
	}

	@Test
	void test_delete_callsDeleteByIdOfUserRepository_whenCalled() {
		User newUser = new User();

		when(userRepositoryMock.existsById(newUser.getId())).thenReturn(true);

		// Assert that no exception is thrown
		assertDoesNotThrow(() -> userService.deleteById(newUser.getId()));

		verify(userRepositoryMock, times(1)).deleteById(newUser.getId());
	}

	@Test
	void test_deleteById_throwsRuntimeException_whenInvalidId() {
		User newUser = new User();

		when(userRepositoryMock.existsById(newUser.getId())).thenReturn(false);

		// Assert that a RuntimeException is thrown
		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> userService.deleteById(newUser.getId()));

		// Assert the exception message
		assertEquals("Invalid ID: " + newUser.getId(), exception.getMessage());

		verify(userRepositoryMock, never()).deleteById(newUser.getId());
	}
}
