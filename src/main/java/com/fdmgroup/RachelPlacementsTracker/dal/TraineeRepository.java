package com.fdmgroup.RachelPlacementsTracker.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.RachelPlacementsTracker.coreModel.Trainee;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {

}
