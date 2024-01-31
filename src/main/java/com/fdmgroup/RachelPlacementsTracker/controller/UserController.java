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
	public List<User> findAll() {
		return this.userService.findAll();
	}

	@GetMapping("users/{userId}")
	public User findById(@PathVariable int userId) {
		return this.userService.findById(userId);
	}

	@PostMapping("users")
	public void createNewUser(@RequestBody User newUser) {
		System.out.println(newUser);
		this.userService.save(newUser);
	}

	@PutMapping("users")
	public void updateUser(@RequestBody User newUser) {
		this.userService.update(newUser);
	}

	@DeleteMapping("users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		this.userService.deleteById(userId);
	}
}
