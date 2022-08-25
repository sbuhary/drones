package com.sbuhary.drones.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.sbuhary.drones.entity.Drone;
import com.sbuhary.drones.entity.Model;

import lombok.Data;

@Data
public class MedicationDTO {
	

	private String name;

	private int weight;

	private String code;

	private byte[] image;

	private Drone drone;

}
