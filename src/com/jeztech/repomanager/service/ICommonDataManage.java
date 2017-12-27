package com.jeztech.repomanager.service;

import java.util.HashMap;
import java.util.List;


public interface ICommonDataManage {
	/**
	 * 查询所有的苹果类型
	 */
	public List<HashMap<String, String>> queryAllAppleType();
	
	/**
	 * 查询所有的苹果尺寸
	 */
	public List<HashMap<String, String>> queryAllAppleSize();
	
	/**
	 * 查询所有的苹果尺寸
	 */
	public List<HashMap<String, String>> listRepoInfo();
}
