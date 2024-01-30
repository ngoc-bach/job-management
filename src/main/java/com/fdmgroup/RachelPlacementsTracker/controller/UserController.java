package com.fdmgroup.RachelPlacementsTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.RachelPlacementsTracker.model.User;
import com.fdmgroup.RachelPlacementsTracker.service.UserService;

@RestController
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("users")
	public List<User> findAll(){
		return this.userService.findAll();
	}
}
