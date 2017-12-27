package com.jeztech.repomanager.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class CommonUtils {
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static final SimpleDateFormat DATE_FORMAT_SN = new SimpleDateFormat(
			"yyyyMMdd");

	public static String getCurrentDate() {
		return DATE_FORMAT.format(new Date());
	}

	public static String getCurrentDateSn() {
		return DATE_FORMAT_SN.format(new Date());
	}

	/**
	 * 取得前一天的目期序列号
	 * 
	 * @return
	 */
	public static String getYesterdayDateSn() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		return DATE_FORMAT_SN.format(c.getTime());
	}

	/**
	 * 格式化所有的货币格式字串
	 * 
	 * @param money
	 * @return
	 */
	public static String convertMoneyString(String money) {
		Double moneyDouble = 0.0d;
		if (StringUtils.isNotEmpty(money)) {
			moneyDouble = Double.valueOf(money);
		}
		return String.format("%.2f", moneyDouble);
	}
	
	/**
	 * 计算两个货币数据的和
	 * @return
	 */
	public static String calculatePriceAdd(String priceLeft, String priceRight) {
		Double money = Double.valueOf(priceLeft) + Double.valueOf(priceRight);
		return CommonUtils.convertMoneyString(String.valueOf(money));
	}
	
	/**
	 * 计算两个货币数据的差 left - right
	 * @return
	 */
	public static String calculatePriceSub(String priceLeft, String priceRight) {
		Double money = Double.valueOf(priceLeft) - Double.valueOf(priceRight);
		return CommonUtils.convertMoneyString(String.valueOf(money));
	}

}
