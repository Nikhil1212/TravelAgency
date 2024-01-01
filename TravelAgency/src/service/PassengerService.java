package service;

import java.util.List;
import java.util.Map;

import entities.Activity;
import entities.Destination;
import entities.Itinerary;
import entities.Passenger;
import entities.TravelPackage;
import repository.ActivityRepository;
import repository.DestinationRepository;
import repository.PassengerRepository;

public class PassengerService {

	private static PassengerService passgengerServiceObj = null;

	private PassengerService() {
		
	}

	public static PassengerService getInstanceOf() {
		if (passgengerServiceObj == null)
			passgengerServiceObj = new PassengerService();
		return passgengerServiceObj;
	}
	
	public void addPassengerToRepository(Passenger passenger) {
		try {
			Map map = getPassengerMap();
			int passengerId = passenger.getId();
			if(map.containsKey(passengerId))
			{
				throw new Exception("Passenger Id already exists. Unable to add Passenger details to repository.");
			}
			else
			{
				map.put(passengerId, passenger);
			}
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
	}

	public Map getPassengerMap() throws Exception {
		PassengerRepository passengerRepository = PassengerRepository.getInstanceOf();
		Map map = passengerRepository.getPassengerMap();
		if (map == null)
			throw new Exception("Initialization error. Passenger Map is null");
		return map;
	}

	public Passenger getPassengerById(int id) throws Exception {

		Map map = getPassengerMap();

		Passenger passenger = (Passenger) map.get(id);

		if (passenger == null)
			throw new Exception("Passenger not found.");

		return passenger;

	}

	public void printPassengerInfo(int passengerId) {

		try {
			Passenger passenger = getPassengerById(passengerId);
			ActivityService activityService = ActivityService.getInstanceOf();
			System.out.println("Passenger name : " + passenger.getName() + "mobile number : "
					+ passenger.getMobileNumber() + ", Balance : " + passenger.getBalance());
			for (int i = 0; i < passenger.getListActivityId().size(); i++) {
				
				Activity act = activityService.getActivity(passenger.getListActivityId().get(i));

				DestinationRepository destinationRepository = DestinationRepository.getInstanceOf();
				Destination destination = destinationRepository.getDestinationMap().get(act.getDestinationId());

				System.out.println(act.getName() + ", Destination : " + destination.getName() + ", cost:  "
						+ passenger.getDiscountedPriceForActivity(act.getCost()));
			}

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}

	public void addActivityToPassenger(int passengerId, int activityId) {

		Passenger passenger;
		ActivityService activityService = ActivityService.getInstanceOf();
		try {
			passenger = getPassengerById(passengerId);

			Activity activity = activityService.getActivity(activityId);
			
			if(!isActivityPartOfTravelPackage(passenger.getTravelPacakgeId(), activityId)) {
				throw new Exception ("This activity is not the part of the package.");
			}

			int seatsAvailable = activity.getSeatsAvailable();

			if (seatsAvailable > 0) {
				double activityPrice = passenger.getDiscountedPriceForActivity(activity.getCost());
				if (isSufficientBalance(passenger.getBalance(), activityPrice)) {

					activityService.updateActivityCapacity(activity.getActivityId(), seatsAvailable - 1);
					updateBalance(passenger, activityPrice);
					passenger.getListActivityId().add(activityId);
				}
			} 
			else
				throw new Exception("No seats available for the activity: " + activity.getName());
		}
		
		catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

	}

	private boolean isActivityPartOfTravelPackage(int travelPackageId, int activityId) throws Exception {
		if(travelPackageId == -1)
		{
			throw new Exception("Passenger is not the part of any travel package.");
		}
		TravelPackageService travelPackageService = TravelPackageService.getInstanceOf();
		TravelPackage travelPackage = travelPackageService.getTravelPackageById(travelPackageId);
		
		ItineraryService itineraryService = 	ItineraryService.getInstanceOf();
		
		Itinerary itinerary = itineraryService.getItineraryById(travelPackage.getItineraryId());
		
		List<Integer> listDestinationIds = itinerary.getListDestinations();
		if (listDestinationIds !=null) {
			DestinationService destinationService = DestinationService.getInstanceOf();
			
			for(int i =0; i<listDestinationIds.size();i++) {
				Destination destination =	destinationService.getDestinationById(listDestinationIds.get(i));
				List<Integer> listActivityIds = destination.getListActivity();
				if(listActivityIds!=null) {
					for(int j =0; j < listActivityIds.size();j++) {
						if(activityId == listActivityIds.get(i))
							return true;
					}
				}
				else
					throw new Exception ("No activities found for the given travel package.");
				
			}
		}
		else
			throw new Exception("No destinations exist for the given travel package id.");
		return false;
	}

	private boolean isSufficientBalance(double balance, double activityPrice) {
		if (activityPrice > balance)
			return false;
		return true;
	}

	private void updateBalance(Passenger passenger, double activityPrice) {
		passenger.decrementBalance(activityPrice);
	}

	public int generateNewIdForPassenger() throws Exception {

		Map map = getPassengerMap();
		int newId = map.size()+1;
		
		if(!map.containsKey(newId))
			return newId;
		
		while(true) {
			newId++;
			if(!map.containsKey(newId))
				return newId;
		}
	}

	public void updateName(int passengerId, String name) throws Exception {
		if(name.length() ==0)
			throw new Exception ("Name cannot be empty.");
		PassengerService passengerService = PassengerService.getInstanceOf();
		Passenger passenger =	passengerService.getPassengerById(passengerId);
		passenger.setName(name);
	}

	public void updateMobileNumber(int passengerId, long mobileNo) throws Exception {

		if(mobileNo < 0)
			throw new Exception("Invalid mobile number");
		PassengerService passengerService = PassengerService.getInstanceOf();
		Passenger passenger =	passengerService.getPassengerById(passengerId);
		passenger.setMobileNumber(mobileNo);
		
		
	}

	public void updateTravelPacakgeId(int passengerId, int travelPackageId) throws Exception {

		TravelPackageService packageService = TravelPackageService.getInstanceOf();
		TravelPackage travelPackage =	packageService.getTravelPackageById(travelPackageId);
		Passenger passenger = getPassengerById(passengerId);
		if (passenger.getTravelPacakgeId() != -1)
			throw new Exception("Passenger is already enrolled to a different travel Package.");
		if(travelPackage.getSeatsAvailable() > 0) {
			packageService.addPassengerToTravelPackage(passengerId, travelPackageId);
			
		}
	}

}
