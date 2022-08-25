package com.sbuhary.drones.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sbuhary.drones.entity.AuditEventLog;
import com.sbuhary.drones.entity.Drone;
import com.sbuhary.drones.repository.AuditEventLogRepository;
import com.sbuhary.drones.repository.DroneRepository;
import com.sbuhary.drones.service.AuditEventService;

import lombok.extern.slf4j.Slf4j;

@Service
@EnableScheduling
@Slf4j
public class AuditEventServiceImpl implements AuditEventService {

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Autowired
	private DroneRepository droneRepository;
	
	@Autowired
	AuditEventLogRepository auditEventLogRepository;
	
	@Scheduled(cron="${cron-expression}")
	public void scheduleTaskWithCronExpression() {
		
		// List<AuditEventLog> auditEventLogs = new ArrayList<AuditEventLog>();
		//Iterable<Drone> drones = droneRepository.findAll();
		for (Drone drone : droneRepository.findAll()) {
			
			AuditEventLog auditEventLog = new AuditEventLog();
			auditEventLog.setSerialNumber(drone.getSerialNumber());
			auditEventLog.setBatteryCapacity(drone.getBatteryCapacity());
			auditEventLog.setTimestamp(dateTimeFormatter.format(LocalDateTime.now()));
			auditEventLogRepository.save(auditEventLog);
			log.info("Cron Task :: Execution Time - {}", auditEventLog);
		}
		// return ResponseEntity.status(HttpStatus.OK).body(drones);
		
	    
	}
}
