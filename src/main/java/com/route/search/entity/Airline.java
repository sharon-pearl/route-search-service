package com.route.search.entity;

public class Airline {

	private String name;
	private String d2Code;
	private String d3Code;
	private String country;

	public Airline(String d2Code) {
		this.d2Code = d2Code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getD2Code() {
		return d2Code;
	}

	public String getD3Code() {
		return d3Code;
	}

	public void setD3Code(String d3Code) {
		this.d3Code = d3Code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Airline [name=" + name + ", d2Code=" + d2Code + ", d3Code=" + d3Code + ", country=" + country + "]";
	}

}
