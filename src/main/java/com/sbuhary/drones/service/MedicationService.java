package com.sbuhary.drones.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sbuhary.drones.entity.Medication;

/**
 * 
 * @author SBUHARY
 *
 */
public interface MedicationService {

	Medication addMedication(String serialNumber, String medicationDTO, MultipartFile file) throws IOException;

	List<Medication> findByDrone(String serialNumber);
}