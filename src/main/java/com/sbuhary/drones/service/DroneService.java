package com.sbuhary.drones.service;

import java.util.List;

import com.sbuhary.drones.dto.DroneRegistrationDTO;
import com.sbuhary.drones.entity.Drone;

/**
 * 
 * @author SBUHARY
 *
 */
public interface DroneService {

	Drone registerDrone(DroneRegistrationDTO droneRegistrationDTO);

	Drone findDroneBySerialNumber(String serialNumber);

	List<Drone> availableDrones();

	boolean isDroneExists(String serialNumber);
}