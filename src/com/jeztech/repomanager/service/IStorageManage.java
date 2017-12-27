package com.jeztech.repomanager.service;

import java.util.List;

import com.jeztech.repomanager.model.StorageInfo;
import com.jeztech.repomanager.model.StorageStat;

public interface IStorageManage {

	/**
	 * 删除一个项目, 记录
	 */
	public void deleteItem(StorageInfo info);
	
	/**
	 * 删除一个库存项目, 库存
	 */
	public void deleteKeepItem(StorageInfo info);

	/**
	 * 入库记录
	 */
	public void in(StorageInfo info);

	/**
	 * 出库记录
	 */
	public void out(StorageInfo info);

	/**
	 * 列出所有的符合条件的数量统计
	 */
	public StorageStat getStorageStat(StorageInfo cond,
			List<Integer> statusRange);

	/**
	 * 列出所有的符合条件的项目, 入出库记录
	 */
	public Integer getAllItemCount(StorageInfo cond, List<Integer> statusRange);

	/**
	 * 列出所有的符合条件的项目, 入出库记录
	 */
	public List<StorageInfo> getAllItem(StorageInfo cond,
			List<Integer> statusRange, Integer startRow, Integer rows);

	/**
	 * 列出所有的符合条件的项目数, 库存记录
	 */
	public Integer getAllKeepItemCount(StorageInfo cond);

	/**
	 * 列出所有的符合条件的项目, 库存记录
	 */
	public List<StorageInfo> getAllKeepItem(StorageInfo cond, Integer startRow, Integer rows);
	
	/**
	 * 列出所有的符合条件的项目
	 */
	public List<StorageInfo> getAllKeepItem(StorageInfo cond);
}
