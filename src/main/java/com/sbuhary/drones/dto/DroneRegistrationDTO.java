package com.sbuhary.drones.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 
 * @author SBUHARY
 *
 */
@Data
public class DroneRegistrationDTO {

	@NotNull
	@NotBlank(message = "Serial Number is mandatory")
	@Size(min = 5, max = 100)
	private String serialNumber;

	@NotNull
	@PositiveOrZero
	@Max(value = 500, message = "Maximum weight limit allowed for drone is {value} grams")
	private int weightLimit;

	@NotNull
	@PositiveOrZero
	@Max(value = 100, message = "Maximum battery capacity for drone should be {value} percentage")
	private int batteryCapacity; // percentage // also automate this
}