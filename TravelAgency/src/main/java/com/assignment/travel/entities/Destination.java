package com.assignment.travel.entities;

import java.util.ArrayList;
import java.util.List;

public class Destination {
	int destinationID;
	String name;
	List<Integer> listActivityId;
	
	public Destination(String name, int id) {
		this.name = name;
		this.listActivityId = new ArrayList<Integer>();
		this.destinationID = id;
	}

	public int getDestinationID() {
		return destinationID;
	}
	public void setDestinationID(int destinationID) {
		this.destinationID = destinationID;
	}
	public List<Integer> getListActivity() {
		return listActivityId;
	}
	public void setListActivity(List<Integer> listActivityId) {
		this.listActivityId = listActivityId;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addActivityById(int activityId) {
		listActivityId.add(activityId);
	}
	
	public void removeActivity(int index) {
		listActivityId.remove(index);
	}
	

}
