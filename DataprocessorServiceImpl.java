package com.ona.dataprocessor.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.google.gson.Gson;
import com.ona.data.WaterPoint;
import com.ona.dataprocessor.service.DataprocessorService;

/**
 * @author mnduati
 * @date May 28, 2016
 * 
 */
@ManagedBean(name = "dataprocessorService")
@ApplicationScoped
public class DataprocessorServiceImpl implements DataprocessorService, Serializable {

	
	private static final long serialVersionUID = 1L;
	private URL url;
	private String jsonString;
	private static String FUNCTIONAL_WATER_POINT = "Yes";
	private static String BROKEN_WATER_POINT = "No";

	public DataprocessorServiceImpl() {
		super();
		jsonString = readUrl("https://raw.githubusercontent.com/onaio/ona-tech/master/data/water_points.json");
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.ona.dataprocessor.service.DataprocessorService#readUrl(java.lang.String)
	 */
	@Override
	public String readUrl(String urlString) {
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			try {
				url = new URL(urlString);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return buffer.toString();
	}

	/* (non-Javadoc)
	 * @see com.ona.dataprocessor.service.DataprocessorService#getWaterPointsFromJson()
	 */
	@Override
	public List<WaterPoint> getWaterPointsFromJson() {
		List<WaterPoint> waterPoints = new ArrayList<WaterPoint>();
		Gson gson = new Gson();
		waterPoints = Arrays.asList(gson.fromJson(jsonString, WaterPoint[].class));
		return waterPoints;

	}

	/**
	 * Returns a list of {@link WaterPoint} that are functional
	 * @return
	 */
	public List<WaterPoint> getFunctionalWaterPoints() {
		List<WaterPoint> waterPoints = getWaterPointsFromJson();
		List<WaterPoint> functionalWaterPoints = new ArrayList<WaterPoint>();

		for (WaterPoint waterPoint : waterPoints) {
			if (waterPoint.getWater_functioning().equalsIgnoreCase(FUNCTIONAL_WATER_POINT)) {
				functionalWaterPoints.add(waterPoint);
			}
		}
		return functionalWaterPoints;

	}

	/**
	 * Returns an array list of all Communities
	 * @return
	 */
	public List<String> getCommunities() {
		List<String> communities = new ArrayList<String>();
		List<WaterPoint> waterPoints = getWaterPointsFromJson();
		for (WaterPoint waterPoint : waterPoints) {
			String community = waterPoint.getCommunities_villages();
			if (!communities.contains(community)) {
				communities.add(community);

			}
		}

		return communities;
	}

	/**
	 * Returns a list of {@link WaterPoint} that are broken
	 * @return
	 */
	public List<WaterPoint> getBrokenWaterPoints() {
		List<WaterPoint> waterPoints = getWaterPointsFromJson();
		List<WaterPoint> brokenWaterPoints = new ArrayList<WaterPoint>();

		for (WaterPoint waterPoint : waterPoints) {
			if (waterPoint.getWater_functioning().equalsIgnoreCase(BROKEN_WATER_POINT)) {
				brokenWaterPoints.add(waterPoint);
			}
		}
		return brokenWaterPoints;

	}

	/* (non-Javadoc)
	 * @see com.ona.dataprocessor.service.DataprocessorService#numberOfFunctionalWaterPoints()
	 */
	@Override
	public List<WaterPoint> numberOfFunctionalWaterPoints() {
		List<WaterPoint> number_functional_Water_Points = new ArrayList<WaterPoint>();
		List<WaterPoint> waterPoints = getWaterPointsFromJson();
		for (WaterPoint waterPoint : waterPoints) {
			if (waterPoint.getWater_functioning().equalsIgnoreCase(FUNCTIONAL_WATER_POINT)) {
				number_functional_Water_Points.add(waterPoint);
			}
		}
		return number_functional_Water_Points;
	}

	/* (non-Javadoc)
	 * @see com.ona.dataprocessor.service.DataprocessorService#getNumberOfWaterPointsPerCommunity()
	 */
	@Override
	public Map<String, Integer> getNumberOfWaterPointsPerCommunity() {
		Map<String, Integer> communitiesWaterPointsMap = new LinkedHashMap<String, Integer>();
		List<String> communities = getCommunities();
		List<WaterPoint> waterPoints = getWaterPointsFromJson();
		for (String community : communities) {
			int noOfWaterPointsPerCommunity = 0;
			for (WaterPoint waterPoint : waterPoints) {
				if (waterPoint.getCommunities_villages().equals(community)) {
					noOfWaterPointsPerCommunity++;
					communitiesWaterPointsMap.put(community, noOfWaterPointsPerCommunity);
				}
			}

		}

		return communitiesWaterPointsMap;
	}

	/* (non-Javadoc)
	 * @see com.ona.dataprocessor.service.DataprocessorService#getNumberOfFunctionalWaterPoints()
	 */
	@Override
	public Map<String, Integer> getNumberOfFunctionalWaterPoints() {
		List<WaterPoint> functionalWaterPoints = getFunctionalWaterPoints();
		Map<String, Integer> functionalWaterPointsMap = new LinkedHashMap<String, Integer>();
		List<String> communities = getCommunities();
		for (String community : communities) {
			int noOfWaterPointsPerCommunity = 0;
			for (WaterPoint waterPoint : functionalWaterPoints) {
				if (waterPoint.getCommunities_villages().equals(community)) {
					noOfWaterPointsPerCommunity++;
					functionalWaterPointsMap.put(community, noOfWaterPointsPerCommunity);
				}
			}
		}
		return functionalWaterPointsMap;
	}

	/* (non-Javadoc)
	 * @see com.ona.dataprocessor.service.DataprocessorService#getNumberOfBrokenWaterPointsPerCommunity()
	 */
	@Override
	public Map<String, Integer> getNumberOfBrokenWaterPointsPerCommunity() {
		Map<String, Integer> brokenWaterPointsPerCommunityMap = new LinkedHashMap<String, Integer>();
		List<WaterPoint> brokenWaterPoints = getBrokenWaterPoints();
		List<String> communities = getCommunities();
		for (String community : communities) {
			int noOfWaterPointsPerCommunity = 0;
			for (WaterPoint waterPoint : brokenWaterPoints) {
				if (waterPoint.getCommunities_villages().equals(community)) {
					noOfWaterPointsPerCommunity++;
					brokenWaterPointsPerCommunityMap.put(community, noOfWaterPointsPerCommunity);
				}

			}

		}
		return brokenWaterPointsPerCommunityMap;

	}

	/* (non-Javadoc)
	 * @see com.ona.dataprocessor.service.DataprocessorService#getRankBrokenWaterPointsPerCommunity()
	 */
	@Override
	public Map<String, Integer> getRankBrokenWaterPointsPerCommunity() {
		Map<String, Integer> brokenWaterPointsPerCommunityRankMap = new LinkedHashMap<String, Integer>();
		Map<String, Integer> communitiesWaterPointsMap = getNumberOfWaterPointsPerCommunity();
		Map<String, Integer> brokenWaterPointsPerCommunityMap = getNumberOfBrokenWaterPointsPerCommunity();
		Iterator<String> communityNameItr = brokenWaterPointsPerCommunityMap.keySet().iterator();
		int noOfBrokenWaterPoints = 0;
		int noOfCommunitiesWaterPoints = 0;
		String communityName = "";
		Integer percent = 0;
		while (communityNameItr.hasNext()) {
			communityName = communityNameItr.next();
			noOfCommunitiesWaterPoints = communitiesWaterPointsMap.get(communityName);

			for (Map.Entry<String, Integer> entry : brokenWaterPointsPerCommunityMap.entrySet()) {
				if (entry.getValue() != 0)
					noOfBrokenWaterPoints = entry.getValue();
				if (entry.getKey().equals(communityName)) {

					percent = (int) ((noOfBrokenWaterPoints * 100.0f) / noOfCommunitiesWaterPoints);
				}

			}

			brokenWaterPointsPerCommunityRankMap.put(communityName, percent);

		}

		return this.sortByValue(brokenWaterPointsPerCommunityRankMap);
	}



	/**
	 * @param unsortMap
	 * @return a comparator that compares Strings
	 */
	private Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

		// Convert Map to List
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// Convert sorted map back to a Map
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}
