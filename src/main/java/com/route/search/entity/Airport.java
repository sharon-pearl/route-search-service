package com.route.search.entity;

public class Airport {

	private String iata3;
	private String name;
	private String city;
	private String country;
	private Double latitude;
	private Double longitude;

	public Airport(String iata3) {
		this.iata3 = iata3;
	}

	public String getIata3() {
		return iata3;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Airport [iata3=" + iata3 + ", name=" + name + ", city=" + city + ", country=" + country + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}

}
