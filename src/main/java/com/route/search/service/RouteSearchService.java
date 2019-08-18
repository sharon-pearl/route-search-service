package com.route.search.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.route.search.entity.Airline;
import com.route.search.entity.Airport;
import com.route.search.entity.Graph;
import com.route.search.entity.Route;
import com.route.search.entity.RouteEntity;

@Service
public class RouteSearchService {

	private static final Logger logger = LoggerFactory.getLogger(RouteSearchService.class);

	@Autowired
	private MigrationService migrate;

	@Value("${route.maxConnections:4}")
	private int maxConnections;

	private Graph routeGraph;

	private Map<String, List<String>> routeAirlines;

	private Map<String, Airline> airlines;
	private Map<String, Airport> airports;

	private static final String SEPARATOR = "_";

	@PostConstruct
	public void init() {
		loadRoutes();
		airlines = migrate.loadAirlineData();
		airports = migrate.loadAirportData();
	}

	public void loadRoutes() {
		routeAirlines = new HashMap<>();
		routeGraph = new Graph();
		List<Route> routes = migrate.loadRouteData();
		for (Route route : routes) {
			try {
				routeGraph.addEdge(route.getOrigin(), route.getDestination());
				String key = route.getOrigin() + SEPARATOR + route.getDestination();
				if (routeAirlines.containsKey(key)) {
					routeAirlines.get(key).add(route.getAirlineId());
				} else {
					List<String> airlines = new ArrayList<>();
					airlines.add(route.getAirlineId());
					routeAirlines.put(key, airlines);
				}
			} catch (Exception e) {
				logger.error("Error while formatting route " + route, e);
			}
		}
	}

	public List<RouteEntity> getRoutes(String origin, String destination) {
		List<RouteEntity> entities = new ArrayList<>();

		List<String> route = routeGraph.findPath(origin.toUpperCase(), destination.toUpperCase(), maxConnections);
		for (int i = 0; route != null && i < route.size() - 1; i++) {
			RouteEntity routeEntity = new RouteEntity();
			String currentPort = route.get(i);
			String nextPort = route.get(i + 1);
			String airline = routeAirlines.get(currentPort + SEPARATOR + nextPort).get(0);
			routeEntity.setAirlineId(airlines.get(airline));
			routeEntity.setOrigin(airports.get(currentPort));
			routeEntity.setDestination(airports.get(nextPort));
			entities.add(routeEntity);
		}
		return entities;
	}

}