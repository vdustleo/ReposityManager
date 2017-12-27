package com.jeztech.repomanager.model;

import lombok.Data;

@Data
public class PayStat {
	// 货物总数
	private Integer sumGoods = 0;
	
	// 货物总价
	private Integer sumPrice = 0;
	
	// 已付金额
	private Integer payPrice = 0;
	
	// 欠款金额
	private Integer owePrice = 0;
}
