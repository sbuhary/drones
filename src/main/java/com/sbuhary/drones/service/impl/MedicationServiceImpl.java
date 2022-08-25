package com.sbuhary.drones.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbuhary.drones.dto.MedicationDTO;
import com.sbuhary.drones.entity.Drone;
import com.sbuhary.drones.entity.Medication;
import com.sbuhary.drones.repository.DroneRepository;
import com.sbuhary.drones.repository.MedicationRepository;
import com.sbuhary.drones.service.MedicationService;

@Service
public class MedicationServiceImpl implements MedicationService {

	@Autowired
	private DroneRepository droneRepository;
	
	@Autowired
	private MedicationRepository medicationRepository;
	
	@Override
	public MedicationDTO getJson(String medicationDTO) {

		MedicationDTO userJson = new MedicationDTO();
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			userJson = objectMapper.readValue(medicationDTO, MedicationDTO.class);
		} catch (IOException err) {
			System.out.printf("Error", err.toString());
		}
		
		//userJson.setImage(file.getBytes());
		//userJson.setDrone(droneRepository.findBySerialNumber(serialNumber));
		
		return userJson;

	}

	@Override
	public void addMedication(MedicationDTO medicationDTO) {
		Medication medication = new Medication();
		medication.setName(medicationDTO.getName());
		medication.setWeight(medicationDTO.getWeight());
		medication.setCode(medicationDTO.getCode());
		medication.setImage(medicationDTO.getImage());
		medication.setDrone(medicationDTO.getDrone());
		medicationRepository.save(medication);
		
	}

}
