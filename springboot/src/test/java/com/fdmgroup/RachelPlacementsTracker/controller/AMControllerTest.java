package com.fdmgroup.RachelPlacementsTracker.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.RachelPlacementsTracker.coreModel.AccountManager;
import com.fdmgroup.RachelPlacementsTracker.service.AMService;

@ExtendWith(MockitoExtension.class)
public class AMControllerTest {
	private AMController aMController;

	@Mock
	AMService aMServiceMock;

	@BeforeEach
	void setup() {
		aMController = new AMController(aMServiceMock);
	}

	@Test
	public void test_findAll_callsfindAllMethodOfAMService_whenCalled() {
		aMController.findAll();
		verify(aMServiceMock, times(1)).findAll();
	}
	
	@Test
	public void test_findById_callsFindByIdMethodOfAMService_whenCalled() {
		aMController.findById(1);
		verify(aMServiceMock, times(1)).findById(1);
	}
	
	@Test
	public void test_createNewAccountManager_callsCreateNewAccountManagerMethodOfAMService_whenCalled() {
		AccountManager aM = new  AccountManager();
		aMController.createNewAccountManager(aM);
		verify(aMServiceMock, times(1)).save(aM);
	}
	
	@Test
	public void test_updateAccountManager_callsUpdateAccountManagerMethodOfAMService_whenCalled() {
		AccountManager aM = new  AccountManager();
		aMController.updateAccountManager(aM);
		verify(aMServiceMock, times(1)).updateAccountManager(aM);
	}
	
	@Test
	public void test_deleteAccountManager_callsDeleteAccountManagerMethodOfAMService_whenCalled() {
		aMController.deleteAccountManager(1);
		verify(aMServiceMock, times(1)).deleteAccountManager(1);
	}
}
