package com.jeztech.repomanager.util;

import java.net.InetAddress;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.util.Base64;
import org.apache.log4j.Logger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemConfig {
	
	protected final static Logger logger = Logger.getLogger(SystemConfig.class);

	/**
	 *  最后期限，license中的约束字串
	 */
	private static final String DEAD_DAY = "DEAD_DAY";

	/**
	 *  最大仓库数量，license中的约束字串
	 */
	private static final String MAX_REPO = "MAX_REPO";

	/**
	 * 配置license路径键值
	 */
	public static final String SYS_LICENSE_ID = "SYS_LICENSE_ID";

	/**
	 * 配置license路径
	 */
	public static final String LICENSE_FILEPATH = "license";

	/**
	 * license配置文件
	 */
	static final String LICENSE_FILENAME = "license.properties";

	/**
	 * 截止日期
	 */
	private String deadlineDate = "2015-1-1";
	
	/**
	 * 本机的ip地址
	 */
	private String localIp = "127.0.0.1";

	/**
	 * 最大仓库容量
	 */
	private Integer maxRepoSize = 10;

	public static SystemConfig getInstance() {
		return _instance;
	}

	private static SystemConfig _instance = new SystemConfig();

	private SystemConfig() {
		
		try {
			InetAddress addr = InetAddress.getLocalHost();
			localIp = addr.getHostAddress().toString();
		} catch (Exception e) {
			logger.error("SystemConfig init failed.");
		}
	}

	public void loadConfig() {

		// 加载license文件配置
		try {
			
			Properties licenseProp = LoadConfigUtil.getConfig(LICENSE_FILENAME);
			String rawSerial = licenseProp.getProperty("serial");
			
			// 解密字串
			String serial = new String(Base64.decodeBase64(rawSerial), "UTF-8");
			
			String serialParts[] = StringUtils.split(serial, ":/");
			if (serialParts.length == 4 && DEAD_DAY.equals(serialParts[0])
					&& MAX_REPO.equals(serialParts[2])) {
				deadlineDate = serialParts[1];
				maxRepoSize = Integer.parseInt(serialParts[3].trim());
			}
		} catch (Exception e) {
			logger.error("loadConfig failed with exception: " + e);
		}

	}

}
