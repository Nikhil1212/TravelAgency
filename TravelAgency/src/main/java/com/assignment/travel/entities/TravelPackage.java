package com.assignment.travel.entities;

import java.util.ArrayList;
import java.util.List;

public class TravelPackage {
	
	private int packageId;
	private String name;
	private int seatsAvailable;
	private int itineraryId;
	private List<Integer> listPassengers;
	private int maxCapacity;
	
	public TravelPackage(int packageId, String name, int capacity, int itineraryId) {
		this.name = name;
		this.seatsAvailable = capacity;
		this.itineraryId = itineraryId;
		this.listPassengers = new ArrayList<Integer>(); // storing the passenger ids.
		this.maxCapacity = capacity;
		this.packageId = packageId;
	}
	
	
	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getPackageId() {
		return packageId;
	}

	public int getItineraryId() {
		return itineraryId;
	}
	
	public void setItineraryId(int id) {
		 this.itineraryId = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	
	public void setSeatsAvailable(int capacity) {
		this.seatsAvailable = capacity;
	}

	public List<Integer> getListPassengers() {
		return listPassengers;
	}
	
	public void setListPassengers(List<Integer> listPassengers) {
		this.listPassengers = listPassengers;
	}

}
