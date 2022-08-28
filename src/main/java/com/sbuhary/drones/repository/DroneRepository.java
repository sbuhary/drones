package com.sbuhary.drones.repository;

import org.springframework.data.repository.CrudRepository;

import com.sbuhary.drones.entity.Drone;

public interface DroneRepository extends CrudRepository<Drone, Long> {

	Drone findBySerialNumber(String serialNumber);
	
	boolean existsDroneBySerialNumber(String serialNumber);
}
