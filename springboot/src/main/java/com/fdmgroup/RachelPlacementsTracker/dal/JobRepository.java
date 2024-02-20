package com.fdmgroup.RachelPlacementsTracker.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.RachelPlacementsTracker.coreModel.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
	@Query("SELECT j FROM Job j WHERE j.position LIKE CONCAT('%',:position,'%')")
	List<Job> findPartialMatch(@Param("position") String position);

}
