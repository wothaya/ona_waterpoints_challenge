package com.ona.dataprocessor.service.Impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.ona.data.WaterPoint;
import com.ona.dataprocessor.service.impl.DataprocessorServiceImpl;

public class DataprocessorServiceTest {

	private DataprocessorServiceImpl dataProcessor = new DataprocessorServiceImpl();

	@Before
	public void setupService() {
		dataProcessor.readUrl("https://raw.githubusercontent.com/onaio/ona-tech/master/data/water_points.json");
	}

	@Test
	public void testGetWaterPointsFromJSON() {
		List<WaterPoint> allWaterPoints = dataProcessor.getWaterPointsFromJson();
		allWaterPoints.size();
		assertEquals("The Water Points size doesn't match the expected value", allWaterPoints.size(), 712);

	}

	@Test
	public void testNumberOfWaterPointsPerCommunity() {
		Map<String, Integer> waterPointsPerCommunity = dataProcessor.getNumberOfWaterPointsPerCommunity();
		assertFalse(waterPointsPerCommunity.isEmpty());
		assertEquals("The actual number of water points in Selinvoya community is different from the expected value",
				13, (int) waterPointsPerCommunity.get("Selinvoya"));

	}

	@Test
	public void testNumberOfFunctionalWaterPointsPerCommunity() {
		Map<String, Integer> functionalWaterPointsPerCommunity = dataProcessor.getNumberOfFunctionalWaterPoints();
		assertFalse(functionalWaterPointsPerCommunity.isEmpty());
		assertEquals(
				"The actual number of functional water points in Selinvoya community is different from the expected value",
				12, (int) functionalWaterPointsPerCommunity.get("Selinvoya"));
	}

	@Test
	public void testNumberOfBrokenWaterPointsPerCommunity() {
		Map<String, Integer> brokenWaterPointsPerCommunity = dataProcessor.getNumberOfBrokenWaterPointsPerCommunity();
		assertFalse(brokenWaterPointsPerCommunity.isEmpty());
		assertEquals(
				"The actual number of broken water points in Selinvoya community is different from the expected value",
				1, (int) brokenWaterPointsPerCommunity.get("Selinvoya"));
	}

	@Test
	public void testRankOfBrokenWaterPointsPerCommunity() {

		Map<String, Integer> brokenWaterPointsRankPerCommunity = dataProcessor.getRankBrokenWaterPointsPerCommunity();
		List<Integer> expectedArray = new ArrayList<Integer>();
		for (Map.Entry<String, Integer> entry : brokenWaterPointsRankPerCommunity.entrySet()) {
			expectedArray.add(entry.getValue());
		}
		int lowestPercentage = expectedArray.get(0);
		int highestPecentage = expectedArray.get(expectedArray.size() - 1);
		assertTrue("The lowest percentage", 5 == lowestPercentage);
		assertTrue("The highest percentage", 66 == highestPecentage);

	}

}
