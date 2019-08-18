package com.route.search.entity;

public class Route {

	private String airlineId;
	private String origin;
	private String destination;

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "Route [ airlineId=" + airlineId + ", origin=" + origin + ", destination=" + destination + "]";
	}

}
