package com.route.search.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.route.search.entity.RouteEntity;
import com.route.search.service.RouteSearchService;

@RestController
@Validated
@RequestMapping("/route")
public class RouteSearchController {

	private static final Logger LOG = LoggerFactory.getLogger(RouteSearchController.class);

	@Autowired
	private RouteSearchService routeSearchService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/search")
	public ResponseEntity<List> getRoutes(
			@Size(min = 1, max = 3) @RequestParam(name = "origin", required = true) String origin,
			@Size(min = 1, max = 3) @RequestParam(name = "destination", required = true) String destination) {
		HttpStatus status = HttpStatus.OK;
		List<RouteEntity> routes = new ArrayList<>();
		try {
			routes = routeSearchService.getRoutes(origin, destination);
			if (routes.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			LOG.error("Error while searching routes for " + origin + " " + destination, e);
		}
		return new ResponseEntity<List>(routes, status);
	}

}
