package com.route.search.entity;

public class RouteEntity {

	private Airline airlineId;
	private Airport origin;
	private Airport destination;

	public Airline getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(Airline airlineId) {
		this.airlineId = airlineId;
	}

	public Airport getOrigin() {
		return origin;
	}

	public void setOrigin(Airport origin) {
		this.origin = origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "RouteEntity [airlineId=" + airlineId + ", origin=" + origin + ", destination=" + destination + "]";
	}

}
