package com.sbuhary.drones.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbuhary.drones.dto.DroneRegistrationDTO;
import com.sbuhary.drones.entity.Drone;
import com.sbuhary.drones.entity.Model;
import com.sbuhary.drones.entity.State;
import com.sbuhary.drones.exception.AlreadyExistsException;
import com.sbuhary.drones.exception.NotFoundException;
import com.sbuhary.drones.repository.DroneRepository;
import com.sbuhary.drones.service.DroneService;

/**
 * 
 * @author SBUHARY
 *
 */
@Service
public class DroneServiceImpl implements DroneService {

	@Autowired
	private DroneRepository droneRepository;

	/**
	 * save drone registration details
	 */
	@Override
	public Drone register(DroneRegistrationDTO droneRegistrationDTO) {

		if (isDroneExists(droneRegistrationDTO.getSerialNumber())) {
			throw new AlreadyExistsException(
					"Drone with serial number '" + droneRegistrationDTO.getSerialNumber() + "' already exists");
		}

		Drone drone = new Drone();
		drone.setSerialNumber(droneRegistrationDTO.getSerialNumber());
		// drone.setModel(Enum.valueOf(Model.class, droneRegistrationDTO.getModel()));
		drone.setModel(Model.getModel(droneRegistrationDTO.getWeightLimit()));
		drone.setWeightLimit(droneRegistrationDTO.getWeightLimit());
		drone.setCurrentWeight(0);
		drone.setBatteryCapacity(droneRegistrationDTO.getBatteryCapacity());
		drone.setState(droneRegistrationDTO.getBatteryCapacity() < 25 ? State.IDLE : State.LOADING);
		drone.setMedications(Collections.emptyList());

		return droneRepository.save(drone);
	}

	/**
	 * find drone using its serial number
	 */
	@Override
	public Drone findDroneBySerialNumber(String serialNumber) {

		if (!isDroneExists(serialNumber)) {
			throw new NotFoundException("Drone with serial number '" + serialNumber + "' does not exist");
		}

		return droneRepository.findBySerialNumber(serialNumber);
	}

	/**
	 * find all drones which has more space to load medications (available drones)
	 */
	@Override
	public List<Drone> retrieveAvailableDrones() {

		List<Drone> drones = new ArrayList<Drone>();
		for (Drone drone : droneRepository.findAll()) {
			if (drone.retrieveCurrentWeight() < drone.getWeightLimit()) {
				drones.add(drone);
			}
		}

		return drones;
	}

	/**
	 * check if drone exists using its serial number
	 */
	@Override
	public boolean isDroneExists(String serialNumber) {

		return droneRepository.existsDroneBySerialNumber(serialNumber);
	}
}