package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Itinerary {

	private int id;

	private List<Integer> listDestinationId;
	
	public List<Integer> getListDestinations() {
		return listDestinationId;
	}
	
	
	public void removeDestinationById(int index) {
		listDestinationId.remove(index);
	}
	
	public void addDestination(int id) {
		listDestinationId.add(id);
	}

	public Itinerary(int id) {
		this.id = id;
		listDestinationId = new ArrayList<Integer>();
	}


	public int getItineraryId() {
		return id;
	}



}
