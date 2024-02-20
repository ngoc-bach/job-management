package com.fdmgroup.RachelPlacementsTracker;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.RachelPlacementsTracker.coreModel.Job;
import com.fdmgroup.RachelPlacementsTracker.model.User;

import com.fdmgroup.RachelPlacementsTracker.service.JobService;
import com.fdmgroup.RachelPlacementsTracker.service.UserService;

@Service
public class Dataloader implements ApplicationRunner {
	private JobService jobService;
	private UserService userService;

	@Autowired
	public Dataloader(UserService userservice, JobService jobService) {
		super();
		this.jobService = jobService;
		this.userService = userservice;
	}

	@Override
	@Transactional
	public void run(ApplicationArguments args) throws Exception {
		//	CREATE USERS
		User user1 = new User("rachel.bach", "rb123", "trainee", "Rachel", "Bach", "rachel.bach@fdmgroup.com",
				"Melbourne");
		User user2 = new User("dane.mckillop", "dm456", "trainee", "Dane", "Mckillop", "dane.mckillop@fdmgroup.com",
				"Melbourne");
		User user3 = new User("wendy.wang", "ww789", "trainee", "Wendy", "Wang", "wendy.wang@fdmgroup.com", "Sydney");
		User user4 = new User("kelly.su", "ks123", "trainee", "Kelly", "Su", "kelly.su@fdmgroup.com", "Sydney");
		User user5 = new User("edward.bristow", "ed123", "admin", "Edward", "Bristow", "edward.bristow@fdmgroup.com",
				"Melbourne");
		User user6 = new User("nicholas.lloyd", "nl456", "admin", "Nicholas", "Lloyd", "nicholas.lloyd@fdmgroup.com",
				"Sydney");

		this.userService.register(user1);
		this.userService.register(user2);
		this.userService.register(user3);
		this.userService.register(user4);
		this.userService.register(user5);
		this.userService.register(user6);

		// CREATE JOBS
		Job job1 = new Job("Front-End Developer", "Robert Haft", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Quis hendrerit dolor magna eget est. Tellus in metus vulputate eu scelerisque felis imperdiet proin fermentum.", "Melbourne", "opening");
		Job job2 = new Job("Software Developer", "Transurban", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Non sodales neque sodales ut etiam sit amet nisl. Sollicitudin tempor id eu nisl nunc mi ipsum faucibus vitae.", "Melbourne", "opening");
		Job job3 = new Job("IOS Developer", "Butterfly", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Non sodales neque sodales ut etiam sit amet nisl. Sollicitudin tempor id eu nisl nunc mi ipsum faucibus vitae.", "Sydney", "opening");
		Job job4 = new Job("Site Reliability Engineer", "Wipro", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Non sodales neque sodales ut etiam sit amet nisl. Sollicitudin tempor id eu nisl nunc mi ipsum faucibus vitae.", "Sydney", "closed");
		Job job5 = new Job("Technical Consultant", "SymphonyAI", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Non sodales neque sodales ut etiam sit amet nisl. Sollicitudin tempor id eu nisl nunc mi ipsum faucibus vitae.", "Melbourne", "opening");
		Job job6 = new Job("Software Developer", "Green Light Worldwide", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Non sodales neque sodales ut etiam sit amet nisl. Sollicitudin tempor id eu nisl nunc mi ipsum faucibus vitae.", "Canberra", "closed");
		Job job7 = new Job("Data Engineer", "Programa", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Non sodales neque sodales ut etiam sit amet nisl. Sollicitudin tempor id eu nisl nunc mi ipsum faucibus vitae.", "Perth", "opening");
		Job job8 = new Job("Graduate .Net Developer", "Integral Technology Solutions", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Non sodales neque sodales ut etiam sit amet nisl. Sollicitudin tempor id eu nisl nunc mi ipsum faucibus vitae.", "Brisbane", "opening");
		Job job9 = new Job("Machine Learning Engineer", "Mighty Games Group", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Non sodales neque sodales ut etiam sit amet nisl. Sollicitudin tempor id eu nisl nunc mi ipsum faucibus vitae.", "Sydney", "closed");
		Job job10 = new Job("Full Stack Developer", "CivilPro", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Non sodales neque sodales ut etiam sit amet nisl. Sollicitudin tempor id eu nisl nunc mi ipsum faucibus vitae.", "Sydney", "opening");

		
		this.jobService.save(5, job1);
		this.jobService.save(5, job2);
		this.jobService.save(6, job3);
		this.jobService.save(6, job4);
		this.jobService.save(5, job5);
		this.jobService.save(5, job6);
		this.jobService.save(6, job7);
		this.jobService.save(6, job8);
		this.jobService.save(5, job9);
		this.jobService.save(5, job10);

	}
}
