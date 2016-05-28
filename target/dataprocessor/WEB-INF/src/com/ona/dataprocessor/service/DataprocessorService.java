package com.ona.dataprocessor.service;

import java.util.List;
import java.util.Map;

import com.ona.data.WaterPoint;

/**
 * @author mnduati
 * @date May 28, 2016
 */
public interface DataprocessorService {
	
	
	
	/**
	 * @param urlString the json url with the data
	 * @return url
	 */
	public String readUrl(String urlString);
	
	/**
	 * Returns a list of {@link WaterPoint} from the json data from the link
	 * @return 
	 */
	public List<WaterPoint> getWaterPointsFromJson();
	
	/**
	 * Returns a list of {@link WaterPoint} that are functional
	 * @return
	 */
	public List<WaterPoint> numberOfFunctionalWaterPoints();
	
	/**
	 * Returns a {@Link Map} of water points per community
	 * @return
	 */
	public Map<String, Integer> getNumberOfWaterPointsPerCommunity();
	
	
	/**
	 * Returns a {@Link Map} of functional water points per community
	 * that are functional
	 * @return
	 */
	public Map<String, Integer> getNumberOfFunctionalWaterPoints();
	
	
	
	/**
	 * Returns a {@Link Map} of broken water points per community
	 * @return
	 */
	public Map<String, Integer> getNumberOfBrokenWaterPointsPerCommunity();
	
	
	/**
	 *  Returns a {@Link Map} of broken water points per community ranked
	 *  in ascending order by their percentage
	 * @return
	 */
	public Map<String, Integer> getRankBrokenWaterPointsPerCommunity();
	

	
	

}
