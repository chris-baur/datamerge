package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import interfaces.ReportReader;
import models.Report;

public class CSVReportReader implements ReportReader {

	private String reportPath;

	public CSVReportReader(String reportPath) {
		this.reportPath = reportPath;
	}

	@Override
	public List<Report> getReports() {
		List<Report> results = new ArrayList<Report>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(reportPath));
			String row = csvReader.readLine();
			if (row != null) {
				// first row is the column headers for the data
				row = csvReader.readLine();
			}
			while (row != null) {
				String[] data = row.split(",");
				String clientAddress = data[0];
				String clientGUID = data[1];
				ZonedDateTime requestTime = ZonedDateTime.parse(data[2],
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
				String serviceGUID = data[3];
				int retriesRequest = Integer.parseInt(data[4]);
				int packetsRequested = Integer.parseInt(data[5]);
				int packetserviced = Integer.parseInt(data[6]);
				int maxHoleSize = Integer.parseInt(data[7]);
				results.add(new Report(clientAddress, clientGUID, requestTime, serviceGUID, retriesRequest,
						packetsRequested, packetserviced, maxHoleSize));
				row = csvReader.readLine();
			}

			csvReader.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		} catch (NumberFormatException nfe) {
			System.out.println(nfe.getMessage());
		} catch (DateTimeParseException dtpe) {
			System.out.println(dtpe.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

		return results;
	}

}
