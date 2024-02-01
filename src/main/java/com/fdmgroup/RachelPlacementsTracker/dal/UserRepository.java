package com.fdmgroup.RachelPlacementsTracker.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.RachelPlacementsTracker.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
