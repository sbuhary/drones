package com.sbuhary.drones.entity;

/**
 * 
 * @author SBUHARY
 *
 */
public enum Model {

	Lightweight, Middleweight, Cruiserweight, Heavyweight;

	public static Model getModel(int weight) {

		if (weight > 375) {
			return Heavyweight;
		} else if (weight <= 375 && weight > 250) {
			return Cruiserweight;
		} else if (weight <= 250 && weight > 125) {
			return Middleweight;
		} else if (weight <= 125) {
			return Lightweight;
		}

		return null;
	}
}