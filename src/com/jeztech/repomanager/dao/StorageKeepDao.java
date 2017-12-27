package com.jeztech.repomanager.dao;

import java.util.List;
import java.util.Map;

import com.jeztech.repomanager.model.StorageInfo;

public interface StorageKeepDao {

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
	 * 根据key查询对应的项目，用于入库
	 */
	public StorageInfo getItemByKey(StorageInfo info);
	
	/**
	 * 根据key查询对应的项目, 用于出库
	 */
	public StorageInfo getItemById(StorageInfo info);
	
	
	/**
	 * 列出所有的符合条件的项目数
	 */
	public Integer getAllItemCount(Map<String, Object> param);

	/**
	 * 列出所有的符合条件的项目
	 */
	public List<StorageInfo> getAllItem(Map<String, Object> param);
	
	/**
	 * 列出所有的项目
	 */
	public List<StorageInfo> getAllItems(Map<String, Object> param);

	/**
	 * 列出所有的货物总数
	 */
	public Integer getAllKeepGoodsCount(Map<String, Object> param);
}
