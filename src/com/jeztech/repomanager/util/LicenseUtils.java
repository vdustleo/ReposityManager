package com.jeztech.repomanager.util;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;


public class LicenseUtils {
	/**
	 * 永久过期时间
	 */
	private static final String  OVERDUE_TIME = "2015-1-1";
	protected final static Logger logger = Logger.getLogger(LicenseUtils.class);
	
	public static Boolean checkValid(String _deadDate){
		Calendar rightNow = Calendar.getInstance();
		DateTime deadlineDate = null;
		try {
			deadlineDate = DateTime.parse(_deadDate);
		} catch (Exception e) {
			deadlineDate = DateTime.parse(OVERDUE_TIME);
			logger.error("datetime format error with exception" + e);
		}
		Calendar deadDay = Calendar.getInstance();
		deadDay.setTime(deadlineDate.toDate());
		return deadDay.after(rightNow);
	}
}
