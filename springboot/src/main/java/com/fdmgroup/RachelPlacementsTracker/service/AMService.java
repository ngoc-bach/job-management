package com.fdmgroup.RachelPlacementsTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.RachelPlacementsTracker.coreModel.AccountManager;
import com.fdmgroup.RachelPlacementsTracker.dal.AMRepository;
import com.fdmgroup.RachelPlacementsTracker.exceptions.NotFoundException;

@Service
public class AMService {
	private AMRepository aMRepository;

	@Autowired
	public AMService(AMRepository aMRepository) {
		super();
		this.aMRepository = aMRepository;
	}

	public List<AccountManager> findAll() {
		return this.aMRepository.findAll();
	}

	public AccountManager findById(int accountManagerId) {
		return this.aMRepository.findById(accountManagerId).orElseThrow(
				() -> new NotFoundException("Account Manager with ID: " + accountManagerId + " cannot be found"));
	}

	public void save(AccountManager accountManager) {
		this.aMRepository.save(accountManager);
	}

	public void updateAccountManager(AccountManager newAccountManager) {
		if (this.aMRepository.existsById(newAccountManager.getId())) {
			this.aMRepository.save(newAccountManager);
		} else {
			throw new NotFoundException("Account Manager with ID: " + newAccountManager.getId() + " cannot be found");
		}
	}

	public void deleteAccountManager(int accountManagerId) {
		if (this.aMRepository.existsById(accountManagerId)) {
			this.aMRepository.deleteById(accountManagerId);
		} else {
			throw new NotFoundException("Account Manager with ID: " + accountManagerId + " cannot be found");
		}
	}

}
