package utilities;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import interfaces.ReportReader;
import models.Report;

public class XMLReportReader implements ReportReader {

	private String reportPath;

	public XMLReportReader(String reportPath) {
		this.reportPath = reportPath;
	}

	@Override
	public List<Report> getReports() {
		List<Report> results = new ArrayList<Report>();
		try {
			File file = new File(reportPath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			// get all nodes of tag report
			NodeList reportList = doc.getElementsByTagName("report");

			for (int i = 0; i < reportList.getLength(); i++) {
				Node node = reportList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) node;
					String clientAddress = e.getElementsByTagName("client-address").item(0).getTextContent();
					String clientGUID = e.getElementsByTagName("client-guid").item(0).getTextContent();
					ZonedDateTime requestTime = ZonedDateTime.parse(
							e.getElementsByTagName("request-time").item(0).getTextContent(),
							DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
					String serviceGUID = e.getElementsByTagName("service-guid").item(0).getTextContent();
					int retriesRequest = Integer
							.parseInt(e.getElementsByTagName("retries-request").item(0).getTextContent());
					int packetsRequested = Integer
							.parseInt(e.getElementsByTagName("packets-requested").item(0).getTextContent());
					int packetserviced = Integer
							.parseInt(e.getElementsByTagName("packets-serviced").item(0).getTextContent());
					int maxHoleSize = Integer
							.parseInt(e.getElementsByTagName("max-hole-size").item(0).getTextContent());
					results.add(new Report(clientAddress, clientGUID, requestTime, serviceGUID, retriesRequest,
							packetsRequested, packetserviced, maxHoleSize));
				}
			}
		} catch (NullPointerException npe) {
			System.out.println(npe.getMessage());
		} catch (NumberFormatException nfe) {
			System.out.println(nfe.getMessage());
		} catch (DateTimeParseException dtpe) {
			System.out.println(dtpe.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} catch (SAXException saxe) {
			System.out.println(saxe.getMessage());
		} catch (ParserConfigurationException pce) {
			System.out.println(pce.getMessage());
		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
		}

		return results;
	}

}
