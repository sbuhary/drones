package com.sbuhary.drones.service.impl;

import java.util.ArrayList;
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
import com.sbuhary.drones.repository.MedicationRepository;
import com.sbuhary.drones.service.DroneService;

@Service
public class DroneServiceImpl implements DroneService {

	@Autowired
	private DroneRepository droneRepository;

	@Autowired
	private MedicationRepository medicationRepository;

	@Override
	public Drone registerDrone(DroneRegistrationDTO droneRegistrationDTO) {

		if (isDroneExists(droneRegistrationDTO.getSerialNumber())) {
			throw new AlreadyExistsException(
					"Drone with serial number '" + droneRegistrationDTO.getSerialNumber() + "' already exists");
		}

		Drone drone = new Drone();
		drone.setSerialNumber(droneRegistrationDTO.getSerialNumber());
		drone.setModel(Enum.valueOf(Model.class, droneRegistrationDTO.getModel()));
		drone.setWeightLimit(droneRegistrationDTO.getWeightLimit());
		// drone.setCurrentWeight(0);
		drone.setBatteryCapacity(droneRegistrationDTO.getBatteryCapacity());
		drone.setState(State.IDLE);

		return droneRepository.save(drone);
	}

	@Override
	public Drone findDroneBySerialNumber(String serialNumber) {

		if (!isDroneExists(serialNumber)) {
			throw new NotFoundException("Drone with serial number '" + serialNumber + "' does not exist");
		}

		return droneRepository.findBySerialNumber(serialNumber);
	}

	@Override
	public List<Drone> availableDrones() {

		List<Drone> drones = new ArrayList<Drone>();
		for (Drone drone : droneRepository.findAll()) {
			if (drone.retrieveCurrentWeight() < drone.getWeightLimit()) {
				drones.add(drone);
			}
		}
		
		return drones;
	}

	@Override
	public boolean isDroneExists(String serialNumber) {

		return droneRepository.existsDroneBySerialNumber(serialNumber);
	}
}