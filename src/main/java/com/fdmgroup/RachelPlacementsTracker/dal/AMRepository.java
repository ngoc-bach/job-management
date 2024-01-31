package com.fdmgroup.RachelPlacementsTracker.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.RachelPlacementsTracker.coreModel.AccountManager;

@Repository
public interface AMRepository extends JpaRepository<AccountManager, Integer> {

}
