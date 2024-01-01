package com.assignment.travel.entities.passenger;

public class StandardPassenger extends Passenger {

	public StandardPassenger(int id, String name, double balance, long mobileNumber, int travelPackageId) {
		super(id, name, balance, mobileNumber, travelPackageId);
	}

	@Override
	public double getDiscountedPriceForActivity(double cost) {
		return cost;
	}

	

}
