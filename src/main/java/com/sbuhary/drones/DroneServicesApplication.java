package com.sbuhary.drones;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sbuhary.drones.entity.Drone;
import com.sbuhary.drones.entity.Model;
import com.sbuhary.drones.entity.State;
import com.sbuhary.drones.repository.DroneRepository;
import com.sbuhary.drones.repository.MedicationRepository;

/**
 * 
 * @author SBUHARY
 *
 */
@SpringBootApplication
public class DroneServicesApplication {

	public static void main(String[] args) {

		SpringApplication.run(DroneServicesApplication.class, args);
	}

	/**
	 * pre-load some drone details
	 * 
	 * @param droneRepository
	 * @param medicationRepository
	 * @return
	 */
	@Bean
	public CommandLineRunner demo(DroneRepository droneRepository, MedicationRepository medicationRepository) {
		return args -> {

			Drone drone1 = new Drone();
			drone1.setSerialNumber("10000001");
			drone1.setModel(Model.Middleweight);
			drone1.setWeightLimit(250);
			drone1.setBatteryCapacity(90);
			drone1.setState(State.LOADING);
			droneRepository.save(drone1);

			Drone drone2 = new Drone();
			drone2.setSerialNumber("10000002");
			drone2.setModel(Model.Heavyweight);
			drone2.setWeightLimit(450);
			drone2.setBatteryCapacity(85);
			drone2.setState(State.LOADING);
			droneRepository.save(drone2);

			Drone drone3 = new Drone();
			drone3.setSerialNumber("10000005");
			drone3.setModel(Model.Lightweight);
			drone3.setWeightLimit(100);
			drone3.setBatteryCapacity(60);
			drone3.setState(State.LOADING);
			droneRepository.save(drone3);
		};
	}
}