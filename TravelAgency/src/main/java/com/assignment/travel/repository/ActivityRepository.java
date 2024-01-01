package com.assignment.travel.repository;

import java.util.HashMap;
import java.util.Map;

import com.assignment.travel.entities.Activity;
import com.assignment.travel.entities.Destination;

public class ActivityRepository {
	
	private static ActivityRepository activityRepository = null;

	private Map<Integer, Activity> activityMap;
	
	
	private ActivityRepository() {
		activityMap = new HashMap<Integer, Activity>();
	}
	public static ActivityRepository getInstanceOf() {
		
		if(activityRepository == null) {
			activityRepository = new ActivityRepository();
		}
		return activityRepository;
	}
	 
	
	public Map<Integer, Activity> getActivityMap() {
		return activityMap;
	}

}
