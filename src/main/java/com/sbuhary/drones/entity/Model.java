package com.sbuhary.drones.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Model {

	LIGHT_WEIGHT("Lightweight"), MIDDLE_WEIGHT("Middleweight"), CRUISER_WEIGHT("Cruiserweight"),
	HEAVY_WEIGHT("Heavyweight");

	private String value;

	private Model(String value) {
		this.value = value;
	}

	//@JsonValue
	public String getValue() {
		return value;
	}

	/*@JsonCreator
	public static Model fromText(String value) {
		for (Model model : Model.values()) {
			if (model.getValue().equals(value)) {
				return model;
			}
		}
		throw new IllegalArgumentException();
	}
	
	@Override
	public String toString() {
	    return value;
	}*/
}