package com.vsign.tech.rest.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	private static Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

	public static void uploadFile(String outputDir, String outputFileName, MultipartFile file) {
		if (!file.isEmpty()) {
			File outDir = new File(outputDir);
			if (!outDir.exists()) {
				outDir.mkdirs();
			}
			byte[] bytes;
			try {
				bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
				        new FileOutputStream(new File(outputDir + outputFileName)));
				stream.write(bytes);
				stream.flush();
				stream.close();
				LOGGER.info("File Uploaded successfuly!");
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}

		}
	}
	
	/*public static MultipartFile downloadFile(String filepath, String filename) {
		
			byte[] bytes;
			try {
				
				MultipartFile file =null;
				FileInputStream fileInputStream = new FileInputStream(filepath + filename);
				
				
				
				bytes = file.getBytes();
				//BufferedOutputStream stream = new BufferedOutputStream(
				    //    new FileOutputStream(new File(outputDir + outputFileName)));
				//stream.write(bytes);
				//stream.flush();
				//stream.close();
				LOGGER.info("File Uploaded successfuly!");
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}

		}*/
	
}
