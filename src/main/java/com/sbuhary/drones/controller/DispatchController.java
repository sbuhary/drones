package com.sbuhary.drones.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.sbuhary.drones.dto.DroneRegistrationDTO;
import com.sbuhary.drones.dto.MedicationDTO;
import com.sbuhary.drones.entity.Drone;
import com.sbuhary.drones.entity.Medication;
import com.sbuhary.drones.repository.DroneRepository;
import com.sbuhary.drones.repository.MedicationRepository;
import com.sbuhary.drones.service.DroneService;
import com.sbuhary.drones.service.MedicationService;

@Controller
@RequestMapping("api/v1/drones")
public class DispatchController {

	@Autowired
	private DroneRepository droneRepository;
	
	@Autowired
	private MedicationRepository medicationRepository;
	
	@Autowired
	private DroneService droneService;

	@Autowired
	private MedicationService medicationService;

	@PostMapping
	public ResponseEntity<Void> registerDrone(@RequestBody DroneRegistrationDTO droneRegistrationDTO) {

		droneService.registerDrone(droneRegistrationDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/{serialNumber}/medication")
	public ResponseEntity<Void> loadDroneWithMedications(@PathVariable("serialNumber") String serialNumber,
			@RequestPart("medication") String medicationDTOStr, @RequestPart("file") MultipartFile file)
			throws Exception {

		Drone drone = droneRepository.findBySerialNumber(serialNumber);
		
		
		
		MedicationDTO medicationDTO = medicationService.getJson(medicationDTOStr);
		medicationDTO.setImage(file.getBytes());
		medicationDTO.setDrone(drone);
		
		int total = 0;
		for (int i = 0; i < drone.getMedications().size(); i++) {
			total = total + drone.getMedications().get(i).getWeight();
		}
		
		if (total + medicationDTO.getWeight() > drone.getWeightLimit()) {
			throw new Exception("more weight"); // change http code
		}
		
		medicationService.addMedication(medicationDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping(value = "/{serialNumber}/medication")
	public ResponseEntity<List<Medication>> loadedMedicationItems(@PathVariable("serialNumber") String serialNumber) {

		Drone drone = droneRepository.findBySerialNumber(serialNumber);
		return ResponseEntity.status(HttpStatus.OK).body(medicationRepository.findByDrone(drone));
	}

	@GetMapping(value = "/available")
	public ResponseEntity<List<Drone>> availableDrones() {
		List<Drone> drones = new ArrayList<Drone>();
		//Iterable<Drone> drones = droneRepository.findAll();
		for (Drone drone : droneRepository.findAll()) {
			
			int total = 0;
			for (int i = 0; i < drone.getMedications().size(); i++) {
				total = total + drone.getMedications().get(i).getWeight();
			}
			
			if (total < drone.getWeightLimit()) {
				drones.add(drone);
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(drones);
	}
	
	@GetMapping(value = "/{serialNumber}/batterylevel")
	public ResponseEntity<Integer> batterylevel(@PathVariable("serialNumber") String serialNumber) {

		Drone drone = droneRepository.findBySerialNumber(serialNumber);
		return ResponseEntity.status(HttpStatus.OK).body(drone.getBatteryCapacity());
	}
}
