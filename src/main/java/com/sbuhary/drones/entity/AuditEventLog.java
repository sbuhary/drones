package com.sbuhary.drones.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 
 * @author SBUHARY
 *
 */
@Entity
@Table(name = "auditeventlogs")
@Data
public class AuditEventLog implements Serializable {

	private static final long serialVersionUID = 4293714240504653307L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "serial_number", nullable = false, length = 100)
	private String serialNumber; // 100max

	@Column(name = "battery_capacity_percentage", nullable = false)
	private int batteryCapacity; // percentage

	@Column(name = "date_time", nullable = false)
	private String timestamp; // percentage
}