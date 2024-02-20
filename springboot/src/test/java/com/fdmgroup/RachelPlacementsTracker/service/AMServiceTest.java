package com.fdmgroup.RachelPlacementsTracker.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

import com.fdmgroup.RachelPlacementsTracker.coreModel.AccountManager;
import com.fdmgroup.RachelPlacementsTracker.dal.AMRepository;
import com.fdmgroup.RachelPlacementsTracker.model.User;

@ExtendWith(MockitoExtension.class)
public class AMServiceTest {
	private AMService aMService;

	@Mock
	AMRepository aMRepositoryMock;

	@BeforeEach
	void setup() {
		aMService = new AMService(aMRepositoryMock);
	}

	@Test
	public void test_findAll_returnsEmptyArray_whenNoAMsInThere() {
		List<AccountManager> foundAMs = aMRepositoryMock.findAll();
		assertEquals(foundAMs.size(), 0);
		verify(aMRepositoryMock).findAll();
	}

	@Test
	public void test_findAll_callsFindAllMethodOfAMRepository_whenCalled() {
		aMService.findAll();
		verify(aMRepositoryMock, times(1)).findAll();
	}

	@Test
	public void test_findAll_returnsListOfAMsItReceivesFromFindAllMethodOfAMRepository_whenCalled() {
		List<AccountManager> Ams = new ArrayList<>();
		Ams.add(new AccountManager());
		Ams.add(new AccountManager());

		when(aMRepositoryMock.findAll()).thenReturn(Ams);

		List<AccountManager> foundAms = aMService.findAll();
		assertEquals(Ams, foundAms);
		verify(aMRepositoryMock, times(1)).findAll();
	}

	@Test
	void test_findById_returnsAMItReceivesFromFindByIdMethodOfAMRepository_whenCalled() {
		User user = new User();
		Optional<AccountManager> aM = Optional
				.of(new AccountManager(1, "Rachel", "Bach", "rachel.bach@fdmgroup.com", "Melbourne", user));
		when(aMRepositoryMock.findById(1)).thenReturn(aM);

		Optional<AccountManager> foundAm = Optional.of(aMService.findById(1));
		assertEquals(aM, foundAm);
		verify(aMRepositoryMock, times(1)).findById(1);
	}

	@Test
	public void test_save_callsSaveMethodOfAMRepository_whenCalled() {
		AccountManager aM = new AccountManager();
		aMService.save(aM);
		verify(aMRepositoryMock, times(1)).save(aM);
	}

	@Test
	void test_updateAccountManager_callsSaveMethodOfUserRepository_whenCalled() {
		AccountManager aM = new AccountManager();
		aM.setId(1);
		when(aMRepositoryMock.existsById(aM.getId())).thenReturn(true);
		aMService.updateAccountManager(aM);

		verify(aMRepositoryMock, times(1)).save(aM);

	}
	
	@Test
	void test_deleteAccountManager_callsDeleteByIdMethodOfAMRepository_whenCalled() {
		AccountManager aM = new AccountManager();
		aM.setId(1);
		when(aMRepositoryMock.existsById(aM.getId())).thenReturn(true);
		aMService.deleteAccountManager(aM.getId());

		verify(aMRepositoryMock, times(1)).deleteById(aM.getId());

	}
}
