package com.route.search.service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.route.search.entity.Airline;
import com.route.search.entity.Airport;
import com.route.search.entity.Route;

@Component
public class MigrationService {

	private static final Logger logger = LoggerFactory.getLogger(MigrationService.class);

	@Value("${migration.routesFile}")
	private String routesFile;

	@Value("${migration.airlinesFile}")
	private String airlinesFile;

	@Value("${migration.airportsFile}")
	private String airportsFile;

	public List<Route> loadRouteData() {
		List<Route> routes = new ArrayList<>();
		ClassPathResource resource = new ClassPathResource(routesFile);
		try (CSVReader reader = new CSVReader(new FileReader(resource.getFile()));) {
			reader.readNext();
			String[] line = reader.readNext();
			while (line != null) {
				Route route = new Route();
				try {
					route.setAirlineId(line[0]);
					route.setOrigin(line[1]);
					route.setDestination(line[2]);
					routes.add(route);
				} catch (Exception e) {
					logger.error("error while inserting route entity " + route, e);
				} finally {
					line = reader.readNext();
				}
			}
		} catch (Exception e) {
			logger.error("error while inserting reading routes input file", e);
		}
		return routes;
	}

	public Map<String, Airline> loadAirlineData() {
		Map<String, Airline> airlines = new HashMap<>();
		ClassPathResource resource = new ClassPathResource(airlinesFile);
		try (CSVReader reader = new CSVReader(new FileReader(resource.getFile()));) {
			reader.readNext();
			String[] line = reader.readNext();
			while (line != null) {
				try {
					Airline airline = new Airline(line[1]);
					airline.setName(line[0]);
					airline.setD3Code(line[2]);
					airline.setCountry(line[3]);
					airlines.put(airline.getD2Code(), airline);
				} catch (Exception e) {
					logger.error("error while inserting airline entity " + line, e);
				} finally {
					line = reader.readNext();
				}
			}
		} catch (Exception e) {
			logger.error("error while inserting reading airlines input file", e);
		}
		return airlines;
	}

	public Map<String, Airport> loadAirportData() {
		Map<String, Airport> airports = new HashMap<>();
		ClassPathResource resource = new ClassPathResource(airportsFile);
		try (CSVReader reader = new CSVReader(new FileReader(resource.getFile()));) {
			reader.readNext();
			String[] line = reader.readNext();
			while (line != null) {
				try {
					Airport airport = new Airport(line[3]);
					airport.setName(line[0]);
					airport.setCity(line[1]);
					airport.setCountry(line[2]);
					airport.setLatitude(Double.parseDouble(line[4]));
					airport.setLongitude(Double.parseDouble(line[5]));
					airports.put(airport.getIata3(), airport);
				} catch (Exception e) {
					logger.error("error while inserting Airport entity " + line, e);
				} finally {
					line = reader.readNext();
				}
			}
		} catch (Exception e) {
			logger.error("error while inserting reading Airports input file", e);
		}
		return airports;
	}

}
