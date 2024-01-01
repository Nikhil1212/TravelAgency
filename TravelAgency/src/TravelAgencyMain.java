import java.io.IOException;
import java.util.Scanner;

import entities.Activity;
import entities.Destination;
import entities.Itinerary;
import entities.Passenger;
import entities.TravelPackage;
import passengerType.GoldPassenger;
import passengerType.PremiumPassenger;
import passengerType.StandardPassenger;
import service.ActivityService;
import service.DestinationService;
import service.ItineraryService;
import service.PassengerService;
import service.TravelPackageService;

public class TravelAgencyMain {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		ActivityService activityService = ActivityService.getInstanceOf();
		PassengerService passengerService = PassengerService.getInstanceOf();
		DestinationService destinationService = DestinationService.getInstanceOf();
		ItineraryService itineraryService = ItineraryService.getInstanceOf();
		TravelPackageService travelPackageService = TravelPackageService.getInstanceOf();
		System.out.println(
				"Welcome to Bangalore Travel Agency. Connecting People. Spreading Happiness. Creating Memories!!");
		System.out.println("Please Select the following options: " + Constants.userRegistration
				+ Constants.additionOfNewActivity + Constants.newItinerary + Constants.newDestination
				+ Constants.updatePassengerDetails + Constants.updateDestinationDetails
				+ Constants.updateItineraryDetails + Constants.upadteTravelPackageDetails
				+ Constants.removeActivityFromDestination + Constants.newTravelPackage + Constants.enrollForActivity);

