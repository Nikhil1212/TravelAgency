package com.assignment.travel.entities.passenger;

public class GoldPassenger extends Passenger {
	
	private static double discountPercentage = 10;

	public GoldPassenger(int id, String name, double balance,  long mobileNumber, int travelPackageId) {
		super(id, name, balance, mobileNumber, travelPackageId);
	}

	@Override	
	public double getDiscountedPriceForActivity(double cost) {
		double res = cost * (100.0 - discountPercentage)/100.0;
		return res;
	}
	
}
