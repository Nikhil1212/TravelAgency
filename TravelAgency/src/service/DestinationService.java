package service;

import java.util.List;
import java.util.Map;

import entities.Activity;
import entities.Destination;
import repository.DestinationRepository;

public class DestinationService {

	
	private static DestinationService destinationServiceObj = null;

	private DestinationService() {

	}
	
	public static DestinationService getInstanceOf() {
		if(destinationServiceObj == null)
			destinationServiceObj = new DestinationService();
		return destinationServiceObj;
	}
	
	public void addDestinationToRepository(Destination destination) {
		try {
			Map map = getDestinationMap();
			int destinationId = destination.getDestinationID();
			if(map.containsKey(destinationId))
			{
				throw new Exception("Destination Id already exists. Unable to add new Destination to repository.");
			}
			else
			{
				map.put(destinationId, destination);
			}
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	
	public int generateNewIdForDestination() throws Exception {
		return getDestinationMap().size()+1;
	}
	
	public Map getDestinationMap() throws Exception {
		
		DestinationRepository destinationRepository = DestinationRepository.getInstanceOf();
		Map map = destinationRepository.getDestinationMap();
		if(map == null)
			throw new Exception ("Initialization error. Destination Map is null");
		return map;
	}
	
	public Destination getDestinationById(int id) throws Exception {
		
		if(id < 0) {
			throw new Exception ("Id cannot be negative.");
		}
		Map map = getDestinationMap();
		
		Destination destination = (Destination) map.get(id);
		
		if(destination == null)
			throw new Exception ("Destination not found.");
		
		return destination;
	}
	
	public void addActivityToDestination(int destinationId, int activityId) {
		try {
			if(isActivityPartOfDestination(activityId)) {
				throw new Exception("Activity already part of some other destination:");
			}
			Destination destination = getDestinationById(destinationId);
			List<Integer> listActivityId = destination.getListActivity();
			if(listActivityId!=null) {
				for(int i =0; i < listActivityId.size(); i++) {
					if(activityId == listActivityId.get(i))
						throw new Exception ("Duplicate Activity. Activity Id already exists.");
				}
			}
			
			destination.addActivityById(activityId);
		
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean isActivityPartOfDestination(int activityId) throws Exception {
		ActivityService activityService = ActivityService.getInstanceOf();
		int destinationId = activityService.getActivity(activityId).getDestinationId();
		return false;
	}

	public void removeActivityFromDestination(int destinationId, int activityId) {
		try {
			Destination destination = getDestinationById(destinationId);
			List<Integer> listActivityIds = destination.getListActivity();
			if(listActivityIds!=null) {
				int index = listActivityIds.indexOf(activityId);
				if(index == -1)
					throw new Exception ("Activity does not exist for the destination id : "+destinationId);
				listActivityIds.remove(index);
			}		
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void printDestinationInfo(int destinationId) {
		try {
			Destination destination = getDestinationById(destinationId);
			System.out.println("Destination : "+destination.getName());
			
			List<Integer> activityList = destination.getListActivity();
			if(activityList !=null) {
				ActivityService activityService = ActivityService.getInstanceOf();
				for(int i =0; i < activityList.size();i++) {
					int activityId = activityList.get(i);
					activityService.printActivityInfo(activityId);
				}
			}
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
	}

}
