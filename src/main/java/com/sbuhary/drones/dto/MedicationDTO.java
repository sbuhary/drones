package com.sbuhary.drones.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.web.multipart.MultipartFile;

import com.sbuhary.drones.entity.Drone;
import com.sbuhary.drones.entity.Model;

import lombok.Data;

@Data
public class MedicationDTO {

	@NotNull
	@NotBlank(message = "Medication name is mandatory")
	@Pattern(regexp = "^[a-zA-Z0-9_-]*$",
            message = "allowed only letters, numbers, ‘-‘, ‘_’")
	private String name;

	@NotNull
	//@NotBlank(message = "Medication weight is mandatory")
	@PositiveOrZero
	private int weight;

	@NotNull
	@NotBlank(message = "Medication code is mandatory")
	@Pattern(regexp = "^[A-Z0-9_]*$",
            message = "allowed only upper case letters, underscore and numbers")
	private String code;

	private MultipartFile image;

	private Drone drone;
}