package com.sbuhary.drones.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sbuhary.drones.dto.DroneRegistrationDTO;
import com.sbuhary.drones.entity.Drone;
import com.sbuhary.drones.entity.Medication;
import com.sbuhary.drones.exception.AlreadyExistsException;
import com.sbuhary.drones.exception.NotFoundException;
import com.sbuhary.drones.repository.DroneRepository;
import com.sbuhary.drones.service.impl.DroneServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DroneServiceTest {

	@Mock
	private DroneRepository droneRepository;

	@InjectMocks
	private DroneServiceImpl droneService;

	private DroneRegistrationDTO dto;

	@BeforeEach
	public void setup() {
		dto = new DroneRegistrationDTO();
		dto.setSerialNumber("1111111");
		dto.setWeightLimit(450);
		dto.setBatteryCapacity(68);
	}

	@DisplayName("JUnit test for register method")
	@Test
	public void givenDroneRegistrationDTOObject_whenRegister_thenReturnDroneObject() {

		given(droneRepository.save(any(Drone.class))).willReturn(new Drone());

		Drone savedDrone = droneService.register(dto);

		assertThat(savedDrone).isNotNull();
	}

	@DisplayName("JUnit test for register method which throws exception")
	@Test
	public void givenExistingDroneRegistrationDTO_whenRegister_thenThrowsException() {

		given(droneRepository.existsDroneBySerialNumber(anyString())).willReturn(true);

		org.junit.jupiter.api.Assertions.assertThrows(AlreadyExistsException.class, () -> {
			droneService.register(dto);
		});

		verify(droneRepository, never()).save(any(Drone.class));
	}

	@DisplayName("JUnit test for findDroneBySerialNumber method")
	@Test
	public void givenSerialNumber_whenFindDroneBySerialNumber_thenReturnDroneObject() {

		given(droneRepository.existsDroneBySerialNumber(anyString())).willReturn(true);
		given(droneRepository.findBySerialNumber(anyString())).willReturn(new Drone());

		Drone savedDrone = droneService.findDroneBySerialNumber("1111111");

		assertThat(savedDrone).isNotNull();
	}

	@DisplayName("JUnit test for findDroneBySerialNumber method which throws exception")
	@Test
	public void givenNotExistingSerialNumber_whenFindDroneBySerialNumber_thenThrowsException() {

		given(droneRepository.existsDroneBySerialNumber(anyString())).willReturn(false);

		org.junit.jupiter.api.Assertions.assertThrows(NotFoundException.class, () -> {
			droneService.findDroneBySerialNumber("1111111");
		});

		verify(droneRepository, never()).findBySerialNumber(anyString());
	}

	@DisplayName("JUnit test for retrieveAvailableDrones method which are available")
	@Test
	public void givenExistingDrones_whenRetrieveAvailableDrones_thenReturnListOfDroneObject_available() {

		Drone drone = new Drone();
		drone.setWeightLimit(250);

		List<Drone> drones = new ArrayList<Drone>();
		drones.add(drone);

		given(droneRepository.findAll()).willReturn(drones);

		List<Drone> savedDrones = droneService.retrieveAvailableDrones();

		assertThat(savedDrones).isNotNull();
		assertThat(savedDrones).isNotEmpty();
	}

	@DisplayName("JUnit test for retrieveAvailableDrones method which are unavailable")
	@Test
	public void givenExistingDrones_whenRetrieveAvailableDrones_thenReturnListOfDroneObject_unavailable() {

		Medication med = new Medication();
		med.setWeight(250);
		List<Medication> meds = new ArrayList<Medication>();
		meds.add(med);

		Drone drone = new Drone();
		drone.setWeightLimit(250);
		drone.setMedications(meds);

		List<Drone> drones = new ArrayList<Drone>();
		drones.add(drone);

		given(droneRepository.findAll()).willReturn(drones);

		List<Drone> savedDrone = droneService.retrieveAvailableDrones();

		assertThat(savedDrone).isNotNull();
		assertThat(savedDrone).isEmpty();
	}
}
