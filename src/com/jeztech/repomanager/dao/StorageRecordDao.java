package com.jeztech.repomanager.dao;

import java.util.List;
import java.util.Map;

import com.jeztech.repomanager.model.StorageInfo;
import com.jeztech.repomanager.model.StorageStat;

public interface StorageRecordDao {

	/**
	 * 添加一个项目
	 */
	public void addItem(StorageInfo info);

	/**
	 * 删除一个项目
	 */
	public void deleteItem(StorageInfo info);

	/**
	 * 修改一个项目
	 */
	public void modifyItem(StorageInfo info);

	/**
	 * 列出所有的符合条件的项目数
	 */
	public Integer getAllItemCount(Map<String, Object> param);

	/**
	 * 列出所有的符合条件的项目
	 */
	public List<StorageInfo> getAllItem(Map<String, Object> param);
	
	/**
	 * 列出所有的符合条件的数量统计
	 */
	public StorageStat getStorageStat(Map<String, Object> param);

}
