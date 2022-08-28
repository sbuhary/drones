package com.sbuhary.drones.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sbuhary.drones.constant.Constants;
import com.sbuhary.drones.entity.Drone;
import com.sbuhary.drones.entity.State;
import com.sbuhary.drones.repository.DroneRepository;
import com.sbuhary.drones.service.BatterySimulatorService;

@Service
@EnableScheduling
public class BatterySimulatorServiceImpl implements BatterySimulatorService {

	@Autowired
	private DroneRepository droneRepository;

	/**
	 * periodic task to discharge the battery (simulation)
	 */
	@Scheduled(cron = "${battery-simulator.discharge.cron-expression}")
	@Override
	public void dischargeBattery() {

		for (Drone drone : droneRepository.findAll()) {
			if (drone.getBatteryCapacity() > 0) {
				drone.setBatteryCapacity(drone.getBatteryCapacity() - 1);
				if (drone.getBatteryCapacity() < Constants.MIN_BATTERY_LEVEL_FOR_LOADING) {
					drone.setState(State.IDLE); // also set the state to 'IDLE' if battery level is <25%
				}
				droneRepository.save(drone);
			}
		}
	}
}