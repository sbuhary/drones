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

	Drone register(DroneRegistrationDTO droneRegistrationDTO);

	Drone findDroneBySerialNumber(String serialNumber);

	List<Drone> retrieveAvailableDrones();

	boolean isDroneExists(String serialNumber);
}