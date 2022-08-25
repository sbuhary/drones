package com.sbuhary.drones.repository;

import org.springframework.data.repository.CrudRepository;

import com.sbuhary.drones.entity.AuditEventLog;

public interface AuditEventLogRepository extends CrudRepository<AuditEventLog, Long> {

}
