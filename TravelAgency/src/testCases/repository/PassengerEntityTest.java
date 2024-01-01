package testCases.repository;


import entities.Passenger;
import passengerType.StandardPassenger;

import org.junit.Test;
import static org.junit.Assert.*;

public class PassengerEntityTest {

    @Test
    public void testGetDiscountedPriceForActivity() {
    	
    	
        // Arrange
        int passengerId = 1;
        String name = "Sachin Singh";
        double balance = 100.0;
        long mobileNumber = 1234567890;

        Passenger passenger = new PassengerImpl(passengerId, name, balance, mobileNumber);

        double discountPercentage = 10.0;
        double activityCost = 50.0;
        double expectedDiscountedPrice = activityCost - (activityCost * (discountPercentage / 100));

        double actualDiscountedPrice = passenger.getDiscountedPriceForActivity(activityCost);

        assertEquals(expectedDiscountedPrice, actualDiscountedPrice, 0.001); 
    }

    
    
    
    @Test
    public void testDecrementBalance() {

    	int passengerId = 1;
        String name = "Palak";
        double initialBalance = 100.0;
//        PassengerMembership membership = PassengerMembership.Standard;
        long mobileNumber = 1234567890;

        Passenger passenger = new StandardPassenger(passengerId, name, initialBalance, mobileNumber,1);

        double decrementAmount = 20.0;
        passenger.decrementBalance(decrementAmount);

        // Assert
        double expectedBalance = initialBalance - decrementAmount;
        assertEquals(expectedBalance, passenger.getBalance(), 0.001); // Using delta for double comparison
        
    }
    
    
    private static class PassengerImpl extends Passenger {
        public PassengerImpl(int id, String name, double balance, long mobileNumber) {
            super(id, name, balance, mobileNumber,1);
        }

        @Override
        public double getDiscountedPriceForActivity(double cost) {
            return cost * 0.9; 
        }
    }

}