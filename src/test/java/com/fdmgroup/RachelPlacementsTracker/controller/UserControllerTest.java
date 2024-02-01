package com.fdmgroup.RachelPlacementsTracker.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.RachelPlacementsTracker.model.User;
import com.fdmgroup.RachelPlacementsTracker.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	private UserController userController;
	private User user;
	
	@Mock
	private UserService userServiceMock;
	
	@BeforeEach
	void setup() {
		userController = new UserController(userServiceMock);
	}
	
	@Test
	public void test_findAll_callsfindAllMethodOfUserService_whenCalled() {
		userController.findAll();
		verify(userServiceMock, times(1)).findAll();
	}
	
	@Test
	public void test_findById_callsfindByIdMethodOfUserService_whenCalled() {
		userController.findById(1);
		verify(userServiceMock, times(1)).findById(1);
	}
	
	@Test
	public void test_createNewUser_callsSaveTraineeMethodOfUserService_whenCalled() {
		user = new User("rachel.bach", "rb123", "trainee", "Rachel", "Bach", "rachel.bach@fdmgroup.com", "Melbourne");
		userController.createNewUser(user);
		verify(userServiceMock, times(1)).saveTrainee(user);
	}
	
	@Test
	public void test_createNewUser_callsSaveAccountManagerMethodOfUserService_whenCalled() {
		user = new User("edward.bristow", "ed123", "admin", "Edward", "Bristow", "edward.bristow@fdmgroup.com", "Melbourne");
		userController.createNewUser(user);
		verify(userServiceMock, times(1)).saveAccountManager(user);
	}
	
	@Test
	public void test_updateUser_callsUpdateMethodOfUserService_whenCalled() {
		userController.updateUser(user);
		verify(userServiceMock, times(1)).update(user);
	}
	
	@Test
	public void test_deleteUser_callsDeleteByIdMethodOfUserService_whenCalled() {
		userController.deleteUser(1);
		verify(userServiceMock, times(1)).deleteById(1);
	}
	
}
