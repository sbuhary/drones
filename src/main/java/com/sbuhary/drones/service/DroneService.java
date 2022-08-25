package com.sbuhary.drones.service;

import com.sbuhary.drones.dto.DroneRegistrationDTO;
import com.sbuhary.drones.entity.Drone;

public interface DroneService {

	public void registerDrone(DroneRegistrationDTO droneRegistrationDTO);
	
	// public Drone findDroneBySerialNumber(String serialNumber);

}
