package com.fdmgroup.RachelPlacementsTracker.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.RachelPlacementsTracker.dal.UserRepository;
import com.fdmgroup.RachelPlacementsTracker.model.User;
import com.fdmgroup.RachelPlacementsTracker.service.UserService;

@RestController
public class AuthController {
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
	
	private UserRepository userRepo;
	private final TokenService tokenService;

	public AuthController(TokenService tokenService, UserRepository userRepo) {
		this.tokenService = tokenService;
		this.userRepo = userRepo;
	}

	@PostMapping("auth/login")
	public String token(Authentication authentication) {
		LOG.debug("Token requested for user: '{}'", authentication.getName());
		String token = tokenService.generateToken(authentication);
		LOG.debug("Token granted: {}", token);
		return token;
	}

	@GetMapping("user")
	public User getUser (Authentication auth) {
		User currentUser = this.userRepo.findByUsername(auth.getName()).orElseThrow(() -> new UsernameNotFoundException(auth.getName()));
		return currentUser;
	}
	
//	@GetMapping("example")
//	public String example(Authentication auth) {
//		System.out.println("name:  " + auth.getName());
//		System.out.println("princ: " + auth.getPrincipal());
//		System.out.println("creds: " + auth.getCredentials());
//		System.out.println(auth.getAuthorities());
//		return "Hello, " + auth.getName();
//	}
//
//	@GetMapping("adminonly")
//	public String example() {
//		return "You must be an admin";
//	}

}
