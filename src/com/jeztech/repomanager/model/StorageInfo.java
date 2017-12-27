package com.jeztech.repomanager.model;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import lombok.Data;

@Data
public class StorageInfo {
	
	/**
	 * sid
	 */
	private Integer sid = 0;
	
	// 存储编号
	private String storageSn = "";
	
	// 果农信息
	private String farmerName = "";
	
	// 苹果类型 
	private String appleType = "";
	
	// 苹果尺寸
	private String appleSize = "";
	
	// 仓库编号
	private String repoCode = "";
	
	// 仓库隔档编号
	private Integer repoSep = 0;
	
	// 货物数量
	private Integer goodsCount = 0;
	
	// 单价
	private String unitPrice = "";
	
	// 总价
	private String totalPrice = "";
	
	// 操作时间(出库，或者入库时间)
	private Date operateTime;
	
	// 记录类型
	private Integer type = StorageType.DEFAULT;

	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getOperateTime() {
		return operateTime;
	}
}
