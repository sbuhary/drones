package com.sbuhary.drones.constant;

import java.io.File;

/**
 * 
 * @author SBUHARY
 *
 */
public class Constants {

	public static final String MEDICATION_IMAGE_LOCATION = System.getProperty("user.home") + File.separator + ".drone"
			+ File.separator;

	public static final int MIN_BATTERY_LEVEL_FOR_LOADING = 25;
}