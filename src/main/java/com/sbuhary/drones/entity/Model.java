package com.sbuhary.drones.entity;

public enum Model {

	LIGHT_WEIGHT("Lightweight"),
	MIDDLE_WEIGHT("Middleweight"),
	CRUISER_WEIGHT("Cruiserweight"),
	HEAVY_WEIGHT("Heavyweight");

	private String value;

	private Model(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}