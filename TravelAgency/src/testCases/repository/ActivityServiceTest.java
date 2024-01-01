package testCases.repository;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Activity;
import repository.ActivityRepository;
import service.ActivityService;

public class ActivityServiceTest {

	@Test
	public void testUpdateActivityCapacity() {
		Activity activity = new Activity("SightSeeing", "Enjoy the nature", 50.0, 100, 1, 1);
		int newValue = 42;

		activity.setSeatsAvailable(newValue);

		assertEquals(newValue, activity.getSeatsAvailable());
	}

	@Test
	public void testGetTotalSeatsBooked() {
		Activity activity = new Activity("SightSeeing", "Enjoy the nature", 50.0, 100, 1, 1);

		// Set the initial values for seats available and max capacity
		activity.setSeatsAvailable(10);
		activity.setMaxCapacity(20);

		// Create an instance of the class containing the getTotalSeatsBooked method

		ActivityService activityService = ActivityService.getInstanceOf();
		// Call the method and check the result
		int result = activityService.getTotalSeatsBooked(activity);

		// Assert the expected result based on the initial values
		assertEquals(10, result);
	}

	@Test
	public void testGetTotalSeatsBookedWithInavlidSeatsSet() {
		Activity activity = new Activity("SightSeeing", "Enjoy the nature", 50.0, 10, 1, 1);

		// Initially setting the maximum capacity to 10.
		activity.setSeatsAvailable(25);
		// Now, setting the current seats available to 25.

		ActivityService activityService = ActivityService.getInstanceOf();
		int result = activityService.getTotalSeatsBooked(activity);

		assertEquals(0, result);
	}

	@Test
	public void testGetTotalSeatsBookedWhenMaximumCapacityChanged() {
		// Create an instance of the Activity class (assuming such a class exists)
		Activity activity = new Activity("SightSeeing", "Enjoy the nature", 50.0, 20, 1, 1);

		// Set the initial values for seats available and max capacity to an invalid
		// state
		activity.setMaxCapacity(10);

		ActivityService activityService = ActivityService.getInstanceOf();
		// Call the method and check the result
		int result = activityService.getTotalSeatsBooked(activity);
		// Assert the expected result when an exception is caught
		assertEquals(0, result);
	}

	@Test
	public void testUpdateMaxSeatsAvailableSuccess() {
		// Create an instance of the Activity class (assuming such a class exists)
		int activityId = 10;
		Activity activity = new Activity("SightSeeing", "Enjoy the nature", 50.0, 20, 1, activityId);
		activity.setSeatsAvailable(10);
		// add the activity to the repository.
		ActivityRepository activityRepository = ActivityRepository.getInstanceOf();
		activityRepository.getActivityMap().put(activityId, activity);

		try {
			// Call the method and update max seats

			// Assert the expected result based on the updated values
			ActivityService activityService = ActivityService.getInstanceOf();
			activityService.updateMaxSeatsAvailable(activityId, 30);

			int x = activity.getMaxCapacity();
			int y = activity.getSeatsAvailable();
			assertEquals(30, x);
			assertEquals(30, y);
		} catch (Exception exception) {
			fail("Unexpected exception: " + exception.getMessage());
		}

		// Set initial values for seats available and max capacity
	}

	@Test
	public void testGetTotalBookingsForInvalidCurrentSeats() {
		/**
		 * Setting seatsAvailable more than maxCapacity and some bookings already done.
		 * Needs to be completed.
		 */
		assertEquals(0, 0);
	}
	
	 @Test
	    public void testUpdateMaxSeatsAvailableWithInvalidActivityId() {
	        // Create an instance of the class containing the updateMaxSeatsAvailable method
		 
		 	int activityId = -1;
			Activity activity = new Activity("Sightseeing", "City tour", 30.0, 25, 2, activityId);
			
			ActivityService activityService = ActivityService.getInstanceOf();


	        try {
	            // Call the method with an invalid activity ID
				activityService.updateMaxSeatsAvailable(activityId, 30);

	            // Fail the test if no exception is thrown when expected
	            fail("Expected exception not thrown");
	        } catch (Exception exception) {
	            // Assert the expected exception message
	            assertEquals("Invalid Activity id.", exception.getMessage());
	        }
	    }

//	 @Test
//	 public void testPrintPassengerInfo() {
//	 Passenger samplePassenger = new StandardPassenger(1,"John Doe",100.0,PassengerMembership.Standard,1234567890);
//     // Create a sample activity
//     Activity sampleActivity = new Activity("SightSeeing","Enjoy the nature",50.0,100,1,1);
//     // Create a sample destination
//     Destination sampleDestination = new Destination("City Park",1);
//     
//     // let's complete tomorrow.
//
//	 }

}
