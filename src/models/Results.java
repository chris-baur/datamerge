package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Results {

	private List<Report> reports;
	
	public Results(List<Report> reports) {
		this.reports = reports;
	}
	
	public List<Report> getReports() {
		return reports;
	}

	/**
	 * Prints to console the summary of records per service GUID
	 */
	public void printServiceGUIDRecords() {
		HashMap<String, List<Report>> hashmap = new HashMap<>();
		for(Report r : reports) {
			String key = r.getServiceGUID();
			//contains key, add to list.
			if(hashmap.containsKey(key)) {
				hashmap.get(key).add(r);
			} else { //does not contain key, add to hashmap
				hashmap.put(key, new ArrayList<Report>(Arrays.asList(r)));
			}
		}
		System.out.println("--- Summary of filtered results ---");
		hashmap.forEach((key, value) -> System.out.printf("Service GUID: %s. # of Records: %d.\n", key, value.size()));
	}
}
