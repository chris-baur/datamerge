package models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Report {
	private String clientAddress;
	private String clientGUID;
	private ZonedDateTime requestTime;
	private String serviceGUID;
	private int retriesRequest;
	private int packetsRequested;
	private int packetserviced;
	private int maxHoleSize;

	public Report(String clientAddress, String clientGUID, ZonedDateTime requestTime, String serviceGUID,
			int retriesRequest, int packetsRequested, int packetserviced, int maxHoleSize) {
		super();
		this.clientAddress = clientAddress;
		this.clientGUID = clientGUID;
		this.requestTime = requestTime;
		this.serviceGUID = serviceGUID;
		this.retriesRequest = retriesRequest;
		this.packetsRequested = packetsRequested;
		this.packetserviced = packetserviced;
		this.maxHoleSize = maxHoleSize;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public String getClientGUID() {
		return clientGUID;
	}

	public ZonedDateTime getRequestTime() {
		return requestTime;
	}

	public String getServiceGUID() {
		return serviceGUID;
	}

	public int getRetriesRequest() {
		return retriesRequest;
	}

	public int getPacketsRequested() {
		return packetsRequested;
	}

	public int getPacketserviced() {
		return packetserviced;
	}

	public int getMaxHoleSize() {
		return maxHoleSize;
	}

	/**
	 * Returns a string array representation of all the fields of the Report object
	 * 
	 * @return String[]
	 */
	public String[] toStringArray() {
		return new String[] { clientAddress, clientGUID, requestTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")), serviceGUID,
				Integer.toString(retriesRequest), Integer.toString(packetsRequested), Integer.toString(packetserviced),
				Integer.toString(maxHoleSize) };
	}

}
