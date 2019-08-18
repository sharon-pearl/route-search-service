package com.route.search.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	Map<String, List<String>> vertices;

	public Graph() {
		vertices = new HashMap<>();
	}

	public void addEdge(String vertex1, String vertex2) {
		if (vertices.containsKey(vertex1)) {
			vertices.get(vertex1).add(vertex2);
		} else {
			List<String> edges = new ArrayList<>();
			edges.add(vertex2);
			vertices.put(vertex1, edges);
		}
	}

	public List<String> findPath(String origin, String destination, int maxEdges) {
		List<String> travelledVertices = new ArrayList<>();
		travelledVertices.add(origin);
		List<String> route = findPath(origin, destination, travelledVertices, 0, maxEdges / 2);
		if (route == null) {
			route = findPath(origin, destination, travelledVertices, 0, maxEdges);
		}
		return route;
	}

	List<String> findPath(String origin, String destination, List<String> travelledVertices, int level, int maxEdges) {
		List<String> result = null;
		List<String> connectedVertices = vertices.get(origin);
		if (connectedVertices != null && level < maxEdges) {
			if (connectedVertices.contains(destination)) {
				travelledVertices.add(destination);
				result = travelledVertices;
			} else {
				int possibleRouteLength = Integer.MAX_VALUE;
				for (String connectedVertex : connectedVertices) {
					if (!travelledVertices.contains(connectedVertex)) {
						List<String> newTravelled = new ArrayList<>(travelledVertices);
						newTravelled.add(connectedVertex);
						List<String> routeFound = findPath(connectedVertex, destination, newTravelled, level + 1,
								maxEdges);
						if (routeFound != null && routeFound.size() < possibleRouteLength) {
							result = routeFound;
							possibleRouteLength = routeFound.size();
						}
					}
				}
			}

		}
		return result;
	}

}