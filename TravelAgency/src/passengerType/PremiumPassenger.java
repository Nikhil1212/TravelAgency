package passengerType;

import entities.Passenger;

public class PremiumPassenger extends Passenger {

	public PremiumPassenger(int id, String name, double balance,  long mobileNumber, int travelPackageId) {
		super(id, name, balance, mobileNumber,travelPackageId);
	}
	
	
	@Override
	public double getDiscountedPriceForActivity(double cost) {
		return 0;
	}
	

	

}
