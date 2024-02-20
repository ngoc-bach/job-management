package com.fdmgroup.RachelPlacementsTracker.coreModel;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fdmgroup.RachelPlacementsTracker.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Trainee {
	@Id
	@GeneratedValue
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String location;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_USER_ID")
	private User user;

	@ManyToMany
	@JoinTable(name = "TRAINEE_JOB", joinColumns = @JoinColumn(name = "FK_TRAINEE_ID"), inverseJoinColumns = @JoinColumn(name = "FK_JOB_ID"))
	private List<Job> jobs;

	public Trainee() {
		super();
	}

	public Trainee(String firstName, String lastName, String email, String location, User user) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.location = location;
		this.user = user;
	}

	public Trainee(int id, String firstName, String lastName, String email, String location, User user) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.location = location;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Job> getJobs() {
		if (jobs == null) {
			jobs = new ArrayList<>();
		}
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
}