		while (true) {
			try {
				System.out.println("\n Provide the input in the range 0 - 23.");
				int inputNo = scanner.nextInt();
				switch (inputNo) {
				case 0:
					break;
				case 1:
					getInputForPassenger();

				case 2:
					getInputForActivityEntry();
					break;

				case 3:
					generateNewItinerary();
					break;

				case 4:
					getInputForNewDestination();
					break;

				case 5:
					getNewTravelPackage();
					break;

				case 6:
					passengerEnrollForActivity();
					break;

				case 7:
					addActivityToDestination();
					break;

				case 8:
					removeActivityFromDestination();
					break;

				case 9:
					displayAllDetailsOfTravelPackage();
					break;

				case 10:
					displayItineraryOfTravelPackage();
					break;

				case 11:
					displayIndividualPassengerDetails();
					break;

				case 12: {
					// update the maxSeats for activity.
					System.out.println(Constants.enterActivityId);
					int activityId = scanner.nextInt();
					System.out.println("Enter the max seats for the activity.");
					int maxseats = scanner.nextInt();
					activityService.updateMaxSeatsAvailable(activityId, maxseats);
					break;
				}

				case 13: {
					// update maxSeats for travelPackage

					System.out.println("Enter the TravelPackage id ");
					int travelPackageId = scanner.nextInt();
					System.out.println("Enter the max seats for the TravelPackage.");
					int maxseats = scanner.nextInt();
					activityService.updateMaxSeatsAvailable(travelPackageId, maxseats);
					break;
				}
				case 14: {
					// update destinationId for the activity.

					System.out.println(Constants.enterActivityId);
					int activityId = scanner.nextInt();
					System.out.println("Enter the Destination id.");
					int destinationId = scanner.nextInt();
					activityService.updateDestinationForActivity(activityId, destinationId);

					break;
				}

				case 15: {
					// update the cost for the actiSystem.out.println("Enter the Activity id ");
					System.out.println(Constants.enterActivityId);
					int activityId = scanner.nextInt();
					System.out.println("Enter the activity cost");
					double cost = scanner.nextDouble();
					activityService.updateCost(activityId, cost);
					break;
				}

				case 16: {
					// update description for the activity.
					System.out.println(Constants.enterActivityId);
					int activityId = scanner.nextInt();
					System.out.println("Enter the activity description");
					String description = scanner.nextLine();
					activityService.updateDescription(activityId, description);
					break;
				}

				case 17: {
					// remove destination from itinerary.
					System.out.println("Enter itinerary id : ");
					int itineraryId = scanner.nextInt();
					System.out.println("Enter the destinationId");
					int destinationId = scanner.nextInt();
					itineraryService.removeDestinationFromItinerary(itineraryId, destinationId);
					break;
				}

				case 18: {
					// add destination to itinerary.
					System.out.println("Enter itinerary id : ");
					int itineraryId = scanner.nextInt();
					System.out.println("Enter the destinationId");
					int destinationId = scanner.nextInt();
					itineraryService.addDestinationToItinerary(itineraryId, destinationId);
					break;
				}

				case 19: {
					// update Passenger name
					System.out.println("Enter passenger id : ");
					int passengerId = scanner.nextInt();
					System.out.println("Enter Passenger Name : ");
					String name = scanner.nextLine();
					passengerService.updateName(passengerId, name);
					break;
				}

				case 20: {
					// update passenger mobile number.
					System.out.println("Enter passenger id : ");
					int passengerId = scanner.nextInt();
					System.out.println("Enter Mobile Number: ");
					long mobileNo = scanner.nextLong();
					passengerService.updateMobileNumber(passengerId, mobileNo);
					break;
				}

				case 21: {
					// update passenger's travel package id.
					System.out.println("Enter passenger id : ");
					int passengerId = scanner.nextInt();
					System.out.println("Enter Travel Package Id ");
					int travelPackageId = scanner.nextInt();
					passengerService.updateTravelPacakgeId(passengerId, travelPackageId);
					break;
				}

				case 22: {
					// update travel package name.
					System.out.println("Enter travel Pacakge Id:");
					int travelPackageId = scanner.nextInt();
					System.out.println("Enter package name :");
					String name = scanner.nextLine();
					travelPackageService.updateName(travelPackageId, name);
					break;
				}

				case 23: {
					// update travel package itinerary id.
					System.out.println("Enter travel Pacakge Id:");
					int travelPackageId = scanner.nextInt();
					System.out.println("Enter Itinerary id :");
					int itineraryId = scanner.nextInt();
					travelPackageService.updateTravelPackageItinerary(travelPackageId, itineraryId);
					break;
				}

				default:
					throw new IllegalArgumentException("Invalid input: " + inputNo);
				}

				if (inputNo == 0)
					break;
			} catch (IOException exception) {
				System.out.println(exception.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
	}

	private static void displayAllDetailsOfTravelPackage() throws Exception {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter travel Package id : ");
		int travelPackageId = scanner.nextInt();

		TravelPackageService travelPackageService = TravelPackageService.getInstanceOf();
		travelPackageService.displayIndividualTravelPacakge(travelPackageId);

		scanner.close();
	}

	private static void displayIndividualPassengerDetails() {
		Scanner scanner = new Scanner(System.in);
		int passengerId = scanner.nextInt();
		PassengerService passengerService = PassengerService.getInstanceOf();
		passengerService.printPassengerInfo(passengerId);
		scanner.close();
	}

	private static void displayItineraryOfTravelPackage() throws Exception {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n Enter travel package id:");
		int travelPackageId = scanner.nextInt();

		ItineraryService itineraryService = ItineraryService.getInstanceOf();
		TravelPackageService travelPackageService = TravelPackageService.getInstanceOf();
		int itineraryId = travelPackageService.getTravelPackageById(travelPackageId).getItineraryId();
		itineraryService.displayItinerary(itineraryId);

		scanner.close();

	}

	private static void removeActivityFromDestination() {
		DestinationService destinationService = DestinationService.getInstanceOf();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter ActivityId :");
		int activityId = scanner.nextInt();
		System.out.println("Enter Destination id  : ");
		int destinationId = scanner.nextInt();
		destinationService.removeActivityFromDestination(destinationId, activityId);
		scanner.close();
	}

	private static void addActivityToDestination() {

		DestinationService destinationService = DestinationService.getInstanceOf();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter ActivityId :");
		int activityId = scanner.nextInt();
		System.out.println("Enter Destination id  : ");
		int destinationId = scanner.nextInt();
		destinationService.addActivityToDestination(destinationId, activityId);
		scanner.close();

	}

	private static void passengerEnrollForActivity() {
		Scanner scanner = new Scanner(System.in);
		PassengerService passengerService = PassengerService.getInstanceOf();
		System.out.println(Constants.enterPassengerId);
		int passengerId = scanner.nextInt();
		System.out.println(Constants.enterActivityId);
		int activityId = scanner.nextInt();
		passengerService.addActivityToPassenger(passengerId, activityId);
		scanner.close();
	}

	private static void getNewTravelPackage() throws Exception {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the travel package name");
		String name = scanner.nextLine();

		System.out.println("Enter the max seats for this package");
		int maxSeats = scanner.nextInt();

		// need to discuss about this.

		System.out.println("Enter the Itinerary id or enter -1 to add later.");
		int itineraryId = scanner.nextInt();

		// let's complete tomorrow.
		TravelPackageService packageService = TravelPackageService.getInstanceOf();
		int id = packageService.generateIdForNewTravelPackage();
		TravelPackage travelPackage = new TravelPackage(id, name, maxSeats, itineraryId);
		packageService.addTravelPackageToRepository(travelPackage);
		System.out.println("Successfully added Travel Package with id  " + id + " to the repository.");
		scanner.close();
	}

	private static void getInputForNewDestination() throws Exception {

		DestinationService destinationService = DestinationService.getInstanceOf();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter Destination name : ");
		String name = scanner.nextLine();
		int id = destinationService.generateNewIdForDestination();
		Destination destination = new Destination(name, id);
		destinationService.addDestinationToRepository(destination);
		System.out.println("Successfully added destination to repository.  Destination id generated is : " + id);
		scanner.close();
	}

	private static Itinerary generateNewItinerary() throws Exception {
		ItineraryService itineraryService = ItineraryService.getInstanceOf();
		int id = itineraryService.generateNewIdForItinerary();
		Itinerary itinerary = new Itinerary(id);
		itineraryService.addItineraryToRepository(itinerary);
		System.out.println("New itinerary has been added with id : " + id
				+ ". For future references, add new destinations under this id.");
		return itinerary;
	}

	private static void getInputForActivityEntry() throws Exception {

		Scanner scanner = new Scanner(System.in);
		ActivityService activityService = ActivityService.getInstanceOf();
		System.out.println("Enter Activity Name : ");
		String activityName = scanner.nextLine();
		System.out.println("Enter some description about that :");
		String description = scanner.nextLine();
		System.out.println("Enter Total number of seats available: ");
		int seats = scanner.nextInt();
		System.out.println("Enter the Activity cost : ");
		double cost = scanner.nextDouble();
		System.out
				.println("Enter the destinationId for which this activity is part of: ( Or Enter -1 to update later.)");
		int destinationId = scanner.nextInt();
		int activityId = activityService.generateNewIdForActivity();
		Activity activity = new Activity(activityName, description, cost, seats, destinationId, activityId);
		activityService.addActivityToRepository(activity);
		scanner.close();

	}

	private static void getInputForPassenger() throws Exception {

		PassengerService passengerService = PassengerService.getInstanceOf();

		Scanner scanner = new Scanner(System.in);
		System.out.println(Constants.enterYourName);
		String name = scanner.next();
		System.out.println(Constants.enterMobileNumber);
		long mobileNo = scanner.nextLong();
		System.out.println(Constants.enterBalance);
		double balance = scanner.nextDouble();
		System.out.println(Constants.enterPassengerType);
		int membershipId = scanner.nextInt();
		System.out.println("Enter the Travel package id (-1 if you want to update later.)");
		int travelPacakgeId = scanner.nextInt();
		int passengerId = passengerService.generateNewIdForPassenger();
		Passenger passenger = generatePassengerInstance(membershipId, name, mobileNo, balance, passengerId,
				travelPacakgeId);
		passengerService.addPassengerToRepository(passenger);
		System.out.println("Successfully added Passenger information. Use the passeenger id : " + passenger.getId()
				+ " for your reference.");

		scanner.close();
	}

	private static Passenger generatePassengerInstance(int membershipId, String name, long mobileNo, double balance,
			int passengerId, int travelPackageId) {

		if (membershipId == 1)
			return new StandardPassenger(passengerId, name, balance, mobileNo, travelPackageId);
		if (membershipId == 2)
			return new PremiumPassenger(passengerId, name, balance, mobileNo, travelPackageId);

		return new GoldPassenger(passengerId, name, balance, mobileNo, travelPackageId);

	}

}
