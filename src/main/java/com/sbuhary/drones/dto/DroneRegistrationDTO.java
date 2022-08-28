package com.sbuhary.drones.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.sbuhary.drones.entity.Model;
import com.sbuhary.drones.validation.constraint.ValueOfEnum;

import lombok.Data;

@Data
public class DroneRegistrationDTO {

	@NotNull
	@NotBlank(message = "Serial Number is mandatory")
	@Size(min = 5, max = 100)
	private String serialNumber;

	@NotNull
	@NotBlank(message = "Drone model is mandatory")
	@ValueOfEnum(enumClass = Model.class, message = "Model should be one of 'Lightweight', 'Middleweight', 'Cruiserweight' or 'Heavyweight")
	private String model;

	@NotNull
	@PositiveOrZero
	// @Min(value = 0, message = "Minimum weight limit should be {value} grams")
	@Max(value = 500, message = "Maximum weight limit allowed for drone is {value} grams")
	private int weightLimit;

	@NotNull
	@PositiveOrZero
	//@Min(value = 0, message = "Minimum battery capacity should be {value} percentage")
	@Max(value = 100, message = "Maximum battery capacity for drone should be {value} percentage")
	private int batteryCapacity; // percentage // also automate this
	// private State state;
}