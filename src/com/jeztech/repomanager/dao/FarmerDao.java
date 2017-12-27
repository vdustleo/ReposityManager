package com.jeztech.repomanager.dao;

import java.util.List;
import java.util.Map;

import com.jeztech.repomanager.model.FarmerInfo;

public interface FarmerDao {
	
	/**
	 * 添加一个果农
	 */
	public void addItem(FarmerInfo info);
	
	/**
	 * 删除一个果农
	 */
	public void deleteItem(FarmerInfo info);
	
	/**
	 * 修改果农信息
	 */
	public void modifyItem(FarmerInfo info);
	
	/**
	 * 查询一个果农的信息
	 */
	public FarmerInfo getFarmerByStorageSn(String storageSn);
	
	/**
	 * 列出所有的符合条件的项目数
	 */
	public Integer getAllItemCount(FarmerInfo cond);
	
	/**
	 * 列出所有的符合条件的项目
	 */
	public List<FarmerInfo> getAllItem(Map<String, Object> param);
	
	/**
	 * 列出当前系统中最大的存储编号
	 */
	public Integer getMaxStorageSn();
	
	/**
	 * 根据编号取得所有的相关的信息，用于autocomplete框
	 */
	public List<FarmerInfo> getAllItemById(String storageSn);
}
