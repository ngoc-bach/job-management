package com.fdmgroup.RachelPlacementsTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.fdmgroup.RachelPlacementsTracker.security.RsaKeyProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class RachelPlacementsTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RachelPlacementsTrackerApplication.class, args);
	}

}
