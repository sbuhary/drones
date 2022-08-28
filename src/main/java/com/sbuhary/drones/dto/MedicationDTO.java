package com.sbuhary.drones.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.web.multipart.MultipartFile;

import com.sbuhary.drones.entity.Drone;

import lombok.Data;

/**
 * 
 * @author SBUHARY
 *
 */
@Data
public class MedicationDTO {

	@NotNull
	@NotBlank(message = "Medication name is mandatory")
	@Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "Name allows only letters, numbers, hyphens and underscores")
	private String name;

	@NotNull
	@PositiveOrZero
	private int weight;

	@NotNull
	@NotBlank(message = "Medication code is mandatory")
	@Pattern(regexp = "^[A-Z0-9_]*$", message = "Code allows only upper case letters, underscore and numbers")
	private String code;

	private MultipartFile image;

	private Drone drone;
}