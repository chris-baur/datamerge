package utilities;

import java.util.ArrayList;
import java.util.List;

import interfaces.ReportReader;
import models.Report;
public class Reader {

	/**
	 * Reads from a report reader and concatenates the provided reports
	 * @param reportPaths
	 * @return List<Report>
	 */
	public static List<Report> readFromReports(String[] reportPaths) {
		List<Report> results = new ArrayList<Report>();
		
		//loop through all reports to look at
		for(String reportPath: reportPaths) {
			ReportReader rr = reportReaderFactory(reportPath);
			results.addAll(rr.getReports());
		}
		
		return results;
	}
	
	/**
	 * Factory method for getting the appropriate report reader for the file type
	 * 
	 * @param reportPath String
	 * @return ReportReader
	 */
	private static ReportReader reportReaderFactory(String reportPath) {
		String extension = reportPath.substring(reportPath.lastIndexOf('.') + 1);
		switch(extension) {
		case "csv":
			return new CSVReportReader(reportPath);
		case "xml":
			return new XMLReportReader(reportPath);
		case "json":
			return new JSONReportReader(reportPath);
		default:
			return null;
		}
	}
}
