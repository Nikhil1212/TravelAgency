package repository;

import java.util.HashMap;
import java.util.Map;

import entities.Destination;

public class DestinationRepository {
	
	private static DestinationRepository destinationRepository = null;

	private Map<Integer, Destination> destinationMap;
	
	private DestinationRepository() {
		destinationMap = new HashMap<Integer, Destination>();
	}
	
	public static DestinationRepository getInstanceOf() {
		
		if(destinationRepository == null) {
			destinationRepository = new DestinationRepository();
		}
		return destinationRepository;
	}
	
	
	
	public Map<Integer, Destination> getDestinationMap() {
		return destinationMap;
	}

	
	public Destination getDestinationById(int id) {
		return destinationMap.get(id);
	}
	
	public void addDestination(Destination destination) {
		destinationMap.put(destination.getDestinationID(), destination);
	}


}
