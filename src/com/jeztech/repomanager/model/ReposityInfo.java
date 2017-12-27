package com.jeztech.repomanager.model;

import lombok.Data;

@Data
public class ReposityInfo {
	
	/**
	 * 编号
	 */
	private String repoCode = "";
	
	/**
	 * 隔档数量
	 */
	private Integer sepNum = 0;
	
	/**
	 * 管理员
	 */
	private String admin = "";
}
