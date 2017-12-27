package com.jeztech.repomanager.interceptor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.jeztech.repomanager.util.SystemConfig;


public class ApplicationListener implements ServletContextListener {
	protected final static Logger logger = Logger.getLogger(ApplicationListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("Server started!");
		
		// 设置系统的环境目录
		String basePath = arg0.getServletContext().getRealPath("/");
		String path = FilenameUtils.concat(basePath, SystemConfig.LICENSE_FILEPATH);
		
		logger.info("Server started! license file path is:" + path);
		System.setProperty(SystemConfig.SYS_LICENSE_ID, path);
		
		// 加载系统配置
		SystemConfig.getInstance().loadConfig();
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("Server destoryed!");
	}

}
