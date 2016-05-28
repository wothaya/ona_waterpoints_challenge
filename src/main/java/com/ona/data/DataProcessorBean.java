package com.ona.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ona.dataprocessor.service.DataprocessorService;

/**
 * @author mnduati
 * @date May 28, 2016
 */
@ManagedBean(name = "waterpoint")
@ViewScoped
public class DataProcessorBean implements Serializable {


	private static final long serialVersionUID = 1L;

	private List<WaterPoint> waterPoints;
	private List<WaterPoint> functionalWaterPoints;
	private Map<String, Integer> number_of_Water_Points_Per_Community;
	private Map<String, Integer> waterPointPerCommunityMap;
	private List<String> waterPointPerCommunityKeyList;
	private Map<String, Integer> functionalWaterPointsMap;
	private List<String> functionalWaterPointsKeyList;
	public Map<String, Integer> brokenWaterPointsPerCommunityMap;
	private List<String> brokenWaterPointsPerCommunityKeyList;
	private Map<String, Integer> brokenWaterPointsRankMap;
	private List<String> brokenWaterPointsRankKeyList;

	@ManagedProperty("#{dataprocessorService}")
	private DataprocessorService dataprocessorService;

	@PostConstruct
	public void init() {
		waterPoints = dataprocessorService.getWaterPointsFromJson();
		functionalWaterPoints = dataprocessorService.numberOfFunctionalWaterPoints();
		waterPointPerCommunityMap = dataprocessorService.getNumberOfWaterPointsPerCommunity();
		waterPointPerCommunityKeyList = new ArrayList<String>(waterPointPerCommunityMap.keySet());
		functionalWaterPointsMap = dataprocessorService.getNumberOfFunctionalWaterPoints();
		functionalWaterPointsKeyList = new ArrayList<String>(functionalWaterPointsMap.keySet());
		brokenWaterPointsPerCommunityMap = dataprocessorService.getNumberOfBrokenWaterPointsPerCommunity();
		brokenWaterPointsPerCommunityKeyList = new ArrayList<String>(brokenWaterPointsPerCommunityMap.keySet());
		brokenWaterPointsRankMap = dataprocessorService.getRankBrokenWaterPointsPerCommunity();
		brokenWaterPointsRankKeyList = new ArrayList<String>(brokenWaterPointsRankMap.keySet());

	}

	public List<WaterPoint> getWaterPoints() {
		return waterPoints;
	}

	public void setWaterPoints(List<WaterPoint> waterPoints) {
		this.waterPoints = waterPoints;
	}

	public DataprocessorService getDataprocessorService() {
		return dataprocessorService;
	}

	public void setDataprocessorService(DataprocessorService dataprocessorService) {
		this.dataprocessorService = dataprocessorService;
	}

	public List<WaterPoint> getFunctionalWaterPoints() {
		return functionalWaterPoints;
	}

	public void setFunctionalWaterPoints(List<WaterPoint> functionalWaterPoints) {
		this.functionalWaterPoints = functionalWaterPoints;
	}

	public Map<String, Integer> getNumber_of_Water_Points_Per_Community() {
		return number_of_Water_Points_Per_Community;
	}

	public void setNumber_of_Water_Points_Per_Community(Map<String, Integer> number_of_Water_Points_Per_Community) {
		this.number_of_Water_Points_Per_Community = number_of_Water_Points_Per_Community;
	}

	public Map<String, Integer> getWaterPointPerCommunityMap() {
		return waterPointPerCommunityMap;
	}

	public void setWaterPointPerCommunityMap(Map<String, Integer> waterPointPerCommunityMap) {
		this.waterPointPerCommunityMap = waterPointPerCommunityMap;
	}

	public List<String> getWaterPointPerCommunityKeyList() {
		return waterPointPerCommunityKeyList;
	}

	public void setWaterPointPerCommunityKeyList(List<String> waterPointPerCommunityKeyList) {
		this.waterPointPerCommunityKeyList = waterPointPerCommunityKeyList;
	}

	public Map<String, Integer> getFunctionalWaterPointsMap() {
		return functionalWaterPointsMap;
	}

	public void setFunctionalWaterPointsMap(Map<String, Integer> functionalWaterPointsMap) {
		this.functionalWaterPointsMap = functionalWaterPointsMap;
	}

	public List<String> getFunctionalWaterPointsKeyList() {
		return functionalWaterPointsKeyList;
	}

	public void setFunctionalWaterPointsKeyList(List<String> functionalWaterPointsKeyList) {
		this.functionalWaterPointsKeyList = functionalWaterPointsKeyList;
	}

	public Map<String, Integer> getBrokenWaterPointsRankMap() {
		return brokenWaterPointsRankMap;
	}

	public void setBrokenWaterPointsRankMap(Map<String, Integer> brokenWaterPointsRankMap) {
		this.brokenWaterPointsRankMap = brokenWaterPointsRankMap;
	}

	public List<String> getBrokenWaterPointsRankKeyList() {
		return brokenWaterPointsRankKeyList;
	}

	public void setBrokenWaterPointsRankKeyList(List<String> brokenWaterPointsRankKeyList) {
		this.brokenWaterPointsRankKeyList = brokenWaterPointsRankKeyList;
	}

	public List<String> getBrokenWaterPointsPerCommunityKeyList() {
		return brokenWaterPointsPerCommunityKeyList;
	}

	public void setBrokenWaterPointsPerCommunityKeyList(List<String> brokenWaterPointsPerCommunityKeyList) {
		this.brokenWaterPointsPerCommunityKeyList = brokenWaterPointsPerCommunityKeyList;
	}

	public Map<String, Integer> getBrokenWaterPointsPerCommunityMap() {
		return brokenWaterPointsPerCommunityMap;
	}

	public void setBrokenWaterPointsPerCommunityMap(Map<String, Integer> brokenWaterPointsPerCommunityMap) {
		this.brokenWaterPointsPerCommunityMap = brokenWaterPointsPerCommunityMap;
	}
}
