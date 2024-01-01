package service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import entities.Activity;
import entities.Destination;
import entities.Passenger;
import repository.ActivityRepository;
import repository.DestinationRepository;
import repository.PassengerRepository;

public class ActivityService {
	
	

	private static ActivityService activityServiceObj = null;

	private ActivityService() {

	}
	
	public void addActivityToRepository(Activity activity) {
		try {
			Map map = getActivityMap();
			int activityId = activity.getActivityId();
			if(map.containsKey(activityId))
			{
				throw new Exception("Activity Id already exists. Unable to add new Activity to repository.");
			}
			else
			{
				map.put(activityId, activity);
			}
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	public static ActivityService getInstanceOf() {
		if(activityServiceObj == null)
			activityServiceObj = new ActivityService();
		return activityServiceObj;
	}
	
	public Map getActivityMap() throws Exception {
		ActivityRepository activityRepository = ActivityRepository.getInstanceOf();
		Map map = activityRepository.getActivityMap();
		if(map == null)
			throw new Exception ("Initialization error. Activity Map is null");
		return map;
	}
	
	public Activity getActivity(int id) throws Exception {
		
		Map map = getActivityMap();
		
		Activity activity = (Activity) map.get(id);
		
		if(activity == null)
			throw new Exception ("Activity not found.");
		
		return activity;
	}
	
	
	
	public void updateActivityCapacity(int activityId, int value) throws Exception {
		try
		{
			Activity activity = getActivity(activityId);
			activity.setSeatsAvailable(value);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void printActivityInfo(int activityId) {
		
		try
		{
			Activity activity = getActivity(activityId);
			DestinationService destinationService = DestinationService.getInstanceOf();
			Destination destination = destinationService.getDestinationById(activity.getDestinationId());
			System.out.println("Activity id : "+activity.getActivityId()+" Name : "+activity.getName()+" "+activity.getDescription()+", Max seats : "+ activity.getMaxCapacity() + "current seats available "+activity.getSeatsAvailable()+" "
			+"cost : "+activity.getCost() + "Destination : "+destination.getName());
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void printBookingsAvailableForActivity() throws Exception {
		
		ActivityRepository activityRepository = ActivityRepository.getInstanceOf();
		Map activityMap = activityRepository.getActivityMap();
		if(activityMap !=null)
		{
			Object activityIdSet[] = activityMap.keySet().toArray();
			
			for(int i =0; i< activityIdSet.length;i++) {
				
				int activityId = (int) activityIdSet[i];
				
				Activity activity = (Activity) activityMap.get(activityId);
				if(activity !=null) {
					if(activity.getSeatsAvailable() > 0) {
						System.out.println(activity.getName()+" , "+activity.getSeatsAvailable());
					}
				}
			}
		}
		else
		{
			throw new Exception("Initialization Error!!. Activity Map is null.");
		}

	}
	
	public void updateMaxSeatsAvailable(int activityId, int maxSeats) throws Exception {
		
		if(activityId <0) {
			throw new Exception("Invalid Activity id.");
		}
		
		if(maxSeats < 0)
		{
			throw new Exception("Invalid value for maxSeats");
		}
		
		ActivityRepository activityRepository = ActivityRepository.getInstanceOf();
		Activity activity = activityRepository.getActivityMap().get(activityId);
		if(activity!= null)
		{
			int totalSeatsBooked = getTotalSeatsBooked(activity);
			if(totalSeatsBooked > maxSeats)
			{
				throw new Exception("Maximum seats cannot be more than seats already booked. Already "+totalSeatsBooked+" bookings done for "+activity.getName()+". So, cannot set max seats to "+maxSeats);
			}
			else
			{
				activity.setMaxCapacity(maxSeats);
				activity.setSeatsAvailable(maxSeats - totalSeatsBooked);
			}
		}
		else
			throw new Exception("Activity not found.");
	}

	public int getTotalSeatsBooked(Activity activity) {
		/**
		 * Total booking
		 */

		int curSeats = activity.getSeatsAvailable();
		int curMaxSeats = activity.getMaxCapacity();
		int totalSeatsBooked = curMaxSeats - curSeats; 
		try {
			if(curMaxSeats < curSeats)
			{
				throw new Exception("Seats available cannot be more than maxCapacity");
			}
		}
		catch(Exception exception) {
			return 0;
		}
		return totalSeatsBooked;
	}
	
	public int generateNewIdForActivity() throws Exception {
		return getActivityMap().size()+1;
	}

	public void updateDestinationForActivity(int activityId, int destinationId) throws Exception {
		Activity activity = getActivity(activityId);
		int curDestinationId = activity.getDestinationId();
		DestinationService destinationService = DestinationService.getInstanceOf();
		Destination destination = destinationService.getDestinationById(curDestinationId);
		List<Integer> activityIdList = destination.getListActivity();
		int index = activityIdList.indexOf(activityId);
		if(index == -1)
			throw new Exception ("Error. Unable to remove the activity id from the old destination.");
		destination.removeActivity(index);
		activity.setDestinationId(destinationId);
	}

	public void updateCost(int activityId, double cost) throws Exception {
		if(cost < 0)
			throw new Exception ("Cost is negative.");
		Activity activity = getActivity(activityId);
		activity.setCost(cost);
	}

	public void updateDescription(int activityId, String description) throws Exception {
		if(description.length() ==0)
			throw new Exception ("Description is empty.");
		Activity activity = getActivity(activityId);
		activity.setDescription(description);		
	}
	
	
}

