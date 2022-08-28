package com.sbuhary.drones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @author SBUHARY
 *
 */
@Data
@AllArgsConstructor
public class BatteryLevelDTO {

	private String serialNumber;
	private int batteryCapacity;
}