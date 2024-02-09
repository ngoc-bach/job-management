package com.fdmgroup.RachelPlacementsTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.RachelPlacementsTracker.coreModel.AccountManager;
import com.fdmgroup.RachelPlacementsTracker.service.AMService;

@RestController
public class AMController {
	private AMService aMService;

	@Autowired
	public AMController(AMService aMService) {
		super();
		this.aMService = aMService;
	}
	
	@GetMapping("accountManagers")
	public List<AccountManager> findAll(){
		return this.aMService.findAll();
	}
	
	@GetMapping("accountManagers/{accountManagerId}")
	public AccountManager findById(@PathVariable int accountManagerId ) {
		return this.aMService.findById(accountManagerId);
	}
	
	@PostMapping("accountManagers")
	public void createNewAccountManagers(@RequestBody AccountManager accountManager) {
		System.out.println(accountManager);
		this.aMService.save(accountManager);
	}
	
	@PutMapping("accountManagers")
	public void updateAccountManager(@RequestBody AccountManager newAccountManager) {
		this.aMService.updateAccountManager(newAccountManager);
	}	

	@DeleteMapping("accountManagers/{accountManagerId}")
	public void deleteAccountManager(@PathVariable int accountManagerId ) {
		this.aMService.deleteAccountManager(accountManagerId);
	}
}
