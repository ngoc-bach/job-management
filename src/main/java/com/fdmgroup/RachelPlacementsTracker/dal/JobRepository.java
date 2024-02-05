package com.fdmgroup.RachelPlacementsTracker.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.RachelPlacementsTracker.coreModel.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
	
}
