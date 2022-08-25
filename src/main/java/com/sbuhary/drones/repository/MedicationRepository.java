package com.sbuhary.drones.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sbuhary.drones.entity.Drone;
import com.sbuhary.drones.entity.Medication;

public interface MedicationRepository extends CrudRepository<Medication, Long> {

	List<Medication> findByDrone(Drone drone);
}
