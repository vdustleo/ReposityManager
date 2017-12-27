package com.jeztech.repomanager.model;


import lombok.Data;

@Data
public class FarmerInfo {

	// 存储编号, 该处理sn设计成序数, 存储使用String, 
	// 以防止未来可能的格式化需求
	private String storageSn = "";
	
	// 果农信息
	private String farmerName = "";
	
	// 果农联系电话
	private String farmerTel = "";
	
	// 支付单价
	private String unitPrice = "";
	
}
