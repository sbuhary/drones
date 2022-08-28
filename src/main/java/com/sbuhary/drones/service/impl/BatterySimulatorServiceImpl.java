package com.sbuhary.drones.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sbuhary.drones.entity.Drone;
import com.sbuhary.drones.repository.DroneRepository;
import com.sbuhary.drones.service.BatterySimulatorService;

@Service
@EnableScheduling
public class BatterySimulatorServiceImpl implements BatterySimulatorService {

	@Autowired
	private DroneRepository droneRepository;

	@Scheduled(cron = "${battery-simulator.discharge.cron-expression}")
	@Override
	public void dischargeBattery() {

		for (Drone drone : droneRepository.findAll()) {
			drone.setBatteryCapacity(drone.getBatteryCapacity() - 1);
			droneRepository.save(drone);
		}
	}
}