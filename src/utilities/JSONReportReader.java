package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import interfaces.ReportReader;
import models.Report;

public class JSONReportReader implements ReportReader {

	private String reportPath;

	public JSONReportReader(String reportPath) {
		this.reportPath = reportPath;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Report> getReports() {
		List<Report> results = new ArrayList<Report>();
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(reportPath)) {
			JSONArray reportList = (JSONArray) jsonParser.parse(reader);

			// Iterate over report list and add to results
			reportList.forEach(r -> {
				JSONObject report = (JSONObject) r;
				String clientAddress = (String) report.get("client-address");
				String clientGUID = (String) report.get("client-guid");
				ZonedDateTime requestTime = ZonedDateTime.ofInstant(
						Instant.ofEpochMilli((Long) report.get("request-time")), TimeZone.getDefault().toZoneId());
				String serviceGUID = (String) report.get("service-guid");
				int retriesRequest = Math.toIntExact((Long) report.get("retries-request"));
				int packetsRequested = Math.toIntExact((Long) report.get("packets-requested"));
				int packetserviced = Math.toIntExact((Long) report.get("packets-serviced"));
				int maxHoleSize = Math.toIntExact((Long) report.get("max-hole-size"));
				results.add(new Report(clientAddress, clientGUID, requestTime, serviceGUID, retriesRequest,
						packetsRequested, packetserviced, maxHoleSize));
			});

		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} catch (ParseException pe) {
			System.out.println(pe.getMessage());
		} catch (NumberFormatException nfe) {
			System.out.println(nfe.getMessage());
		} catch (DateTimeParseException dtpe) {
			System.out.println(dtpe.getMessage());
		} catch (ClassCastException cce) {
			System.out.println(cce.getMessage());
			cce.printStackTrace();
		}

		return results;
	}

}
