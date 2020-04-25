package com.example.springrmiclient.model;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="expense")
public class Expense {
	@Override
	public String toString() {
		return "Expense [id=" + id + ", expensedate=" + expensedate + ", description=" + description + ", location="
				+ location + ", category=" + category + ", user=" + user + "]";
	}

	public Expense(Long id, Date expensedate, String description, String location, Category category, User user) {
		super();
		this.id = id;
		this.expensedate = expensedate;
		this.description = description;
		this.location = location;
		this.category = category;
		this.user = user;
	}

	@Id
	@GeneratedValue
	private Long id;
	
	private Date expensedate;
	
	private String description;
	
	private String location;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getExpensedate() {
		return expensedate;
	}

	public void setExpensedate(Date expensedate) {
		this.expensedate = expensedate;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
