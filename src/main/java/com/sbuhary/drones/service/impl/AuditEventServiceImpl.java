package com.sbuhary.drones.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sbuhary.drones.entity.AuditEventLog;
import com.sbuhary.drones.entity.Drone;
import com.sbuhary.drones.repository.AuditEventLogRepository;
import com.sbuhary.drones.repository.DroneRepository;
import com.sbuhary.drones.service.AuditEventService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author SBUHARY
 *
 */
@Service
@EnableScheduling
@Slf4j
public class AuditEventServiceImpl implements AuditEventService {

	// private static final DateTimeFormatter dateTimeFormatter =
	// DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private DroneRepository droneRepository;

	@Autowired
	AuditEventLogRepository auditEventLogRepository;

	@Scheduled(cron = "${audit-event.battery-level.cron-expression}")
	@Override
	public void checkDroneBatteryLevels() {

		for (Drone drone : droneRepository.findAll()) {

			AuditEventLog auditEventLog = new AuditEventLog();
			auditEventLog.setSerialNumber(drone.getSerialNumber());
			auditEventLog.setBatteryCapacity(drone.getBatteryCapacity());
			// auditEventLog.setTimestamp(dateTimeFormatter.format(LocalDateTime.now()));
			auditEventLog.setTimestamp(LocalDateTime.now().toString());

			auditEventLogRepository.save(auditEventLog);

			log.info("Drone battery level - {}", auditEventLog);
		}
	}
}