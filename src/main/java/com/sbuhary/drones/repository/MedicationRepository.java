package com.sbuhary.drones.repository;

import org.springframework.data.repository.CrudRepository;

import com.sbuhary.drones.entity.Medication;

public interface MedicationRepository extends CrudRepository<Medication, Long> {

}
