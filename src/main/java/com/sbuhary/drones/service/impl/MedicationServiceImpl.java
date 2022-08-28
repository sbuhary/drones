package com.sbuhary.drones.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbuhary.drones.constant.Constants;
import com.sbuhary.drones.dto.MedicationDTO;
import com.sbuhary.drones.entity.Drone;
import com.sbuhary.drones.entity.Medication;
import com.sbuhary.drones.exception.LimitExceededException;
import com.sbuhary.drones.exception.NotFoundException;
import com.sbuhary.drones.repository.DroneRepository;
import com.sbuhary.drones.repository.MedicationRepository;
import com.sbuhary.drones.service.DroneService;
import com.sbuhary.drones.service.MedicationService;
import com.sbuhary.drones.util.Utils;

/**
 * 
 * @author SBUHARY
 *
 */
@Service
public class MedicationServiceImpl implements MedicationService {

	@Autowired
	private DroneRepository droneRepository;

	@Autowired
	private MedicationRepository medicationRepository;

	@Autowired
	private DroneService droneService;

	@Autowired
	private Validator validator;

	@Override
	public Medication addMedication(String serialNumber, String medicationJson, MultipartFile multipartFile)
			throws IOException {

		Drone drone = droneRepository.findBySerialNumber(serialNumber);
		if (drone == null) {
			throw new NotFoundException("Drone with serial number '" + serialNumber + "' does not exist");
		}

		ObjectMapper objectMapper = new ObjectMapper();
		MedicationDTO medicationDTO = objectMapper.readValue(medicationJson, MedicationDTO.class);

		Set<ConstraintViolation<MedicationDTO>> violations = validator.validate(medicationDTO);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}

		if (drone.retrieveCurrentWeight() + medicationDTO.getWeight() > drone.getWeightLimit()) {
			throw new LimitExceededException(
					"Maximum more weight allowed is " + (drone.getWeightLimit() - drone.retrieveCurrentWeight())
							+ " grams due to the weight limit of " + drone.getWeightLimit() + " grams");
		}

		File fileStore = new File(Constants.MEDICATION_IMAGE_LOCATION);
		if (!fileStore.exists()) {
			fileStore.mkdir();
		}

		Medication medication = new Medication();
		medication.setName(medicationDTO.getName());
		medication.setWeight(medicationDTO.getWeight());
		medication.setCode(medicationDTO.getCode());

		File file = Utils.createMedicationImageFile(serialNumber, medicationDTO.getCode(), multipartFile);
		medication.setImage(file.getName());

		drone.setCurrentWeight(drone.retrieveCurrentWeight() + medicationDTO.getWeight());
		medication.setDrone(drone);

		return medicationRepository.save(medication);
	}

	@Override
	public List<Medication> findByDrone(String serialNumber) {

		Drone drone = droneService.findDroneBySerialNumber(serialNumber);

		return medicationRepository.findByDrone(drone);
	}
}