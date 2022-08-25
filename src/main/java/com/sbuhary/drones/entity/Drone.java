package com.sbuhary.drones.entity;

import java.io.Serializable;
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
@Table(name = "drones")
@Data
public class Drone implements Serializable {

	private static final long serialVersionUID = -553513249523183514L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "serial_number", nullable = false, unique = true, length = 100)
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

	@OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Medication> medications;
	
	public void addMedication(Medication medication){
		medications.add(medication);
        medication.setDrone(this);
    }

    public void removeMedication(Medication medication){
    	medications.remove(medication);
        medication.setDrone(null);
    }
}