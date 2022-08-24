package com.sbuhary.drones.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "DRONE")
@Data
public class Drone {
	
	// private static final int WEIGHT_LIMIT = 500;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "serial_number", length = 100, nullable = false)
	private String serialNumber; // 100max
	
	@Enumerated(EnumType.STRING)
	@Column(name = "model", nullable = false)
	private Model model;
	
	@Column(name = "weight_limit_gr", nullable = false)
	private int weightLimit; // 500gr max
	
	@Column(name = "battery_capacity_percentage", nullable = false)
	private int batteryCapacity; // percentage
	
	@Enumerated(EnumType.STRING)
	@Column(name = "state", nullable = false)
	private State state;
	
	@OneToMany(mappedBy = "drone", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Medication> medications;

	
}