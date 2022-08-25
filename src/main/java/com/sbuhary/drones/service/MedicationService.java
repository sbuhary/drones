package com.sbuhary.drones.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.sbuhary.drones.dto.MedicationDTO;
import com.sbuhary.drones.entity.Drone;

public interface MedicationService {

	public MedicationDTO getJson(String medicationDTO);

	public void addMedication(MedicationDTO medicationDTO);
}
