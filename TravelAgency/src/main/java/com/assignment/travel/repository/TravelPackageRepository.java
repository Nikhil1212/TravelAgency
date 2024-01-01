package com.assignment.travel.repository;

import java.util.HashMap;
import java.util.Map;

import com.assignment.travel.entities.TravelPackage;


public class TravelPackageRepository {
	
	private Map <Integer, TravelPackage> travelPackageMap;
	
	public Map<Integer, TravelPackage> getTravelPackageMap() {
		return travelPackageMap;
	}

	private static TravelPackageRepository travelPackageRepositoryObj = null;

	private TravelPackageRepository() {
		travelPackageMap = new HashMap <Integer, TravelPackage> ();
	}
	
	public static TravelPackageRepository getInstanceOf() {
		if(travelPackageRepositoryObj == null)
			travelPackageRepositoryObj = new TravelPackageRepository();
		return travelPackageRepositoryObj;
	}

}
