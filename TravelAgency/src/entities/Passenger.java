package entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Passenger {
	
	
	private int passengerId;	// can be treated as id.
	private String name;
	private double balance;   
	private long mobileNumber;
	private int travelPacakgeId;

	public abstract double getDiscountedPriceForActivity(double cost);


	private List<Integer> listActivityId;
	

	public Passenger(int id, String name, double balance,  long mobileNumber, int travelPackageId) {
		this.passengerId = id; 
		this.name = name;
		this.balance = balance;
		this.mobileNumber = mobileNumber;
		this.listActivityId = new ArrayList<Integer>();
		this.travelPacakgeId = travelPackageId;
	}
	
	public int getTravelPacakgeId() {
		return travelPacakgeId;
	}
	
	public int getPassengerId() {
		return passengerId;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public  void decrementBalance(double balance) {
		this.balance -=balance;
	}
	
	public long getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public List<Integer> getListActivityId() {
		return listActivityId;
	}

	public int getId() {
		return this.passengerId;
	}
}
