package com.sbuhary.drones;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sbuhary.drones.entity.Drone;
import com.sbuhary.drones.entity.Medication;
import com.sbuhary.drones.entity.Model;
import com.sbuhary.drones.entity.State;
import com.sbuhary.drones.repository.DroneRepository;
import com.sbuhary.drones.repository.MedicationRepository;

@SpringBootApplication
public class DroneServicesApplication {

	public static void main(String[] args) {

		SpringApplication.run(DroneServicesApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(DroneRepository droneRepository, MedicationRepository medicationRepository) {
		return args -> {

			// create a new book
			Drone drone1 = new Drone();
			drone1.setSerialNumber("10000001");
			drone1.setModel(Model.MIDDLE_WEIGHT);
			drone1.setWeightLimit(250);
			drone1.setBatteryCapacity(90);
			drone1.setState(State.LOADING);

			// save the book
			droneRepository.save(drone1);

			// create and save new pages
			Medication med1 = new Medication();
			med1.setName("Med 1");
			med1.setWeight(50);
			med1.setCode("A10001");
			med1.setImage(new byte[123]);
			med1.setDrone(drone1);
			medicationRepository.save(med1);

			Medication med2 = new Medication();
			med2.setName("Med 2");
			med2.setWeight(60);
			med2.setCode("A10002");
			med2.setImage(new byte[123]);
			med2.setDrone(drone1);
			medicationRepository.save(med2);

			Medication med3 = new Medication();
			med3.setName("Med 3");
			med3.setWeight(70);
			med3.setCode("A10003");
			med3.setImage(new byte[123]);
			med3.setDrone(drone1);
			medicationRepository.save(med3);
		};
	}
}