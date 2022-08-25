package com.sbuhary.drones.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbuhary.drones.dto.DroneRegistrationDTO;
import com.sbuhary.drones.entity.Drone;
import com.sbuhary.drones.entity.Model;
import com.sbuhary.drones.entity.State;
import com.sbuhary.drones.repository.DroneRepository;
import com.sbuhary.drones.repository.MedicationRepository;
import com.sbuhary.drones.service.DroneService;

@Service
public class DroneServiceImpl implements DroneService {

	@Autowired
	private DroneRepository droneRepository;
	
	@Autowired
	private MedicationRepository medicationRepository;

	@Override
	public void registerDrone(DroneRegistrationDTO droneRegistrationDTO) {
		
		Drone drone = new Drone();
		drone.setSerialNumber(droneRegistrationDTO.getSerialNumber());
		drone.setModel(droneRegistrationDTO.getModel());
		drone.setWeightLimit(droneRegistrationDTO.getWeightLimit());
		drone.setBatteryCapacity(droneRegistrationDTO.getBatteryCapacity());
		drone.setState(State.IDLE);

		droneRepository.save(drone);
	}
	
	/*@Override
	public Drone findDroneBySerialNumber(String serialNumber) {
		return droneRepository.findBySerialNumber(serialNumber);
	}*/
}
