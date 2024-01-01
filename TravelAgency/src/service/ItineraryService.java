package service;

import java.util.List;
import java.util.Map;

import entities.Destination;
import entities.Itinerary;
import entities.Passenger;
import repository.ActivityRepository;
import repository.DestinationRepository;
import repository.ItineraryRepository;

public class ItineraryService {
	/**
	 * ItineraryService should be able to perform the operations that is happening on the Itinerary class.
	 */
	

	
	private static ItineraryService itineraryServiceObj = null;

	private ItineraryService() {

	}
	
	public static ItineraryService getInstanceOf() {
		if(itineraryServiceObj == null)
			itineraryServiceObj = new ItineraryService();
		return itineraryServiceObj;
	}
	
	public void addItineraryToRepository(Itinerary itinerary) {
		try {
			Map map = getItineraryMap();
			int itineraryId = itinerary.getItineraryId();
			if(map.containsKey(itineraryId))
			{
				throw new Exception("Itinerary Id already exists. Unable to add Itinerary details to repository.");
			}
			else
			{
				map.put(itineraryId, itinerary);
			}
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	public Map getItineraryMap() throws Exception {		
		ItineraryRepository itineraryRepository = ItineraryRepository.getInstanceOf();
		Map map = itineraryRepository.getItineraryMap();
		if(map == null)
			throw new Exception ("Initialization error. Itinerary Map is null.");
		return map;
	}
	
	public Itinerary getItineraryById (int id) throws Exception {
		if(id <0)
			throw new Exception("Id cannot be negative.");
		Map map = getItineraryMap();
		if(map.containsKey(id)) {
			return (Itinerary) map.get(id);
		}
		throw new Exception ("Could not find the itinerary with id : "+id);
	}

	
	public void addDestinationToItinerary(int itineraryId, int destinationId) {
		try
		{
			Itinerary itinerary = getItineraryById(itineraryId);
			DestinationService destinationService = DestinationService.getInstanceOf();
			List<Integer> list =	itinerary.getListDestinations();
			if(list.contains(destinationId)) {
				throw new Exception ("Duplicate Destination. Id :"+destinationId+" already exists.");
			}
			Destination destination =	destinationService.getDestinationById(destinationId);
			itinerary.addDestination(destinationId);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void displayItinerary(int itineraryId) {
		try
		{
			Itinerary itinerary = getItineraryById(itineraryId);
			DestinationService destinationService = DestinationService.getInstanceOf();
			if(itinerary.getListDestinations()!= null)
			{
				List<Integer> list = itinerary.getListDestinations();
				for(int i=0; i < list.size();i++) {
					destinationService.printDestinationInfo(list.get(i));
				}
			}
			else
				throw new Exception("Destination list is null for the given itinerary.");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

	
	public int generateNewIdForItinerary() throws Exception {
		Map map = getItineraryMap();
		return map.size()+1;
	}

	public void removeDestinationFromItinerary(int itineraryId, int destinationId) throws Exception {
		if(destinationId <0)
			throw new Exception ("Destination id cannot be negative");
		Itinerary itinerary = getItineraryById(itineraryId);
		List <Integer> list = itinerary.getListDestinations();
		int index = list.indexOf(destinationId);
		if(index == -1) {
			throw new Exception("Destination ("+destinationId+") not found.");
		}
		itinerary.removeDestinationById(index);
	}
	
	
	
	/**
	 * Expectation is that from hashMap we will be getting 
	 * @param d
	 */

}
