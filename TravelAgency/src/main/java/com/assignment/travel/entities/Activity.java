package com.assignment.travel.entities;

/**
 * Activity is unique w.r.t each destination. Like one activity cannot be present at more than one destination.
 */
public class Activity {
	
	private String name;
	private String description;
	private int destinationId;
	private int activityId;

	private double cost;
	private int seatsAvailable;	// total number of seats currently available.
	private int maxCapacity; // threshold max no. of passengers that can enroll for this activity.
	
	

	public int getActivityId() {
		return activityId;
	}
	
	
	public Activity(String name, String description, double cost, int capacity, int destinationId, int activityId) {
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.seatsAvailable = capacity;
		this.destinationId = destinationId;
		this.maxCapacity = capacity;
		this.activityId = activityId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	
	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	
	public int getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}	
	
}
