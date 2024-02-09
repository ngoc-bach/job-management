package com.fdmgroup.RachelPlacementsTracker.dal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.RachelPlacementsTracker.coreModel.Trainee;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {
	
	@Query("SELECT t FROM Trainee t WHERE t.user.id = :userId")
	Optional <Trainee> findTraineeByUserId(@Param("userId") int userId);
}
