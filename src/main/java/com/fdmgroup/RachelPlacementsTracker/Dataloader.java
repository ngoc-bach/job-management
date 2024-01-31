package com.fdmgroup.RachelPlacementsTracker;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.RachelPlacementsTracker.coreModel.AccountManager;
import com.fdmgroup.RachelPlacementsTracker.coreModel.Job;
import com.fdmgroup.RachelPlacementsTracker.coreModel.Trainee;
import com.fdmgroup.RachelPlacementsTracker.dal.AMRepository;
import com.fdmgroup.RachelPlacementsTracker.dal.JobRepository;
import com.fdmgroup.RachelPlacementsTracker.dal.TraineeRepository;
import com.fdmgroup.RachelPlacementsTracker.dal.UserRepository;
import com.fdmgroup.RachelPlacementsTracker.model.User;

@Service
public class Dataloader implements ApplicationRunner {
	private UserRepository userRepository;
	private AMRepository aMRepository;
	private JobRepository jobRepository;
	private TraineeRepository traineeRepository;

	@Autowired
	public Dataloader(UserRepository userRepository, AMRepository aMRepository, JobRepository jobRepository,
			TraineeRepository traineeRepository) {
		super();
		this.userRepository = userRepository;
		this.aMRepository = aMRepository;
		this.jobRepository = jobRepository;
		this.traineeRepository = traineeRepository;
	}

	@Override
	@Transactional
	public void run(ApplicationArguments args) throws Exception {
		// CREATE USERS
		User user1 = new User("rachel.bach", "rb123", "trainee");
		User user2 = new User("dane.mckillop", "dm456", "trainee");
		User user3 = new User("wendy.wang", "ww789", "trainee");
		User user4 = new User("kelly.su", "ks123", "trainee");
		User user5 = new User("edward.bristow", "ed123", "admin");
		User user6 = new User("nicholas.lloyd", "nl456", "admin");

		List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6);
		this.userRepository.saveAll(users);

		// CREATE ACCOUNT MANAGERS
		AccountManager accountManager1 = new AccountManager("Edward", "edward.bristow@fdmgroup.com");
		AccountManager accountManager2 = new AccountManager("Nicholas", "nicholas.lloyd@fdmgroup.com");

		List<AccountManager> accountManagers = Arrays.asList(accountManager1, accountManager2);
		this.aMRepository.saveAll(accountManagers);

		// CREATE TRAINEES
		Trainee trainee1 = new Trainee("Rachel", "Bach", "rachel.bach@fdmgroup.com", "Melbourne");
		Trainee trainee2 = new Trainee("Dane", "Mckillop", "dane.mckillop@fdmgroup.com", "Melbourne");
		Trainee trainee3 = new Trainee("Wendy", "Wang", "wendy.wang@fdmgroup.com", "Sydney");
		Trainee trainee4 = new Trainee("Kelly", "Su", "kelly.su@fdmgroup.com", "Sydney");

		List<Trainee> trainees = Arrays.asList(trainee1, trainee2, trainee3, trainee4);
		this.traineeRepository.saveAll(trainees);

		// CREATE JOBS
		Job job1 = new Job("Front-End Developer", "Robert Haft", "url1", "Melbourne", "opening");
		Job job2 = new Job("Software Developer", "Transurban", "url2", "Melbourne", "opening");
		Job job3 = new Job("IOS Developer", "Butterfly", "url3", "Sydney", "opening");
		Job job4 = new Job("Site Reliability Engineer", "Wipro", "url4", "Sydney", "closed");

		List<Job> jobs = Arrays.asList(job1, job2, job3, job4);
		this.jobRepository.saveAll(jobs);

		// SET RELATIONSHIPS
		// USER - TRAINEE, ACCOUNT MANAGER
		trainee1.setUser(user1);
		trainee2.setUser(user2);
		trainee3.setUser(user3);
		trainee4.setUser(user4);

		accountManager1.setUser(user5);
		accountManager2.setUser(user6);

		// JOB - TRAINEE, ACCOUNT MANAGER
		trainee1.setJobs(Arrays.asList(job1, job3));
		trainee2.setJobs(Arrays.asList(job1, job2));
		trainee3.setJobs(Arrays.asList(job2, job3, job4));
		trainee4.setJobs(Arrays.asList(job3));

		job1.setTrainees(Arrays.asList(trainee1, trainee2));
		job2.setTrainees(Arrays.asList(trainee2, trainee3));
		job3.setTrainees(Arrays.asList(trainee1, trainee3, trainee4));
		job4.setTrainees(Arrays.asList(trainee3));

//		accountManager1.setJobs(Arrays.asList(job1, job3));
//		accountManager2.setJobs(Arrays.asList(job2, job4));
		
		job1.setAccountManager(accountManager1);
		job2.setAccountManager(accountManager2);
		job3.setAccountManager(accountManager1);
		job4.setAccountManager(accountManager2);

	}
}
