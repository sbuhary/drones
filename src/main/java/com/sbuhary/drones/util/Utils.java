package com.sbuhary.drones.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.sbuhary.drones.constant.Constants;

public class Utils {

	private Utils() {
	}

	/**
	 * save image file in the system disk and return file
	 * 
	 * @param serialNo
	 * @param code
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 */
	public static File createMedicationImageFile(String serialNo, String code, MultipartFile multipartFile)
			throws IOException {

		String filename = serialNo + "_" + code + "_" + System.currentTimeMillis() + "_"
				+ multipartFile.getOriginalFilename();

		File fileStore = new File(Constants.MEDICATION_IMAGE_LOCATION);
		if (!fileStore.exists()) {
			fileStore.mkdir();
		}

		File file = new File(Constants.MEDICATION_IMAGE_LOCATION + filename);
		file.createNewFile();
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(multipartFile.getBytes());
		}

		return file;
	}
}