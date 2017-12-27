package com.jeztech.repomanager.service;

import java.util.List;

import com.jeztech.repomanager.model.FarmerInfo;

public interface IFarmerManage {
	
	/**
	 * 添加一个果农
	 */
	public void addItem(FarmerInfo info);
	
	/**
	 * 删除一个果农
	 */
	public void deleteItem(FarmerInfo info);
	
	/**
	 * 修改一个项目
	 */
	public void modifyItem(FarmerInfo info);
	
	/**
	 * 查询果农的详情
	 */
	public FarmerInfo queryById(FarmerInfo info);
	
	/**
	 * 列出所有的符合条件的项目数
	 */
	public Integer getAllItemCount(FarmerInfo cond);
	
	/**
	 * 列出所有的符合条件的项目
	 */
	public List<FarmerInfo> getAllItem(FarmerInfo cond, Integer startRow, Integer rows);
	
	/**
	 * 取得最大的存储编号
	 */
	public Integer getMaxStorageSn();
	
	/**
	 * 根据编号取得所有的相关的信息，用于autocomplete框
	 */
	public List<FarmerInfo> getAllItemById(String storageSn);
}
