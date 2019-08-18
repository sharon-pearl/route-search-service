package com.route.search.service;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.route.search.entity.Graph;

public class GraphTest {

	private static Graph graph;

	@BeforeClass
	public static void setUp() {
		graph = new Graph();
		createMocks();
	}

	public static void createMocks() {
		graph.addEdge("1", "3");
		graph.addEdge("3", "2");
		graph.addEdge("3", "4");
		graph.addEdge("1", "4");
		graph.addEdge("4", "5");
		graph.addEdge("2", "1");
		graph.addEdge("5", "1");
		graph.addEdge("1", "6");
		graph.addEdge("6", "7");
		graph.addEdge("7", "8");
		graph.addEdge("8", "9");
		graph.addEdge("9", "10");

	}

	@Test
	public void findPathTest_onePath() {
		List<String> path = graph.findPath("1", "2", 3);
		Assert.assertArrayEquals(path.toArray(), new String[] { "1", "3", "2" });
	}

	@Test
	public void findPathTest_moreThanOnePath() {
		List<String> path = graph.findPath("1", "4", 3);
		Assert.assertArrayEquals(path.toArray(), new String[] { "1", "4" });
	}

	@Test
	public void findPathTest_noPath() {
		List<String> path = graph.findPath("1", "111", 3);
		Assert.assertNull(path);
	}

	@Test
	public void findPathTest_longPath() {
		List<String> path = graph.findPath("1", "10", 6);
		Assert.assertArrayEquals(path.toArray(), new String[] { "1", "6", "7", "8", "9", "10" });
	}

}
