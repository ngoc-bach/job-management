package com.fdmgroup.RachelPlacementsTracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import com.fdmgroup.RachelPlacementsTracker.coreModel.Job;
import com.fdmgroup.RachelPlacementsTracker.coreModel.Trainee;
import com.fdmgroup.RachelPlacementsTracker.dal.AMRepository;
import com.fdmgroup.RachelPlacementsTracker.dal.JobRepository;
import com.fdmgroup.RachelPlacementsTracker.dal.TraineeRepository;
import com.fdmgroup.RachelPlacementsTracker.dal.UserRepository;
import com.fdmgroup.RachelPlacementsTracker.model.User;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {
	private JobService jobService;

	@Mock
	private JobRepository jobRepositoryMock;

	@Mock
	private TraineeRepository traineeRepositoryMock;

	@Mock
	private UserRepository userRepositoryMock;

	@Mock
	private AMRepository aMRepository;

	@BeforeEach
	void setup() {
		jobService = new JobService(jobRepositoryMock, traineeRepositoryMock, userRepositoryMock, aMRepository);
	}

	@Test
	public void test_findAll_returnsEmptyArray_whenNoJobsInThere() {
		List<Job> foundJobs = jobRepositoryMock.findAll();
		assertEquals(foundJobs.size(), 0);
		verify(jobRepositoryMock).findAll();
	}

	@Test
	public void test_findAll_returnsListOfJobs() {
		List<Job> jobs = new ArrayList<>();
		jobs.add(new Job());
		jobs.add(new Job());
		when(jobRepositoryMock.findAll()).thenReturn(jobs);

		Optional<User> user = Optional.ofNullable(new User(1, "rachel.bach", "rb123", "trainee", "Rachel", "Bach",
				"rachel.bach@fdmgroup.com", "Melbourne"));
		when(userRepositoryMock.findById(1)).thenReturn(user);

		List<Job> foundJobs = jobService.findAll(1);
		assertEquals(jobs, foundJobs);
		verify(jobRepositoryMock, times(1)).findAll();

	}

	@Test
	public void test_findJobsByUserId_AdminUser() {
		// Mocking userRepository behavior
		User adminUser = new User();
		adminUser.setId(1);
		adminUser.setRole("admin");
		when(userRepositoryMock.findById(1)).thenReturn(Optional.of(adminUser));

		// Mocking jobRepository behavior
		List<Job> jobs = new ArrayList<>();
		Job job1 = new Job();
		job1.setId(1);
		job1.setAccountManager(new AccountManager("Edward", "Bristow", "edward.bristow@fdmgroup.com", "Melbourne",
				new User(1, "edward.bristow", "ed123", "admin", "Edward", "Bristow", "edward.bristow@fdmgroup.com",
						"Melbourne")));
		jobs.add(job1);
		Job job2 = new Job();
		job2.setId(2);
		job2.setAccountManager(new AccountManager("Nicholas", "Lloyd", "nicholas.lloyd@fdmgroup.com", "Sydney",
				new User(2, "nicholas.lloyd", "nl456", "admin", "Nicholas", "Lloyd", "nicholas.lloyd@fdmgroup.com",
						"Sydney")));
		jobs.add(job2);
		when(jobRepositoryMock.findAll()).thenReturn(jobs);

		// Test
		List<Job> result = jobService.findJobsByUserId(1);
		assertEquals(1, result.size());
		assertEquals(1, result.get(0).getId());

	}

	@Test
	public void test_findJobsByUserId_NonAdminUser() {
		// Mocking userRepository behavior
		User nonAdminUser = new User();
		nonAdminUser.setId(1);
		nonAdminUser.setRole("trainee");
		when(userRepositoryMock.findById(1)).thenReturn(Optional.of(nonAdminUser));

		// Mocking traineeRepository behavior
		Trainee trainee = new Trainee();
		trainee.setUser(nonAdminUser);
		List<Job> jobs = new ArrayList<>();
		Job job1 = new Job();
		job1.setId(1);
		jobs.add(job1);
		Job job2 = new Job();
		job2.setId(2);
		jobs.add(job2);
		trainee.setJobs(jobs);

		when(traineeRepositoryMock.findTraineeByUserId(1)).thenReturn(Optional.of(trainee));

		// Test
		List<Job> result = jobService.findJobsByUserId(1);
		assertEquals(2, result.size());
		assertEquals(1, result.get(0).getId());
		assertEquals(2, result.get(1).getId());
	}

	@Test
	public void test_save_Successful() {
		// Mock data
		User adminUser = new User();
		adminUser.setId(1);
		adminUser.setRole("admin");

		AccountManager accountManager = new AccountManager();
		accountManager.setId(1);

		Job newJob = new Job();

		// Mock userRepository behavior
		when(userRepositoryMock.findById(1)).thenReturn(Optional.of(adminUser));

		// Mock aMRepository behavior
		when(aMRepository.findByUserId(1)).thenReturn(accountManager);

		// Perform the method call
		jobService.save(1, newJob);

		// Verify interactions
		verify(userRepositoryMock).findById(1);
		verify(aMRepository).findByUserId(1);
		verify(jobRepositoryMock).save(newJob);
	}

	@Test
	public void test_update_Successful() {
		// Mock data
		int userId = 1;
		User user = new User();
		user.setId(userId);

		AccountManager accountManager = new AccountManager();
		accountManager.setUser(user);

		Job existingJob = new Job();
		existingJob.setId(1);
		existingJob.setAccountManager(accountManager);

		when(jobRepositoryMock.findById(1)).thenReturn(Optional.of(existingJob));

		jobService.update(userId, existingJob);

		verify(jobRepositoryMock).save(existingJob);

	}

	@Test
	public void test_apply_Successful() {
		// Mock data
		int userId = 1;
		int jobId = 1;

		Trainee trainee = new Trainee();
		trainee.setId(1);

		Job existingJob = new Job();
		existingJob.setId(jobId);

		List<Job> currentJobs = new ArrayList<>();
		currentJobs.add(existingJob);

		// Mock traineeRepository behavior
		when(traineeRepositoryMock.findTraineeByUserId(userId)).thenReturn(Optional.of(trainee));

		// Mock jobRepository behavior
		when(jobRepositoryMock.findById(jobId)).thenReturn(Optional.of(existingJob));

		// Perform the method call
		jobService.apply(userId, existingJob);

		// Verify interactions
		verify(traineeRepositoryMock).findTraineeByUserId(userId);
		verify(jobRepositoryMock).findById(jobId);
		verify(traineeRepositoryMock).save(trainee);

		// Ensure the job is added to the trainee's jobs
		assertTrue(trainee.getJobs().contains(existingJob));
	}


    @Test
    public void testFindPartialMatch() {
        // Mock data
        String query = "software";

        // Create a list of jobs that partially match the query
        List<Job> mockJobs = new ArrayList<>();
        Job job1 = new Job();
        job1.setId(1);
        job1.setPosition("Software Engineer");
        mockJobs.add(job1);

        Job job2 = new Job();
        job2.setId(2);
        job2.setPosition("Software Developer");
        mockJobs.add(job2);

        // Mock jobRepository behavior
        when(jobRepositoryMock.findPartialMatch(query)).thenReturn(mockJobs);

        // Perform the method call
        List<Job> result = jobService.findPartialMatch(query);

        // Verify interactions
        verify(jobRepositoryMock).findPartialMatch(query);

        // Verify the result
        assertEquals(mockJobs.size(), result.size());
        assertTrue(result.contains(job1));
        assertTrue(result.contains(job2));
    }
}
