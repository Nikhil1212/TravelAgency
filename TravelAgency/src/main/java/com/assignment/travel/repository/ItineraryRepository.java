package com.assignment.travel.repository;

import java.util.HashMap;
import java.util.Map;

import com.assignment.travel.entities.Itinerary;

public class ItineraryRepository {
	
	private Map<Integer, Itinerary> itineraryMap;

	private static ItineraryRepository itineraryRepository = null;

	
	public Map<Integer, Itinerary> getItineraryMap() {
		return itineraryMap;
	}
	
	
	private ItineraryRepository() {
		itineraryMap = new HashMap <Integer, Itinerary> ();
	}
	
	public static ItineraryRepository getInstanceOf() {
		
		if(itineraryRepository == null) {
			itineraryRepository = new ItineraryRepository();
		}
		return itineraryRepository;
	}

}
