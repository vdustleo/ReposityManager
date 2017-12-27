package com.jeztech.repomanager.model;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import lombok.Data;

@Data
public class PayInfo {

	// 存储编号
	private String storageSn = "";
	
	// 果农信息
	private String farmerName = "";
	
	/**
	 * 付款的序列: 0 代表初始化, 不是支付的记录， 支持记录依次递增
	 * 
	 * farmerName | goodsCode | psn | totalPrice | xxxx | xxx | xxx |
	 * 张三       | S100      | 0   |   5000     | xxxx | xxx | xxx |
	 * 张三       | S100      | 1   |   5000     |  xxx | xxx | xxx |  
	 * 张三       | S100      | ...   |   5000     |  xxx | xxx | xxx |  
	 * 张三       | S100      | 7   |   5000     |  xxx | xxx | xxx |  
	 * 张三       | S100      | 8   |   5000     |  xxx | xxx | xxx |  
	 * 
	 * 其中 psn = 0, 代表初始化的记录
	 *      psn = 8, 代表当前的记录
	 *      psn 1 ~ 8, 代表所有的支付记录
 	 */
	private Integer psn = 0;
	
	// 总价
	private String totalPrice = "";

	// 支付金额
	private String payPrice = "";
	
	// 欠金额
	private String owePrice = "";
	
	// 支付时间
	private Date payTime;
	
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getPayTime() {
		return payTime;
	}
}
