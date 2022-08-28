package com.sbuhary.drones.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * 
 * @author SBUHARY
 *
 */
@Entity
@Table(name = "medications")
@Data
public class Medication implements Serializable {

	private static final long serialVersionUID = 7108513806918957174L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "weight", nullable = false)
	private int weight;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "image", nullable = true)
	private String image;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "drone_id", nullable = true)
	private Drone drone;
}