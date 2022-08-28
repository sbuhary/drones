package com.sbuhary.drones.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sbuhary.drones.dto.DroneRegistrationDTO;
import com.sbuhary.drones.dto.ResponseDTO;
import com.sbuhary.drones.entity.Drone;
import com.sbuhary.drones.entity.Medication;
import com.sbuhary.drones.service.DroneService;
import com.sbuhary.drones.service.MedicationService;

@RestController
@RequestMapping("/api/v1/drone")
public class DispatchController {

	@Autowired
	private DroneService droneService;

	@Autowired
	private MedicationService medicationService;

	@PostMapping
	public ResponseEntity<ResponseDTO<Drone>> registerDrone(
			@Valid @RequestBody DroneRegistrationDTO droneRegistrationDTO) {

		Drone drone = droneService.registerDrone(droneRegistrationDTO);
		ResponseDTO<Drone> response = new ResponseDTO<Drone>(HttpStatus.CREATED.value(),
				"Drone successfully registered", drone);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/{serial_no}/medication")
	public ResponseEntity<ResponseDTO<Medication>> loadDroneWithMedications(
			@PathVariable("serial_no") String serialNumber, @RequestPart("medication_json") String medicationJson,
			@RequestPart("file") MultipartFile file) throws Exception {

		Medication medication = medicationService.addMedication(serialNumber, medicationJson, file);
		ResponseDTO<Medication> response = new ResponseDTO<Medication>(HttpStatus.CREATED.value(),
				"Medication successfully loaded", medication);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping(value = "/{serial_no}/medication")
	public ResponseEntity<ResponseDTO<List<Medication>>> loadedMedicationItems(
			@PathVariable("serial_no") String serialNumber) {

		List<Medication> medicationList = medicationService.findByDrone(serialNumber);
		ResponseDTO<List<Medication>> response = new ResponseDTO<List<Medication>>(HttpStatus.OK.value(),
				HttpStatus.OK.getReasonPhrase(), medicationList);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(value = "/available")
	public ResponseEntity<ResponseDTO<List<Drone>>> availableDrones() {

		List<Drone> droneList = droneService.availableDrones();
		ResponseDTO<List<Drone>> response = new ResponseDTO<List<Drone>>(HttpStatus.OK.value(),
				HttpStatus.OK.getReasonPhrase(), droneList);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(value = "/{serial_no}/battery_level")
	public ResponseEntity<ResponseDTO<Integer>> batterylevel(@PathVariable("serial_no") String serialNumber) {

		Drone drone = droneService.findDroneBySerialNumber(serialNumber);
		ResponseDTO<Integer> response = new ResponseDTO<Integer>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				drone.getBatteryCapacity());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}