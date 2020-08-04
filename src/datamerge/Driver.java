/**
 * Driver of the application.
 * 
 * @author Chris Baur
 */
package datamerge;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import models.Report;
import models.Results;
import utilities.*;

public class Driver {

	public static void main(String[] args) {
		String[] files = new String[] { "reports.csv", "reports.json", "reports.xml" };

		Results results = new Results(Reader.readFromReports(files));
		List<Report> allReports = results.getReports();
		// chose to make filter explicit for removing the reports with 0 packets
		// serviced, instead of not adding them in the first place in each reader
		List<Report> filteredResults = allReports.stream().filter(r -> r.getPacketserviced() != 0)
				.sorted((r1, r2) -> r1.getRequestTime().compareTo(r2.getRequestTime())).collect(Collectors.toList());
		try {
			String filePath = "output/results.csv";
			File file = new File(filePath);
			file.createNewFile();
			Path path = Paths.get(file.toURI());
			Writer.writeToCSV(filteredResults, path);
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		results.printServiceGUIDRecords();
	}

}
