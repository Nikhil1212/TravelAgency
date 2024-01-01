package testCases.repository;

import static org.junit.Assert.*;

import org.junit.Test;

import com.assignment.travel.entities.Activity;

public class ActivityEntityTest {

	@Test
	public void testActivityInitialization() {
		// Arrange
		String name = "kayaking";
		String description = "A one must thing to do near dawki river.";
		double cost = 60.0;
		int capacity = 40;
		int destinationId = 1;
		int activityId = 4;

		// Act
		Activity activity = new Activity(name, description, cost, capacity, destinationId, activityId);

		// Assert
		assertEquals(name, activity.getName());
		assertEquals(description, activity.getDescription());
		assertEquals(cost, activity.getCost(), 0.001);
		assertEquals(capacity, activity.getSeatsAvailable());
		assertEquals(destinationId, activity.getDestinationId());
		assertEquals(capacity, activity.getMaxCapacity());
		assertEquals(activityId, activity.getActivityId());

	}

	@Test
	public void testUpdateActivityCapacity() {
		// Arrange
		Activity activity = new Activity("Sightseeing", "City tour", 30.0, 25, 2, 1);
		int curCapacity = 15;

		activity.setSeatsAvailable(curCapacity);

		assertEquals(curCapacity, activity.getSeatsAvailable());
		assertNotEquals(curCapacity, activity.getMaxCapacity());
	}

	@Test
	public void testActivityIdAssignment() {
		// Arrange
		Activity activity = new Activity("Snorkeling", "Explore underwater life", 80.0, 10, 3, 1);

		// Assert
		assertTrue(activity.getActivityId() > 0); // ActivityId should be assigned and positive
	}

	// Add more test cases as needed to cover other methods and scenarios.
	

}
