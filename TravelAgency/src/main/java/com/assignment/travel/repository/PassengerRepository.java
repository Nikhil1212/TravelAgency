package com.assignment.travel.repository;

import java.util.HashMap;
import java.util.Map;

import com.assignment.travel.entities.passenger.Passenger;

/**
 *  1. Balance update (decrement)
// 	2. Activity enrollment.
 * 	3. Itinerary add.
 * 	4. Update the hashmap.
 */
public class PassengerRepository {
	
	private static PassengerRepository passengerRepository = null;

	
	private Map <Integer, Passenger> passengerMap; // Idea: w.r.t id, fetch the passenger object. Key is passenger id.
	
	
	public Map<Integer, Passenger> getPassengerMap() {
		return passengerMap;
	}
	
	
	private PassengerRepository() {
		passengerMap = new HashMap <Integer, Passenger> ();
	}
	
	public static PassengerRepository getInstanceOf() {
		
		if(passengerRepository == null) {
			passengerRepository = new PassengerRepository();
		}
		return passengerRepository;
	}
		
	
	// How we can further improve?
	public void addPassengerToMap(Passenger passenger) {
		passengerMap.put(passenger.getPassengerId(), passenger);
	}	
	
}
