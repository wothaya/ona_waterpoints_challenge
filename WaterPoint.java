package com.ona.data;

import java.io.Serializable;

/**
 * @author mnduati
 * @date May 28, 2016
 */
public class WaterPoint implements Serializable {


	private static final long serialVersionUID = 1L;

	String communities_villages;
	String water_functioning;

	public String getCommunities_villages() {
		return communities_villages;
	}

	public void setCommunities_villages(String communities_villages) {
		this.communities_villages = communities_villages;
	}

	public String getWater_functioning() {
		return water_functioning;
	}

	public void setWater_functioning(String water_functioning) {
		this.water_functioning = water_functioning;
	}

}
