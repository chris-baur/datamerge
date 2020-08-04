package utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.opencsv.CSVWriter;

import models.Report;

public class Writer {

	// column header names as defined in csv
	private static final String[] columnHeaders = {"client-address", "client-guid", "request-time", "service-guid", "retries-request", "packets-requested", "packets-serviced", "max-hole-size"};
	
	/**
	 * Writes list of report to a csv file
	 * 
	 * @param results
	 * @param path
	 */
	public static void writeToCSV(List<Report> results, Path path) {
		CSVWriter writer;
		try {
			writer = new CSVWriter(new FileWriter(path.toString()));
			// write column headers before data
			writer.writeNext(columnHeaders);
			for (Report r : results) {
				String[] array = r.toStringArray();
		        writer.writeNext(array);
		    }
			writer.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
