package com.sbuhary.drones.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "MEDICATIONS")
@Data
public class Medication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "weight", nullable = false)
	private int weight;
	
	@Column(name = "code", nullable = false)
	private String code;
	
	@Lob
	@Column(name = "image", nullable = false, columnDefinition="BLOB")
	private byte[] image;
	
	@ManyToOne
	@JoinColumn(name="drone_id", nullable=false)
	private Drone drone;

}
