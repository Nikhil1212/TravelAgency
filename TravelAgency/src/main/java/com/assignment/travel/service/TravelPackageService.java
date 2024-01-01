package com.assignment.travel.service;

import java.util.List;
import java.util.Map;

import com.assignment.travel.entities.Activity;
import com.assignment.travel.entities.Destination;
import com.assignment.travel.entities.Itinerary;
import com.assignment.travel.entities.TravelPackage;
import com.assignment.travel.entities.passenger.Passenger;
import com.assignment.travel.repository.ItineraryRepository;
import com.assignment.travel.repository.PassengerRepository;
import com.assignment.travel.repository.TravelPackageRepository;

public class TravelPackageService {

	private static TravelPackageService travelPackageServiceObj = null;

	private TravelPackageService() {

	}

	public static TravelPackageService getInstanceOf() {
		if (travelPackageServiceObj == null)
			travelPackageServiceObj = new TravelPackageService();
		return travelPackageServiceObj;
	}

	public void addPassengerToTravelPackage(int passengerId, int travelPackageId) {

		try {
			PassengerService passengerService = PassengerService.getInstanceOf();

			Passenger passenger = passengerService.getPassengerById(passengerId);

			TravelPackageService travelPacakgeService = TravelPackageService.getInstanceOf();

			TravelPackage travelPackage = travelPacakgeService.getTravelPackageById(travelPackageId);

			if (travelPackage.getListPassengers() != null) {
				if (travelPackage.getMaxCapacity() > travelPackage.getListPassengers().size()) {
					travelPackage.getListPassengers().add(passengerId);
					int curSize = travelPackage.getSeatsAvailable();
					travelPackage.setSeatsAvailable(curSize - 1);
					passenger.setTravelPacakgeId(travelPackageId);
				} else
					throw new Exception("Seats are full.");
			} else {
				throw new Exception("ListPassengers attribute of TravelPackage is null");
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

	}

	public int generateIdForNewTravelPackage() throws Exception {

		TravelPackageRepository travelPackageRepository = TravelPackageRepository.getInstanceOf();

		Map map = getTravelPackageMap();

		int newId = map.size() + 1;

		if (!map.containsKey(newId))
			return newId;

		while (true) {
			newId++;
			if (!map.containsKey(newId))
				return newId;
		}
	}

	public void addTravelPackageToRepository(TravelPackage travelPackage) {
		try {
			Map map = getTravelPackageMap();
			int packageId = travelPackage.getPackageId();
			if (map.containsKey(packageId)) {
				throw new Exception(
						"Travel Package Id already exists. Unable to add Travel Package details to repository.");
			} else {
				map.put(packageId, travelPackage);
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}

	public Map getTravelPackageMap() throws Exception {
		TravelPackageRepository travelPackageRepository = TravelPackageRepository.getInstanceOf();
		Map map = travelPackageRepository.getTravelPackageMap();
		if (map == null)
			throw new Exception("Initialization error. Travel Package Map is null");
		return map;
	}

	public TravelPackage getTravelPackageById(int id) throws Exception {

		Map map = getTravelPackageMap();

		TravelPackage travelPackage = (TravelPackage) map.get(id);

		if (travelPackage == null)
			throw new Exception("Travel Package does not exist for the given id : " + id);

		return travelPackage;

	}

	private int getTotalSeatsBooked(TravelPackage travelPackage) {

		int curSeats = travelPackage.getSeatsAvailable();
		int curMaxSeats = travelPackage.getMaxCapacity();
		int totalSeatsBooked = curMaxSeats - curSeats;
		try {
			if (curMaxSeats < curSeats) {
				throw new Exception("Seats available cannot be more than maxCapacity");
			}
		} catch (Exception exception) {
			return 0;
		}
		return totalSeatsBooked;
	}

	public void printTravelPackageInfo() {
		TravelPackageRepository travelPackageRepository = TravelPackageRepository.getInstanceOf();
		try {
			Map map = getTravelPackageMap();
			Object[] travelPackageIds = map.entrySet().toArray();
			for (int i = 0; i < travelPackageIds.length; i++) {
				try {
					displayIndividualTravelPacakge((int) travelPackageIds[i]);
				} catch (Exception exception) {
					System.out.println(exception.getMessage());
				}

			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}

	public void displayIndividualTravelPacakge(int id) throws Exception {
		TravelPackage travelPackage = getTravelPackageById((int) id);
		System.out.println(travelPackage.getName() + " : " + travelPackage.getPackageId()
				+ ", Passenger Maximum capacity : " + travelPackage.getMaxCapacity() + " current seats available : "
				+ travelPackage.getSeatsAvailable() + ", total seats booked is : "
				+ getTotalSeatsBooked(travelPackage));
		List<Integer> passengerList = travelPackage.getListPassengers();
		if (passengerList != null) {
			PassengerService passengerService = PassengerService.getInstanceOf();
			for (int j = 0; j < passengerList.size(); j++) {
				int passengerId = passengerList.get(j);
				passengerService.printPassengerInfo(passengerId);
			}
		} else {
			throw new Exception("Passenger List is null");
		}

		int itineraryId = travelPackage.getItineraryId();

		ItineraryService itineraryService = ItineraryService.getInstanceOf();
		DestinationService destinationService = DestinationService.getInstanceOf();

		if (itineraryId > 0) {
			Itinerary itinerary = itineraryService.getItineraryById(itineraryId);
			if (itinerary.getListDestinations() != null) {
				List<Integer> destinations = itinerary.getListDestinations();
				for (int k = 0; k < destinations.size(); k++) {
					int destinationId = destinations.get(k);
					destinationService.printDestinationInfo(destinationId);
				}
			} else
				throw new Exception("Destination list is null.");
		} else {
			throw new Exception("Itineary id cannot be negative.");
		}

	}

	public void updateName(int travelPackageId, String name) throws Exception {
		if (name.isEmpty())
			throw new Exception("Name cannot be empty.");
		TravelPackage travelPackage = getTravelPackageById(travelPackageId);
		travelPackage.setName(name);
	}

	public void updateTravelPackageItinerary(int travelPackageId, int itineraryId) throws Exception {

		TravelPackage travelPackage = getTravelPackageById(travelPackageId);
		if (travelPackage.getItineraryId() != -1) {
			throw new Exception("Already travel package has some itinerary.");
		}
		ItineraryService itineraryService = ItineraryService.getInstanceOf();
		Itinerary itinerary = itineraryService.getItineraryById(itineraryId);
		travelPackage.setItineraryId(itineraryId);
	}

}
