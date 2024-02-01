package com.fdmgroup.RachelPlacementsTracker.coreModel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Job {
	@Id
	@GeneratedValue
	private int id;
	private String position;
	private String company;
	private String description;
	private String location;
	private String status;

	@Transient
	private boolean hasApplied;
	
	@Transient
	private boolean isEditable;

	public boolean isHasApplied() {
		return hasApplied;
	}

	public void setHasApplied(boolean hasApplied) {
		this.hasApplied = hasApplied;
	}

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}
	
	@ManyToOne
	@JoinColumn(name = "FK_ACCOUNTMANAGER_ID")
	@JsonIgnore
	private AccountManager accountManager;

	@ManyToMany(mappedBy = "jobs")
	@JsonBackReference
	private List<Trainee> trainees;

	public List<Trainee> getTrainees() {
		return trainees;
	}

	public void setTrainees(List<Trainee> trainees) {
		this.trainees = trainees;
	}

	public AccountManager getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	public Job() {
		super();
	}

	public Job(String position, String company, String description, String location, String status) {
		super();
		this.position = position;
		this.company = company;
		this.description = description;
		this.location = location;
		this.status = status;
	}

	public Job(int id, String position, String company, String description, String location, String status) {
		super();
		this.id = id;
		this.position = position;
		this.company = company;
		this.description = description;
		this.location = location;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", position=" + position + ", company=" + company + ", description=" + description
				+ ", location=" + location + ", status=" + status + ", accountManager=" + accountManager + "]";
	}

}