package com.fdmgroup.RachelPlacementsTracker.coreModel;

import java.util.List;

import com.fdmgroup.RachelPlacementsTracker.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class AccountManager {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_USER_ID")
	private User user;

	// should set jobs here?
	@OneToMany(mappedBy = "accountManager")
	private List<Job> jobs;

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AccountManager() {
		super();
	}

	public AccountManager(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public AccountManager(int id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "AccountManager [id=" + id + ", name=" + name + ", email=" + email + ", user=" + user + "]";
	}

}
