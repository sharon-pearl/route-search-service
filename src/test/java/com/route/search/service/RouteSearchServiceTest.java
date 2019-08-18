package com.route.search.service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.util.ReflectionUtils;

import com.route.search.entity.Airline;
import com.route.search.entity.Airport;
import com.route.search.entity.Graph;
import com.route.search.entity.RouteEntity;

public class RouteSearchServiceTest {

	private RouteSearchService service;
	private Map<String, Airport> airportsMock;
	private Map<String, Airline> airlinesMock;
	private Graph routeGraphMock;
	private Map<String, List<String>> routeAirlinesMock;

	@Before
	public void setUp() throws Exception {
		service = new RouteSearchService();
		// maxConnections

		Field maxConnectionsField = ReflectionUtils.findField(RouteSearchService.class, "maxConnections");
		maxConnectionsField.setAccessible(true);
		maxConnectionsField.set(service, 6);

		Field airportsField = ReflectionUtils.findField(RouteSearchService.class, "airports");
		airportsField.setAccessible(true);
		airportsMock = Mockito.mock(Map.class);
		airportsField.set(service, airportsMock);

		Field airlinesField = ReflectionUtils.findField(RouteSearchService.class, "airlines");
		airlinesField.setAccessible(true);
		airlinesMock = Mockito.mock(Map.class);
		airlinesField.set(service, airlinesMock);

		Field routeGraphField = ReflectionUtils.findField(RouteSearchService.class, "routeGraph");
		routeGraphField.setAccessible(true);
		routeGraphMock = Mockito.mock(Graph.class);
		routeGraphField.set(service, routeGraphMock);

		Field routeAirlinesField = ReflectionUtils.findField(RouteSearchService.class, "routeAirlines");
		routeAirlinesField.setAccessible(true);
		routeAirlinesMock = Mockito.mock(Map.class);
		routeAirlinesField.set(service, routeAirlinesMock);

	}

	@Test
	public void findRoutesTest_empty() {
		List<RouteEntity> entities = service.getRoutes("1", "2");
		Mockito.verify(routeGraphMock, Mockito.times(1)).findPath(Mockito.matches("1"), Mockito.matches("2"),
				Mockito.eq(6));
		Assert.assertTrue(entities.isEmpty());

	}

	@Test
	public void findRoutesTest_nonEmpty() {
		setUpMocks();
		List<RouteEntity> entities = service.getRoutes("1", "2");
		Mockito.verify(routeGraphMock, Mockito.times(1)).findPath(Mockito.matches("1"), Mockito.matches("2"),
				Mockito.eq(6));
		Assert.assertEquals(2, entities.size());
		Assert.assertEquals("CA",entities.get(0).getAirlineId().getD2Code());
		Assert.assertEquals("CA",entities.get(1).getAirlineId().getD2Code());
		Assert.assertEquals("1",entities.get(0).getOrigin().getIata3());
		Assert.assertEquals("3",entities.get(0).getDestination().getIata3());
		Assert.assertEquals("3",entities.get(1).getOrigin().getIata3());
		Assert.assertEquals("2",entities.get(1).getDestination().getIata3());
	}

	public void setUpMocks() {
		String airlineId = "CA";
		Mockito.when(routeGraphMock.findPath(Mockito.matches("1"), Mockito.matches("2"), Mockito.eq(6)))
				.thenReturn(Arrays.asList("1", "3", "2"));
		Mockito.when(routeAirlinesMock.get("1_3")).thenReturn(Arrays.asList(airlineId));
		Mockito.when(routeAirlinesMock.get("3_2")).thenReturn(Arrays.asList(airlineId));
		Airline airlineCA = new Airline(airlineId);
		Mockito.when(airlinesMock.get(airlineId)).thenReturn(airlineCA);
		Airport airport1 = new Airport("1");
		Airport airport2 = new Airport("2");
		Airport airport3 = new Airport("3");
		Mockito.when(airportsMock.get("1")).thenReturn(airport1);
		Mockito.when(airportsMock.get("2")).thenReturn(airport2);
		Mockito.when(airportsMock.get("3")).thenReturn(airport3);
	}

}