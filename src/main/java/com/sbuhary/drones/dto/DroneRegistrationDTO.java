package com.sbuhary.drones.dto;

import org.springframework.stereotype.Component;

import com.sbuhary.drones.entity.Model;
import com.sbuhary.drones.entity.State;

import lombok.Data;

@Data
// @Component
public class DroneRegistrationDTO {

	private String serialNumber; // 100max
	private Model model;
	private int weightLimit; // 500gr max
	private int batteryCapacity; // percentage
	// private State state;
}
