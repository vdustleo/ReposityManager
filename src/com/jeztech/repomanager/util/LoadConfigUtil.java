package com.jeztech.repomanager.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class LoadConfigUtil {
	protected final static Logger logger = Logger.getLogger(LoadConfigUtil.class);

	public static Properties getConfig(String fileName) {
		logger.info("get config of file" + fileName);
		String filepath = System.getProperty(SystemConfig.SYS_LICENSE_ID);
		String propFilePath = FilenameUtils.concat(filepath, fileName);
		Properties prop = null;
		InputStreamReader is = null;
		try {
			logger.info("System file path is :" + propFilePath);
			is = new FileReader(propFilePath);
			prop = new Properties();
			prop.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
		}

		return prop;
	}

}
