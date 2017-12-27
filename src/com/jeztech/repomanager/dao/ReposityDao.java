package com.jeztech.repomanager.dao;

import java.util.List;
import java.util.Map;

import com.jeztech.repomanager.model.ReposityInfo;

public interface ReposityDao {
	
	/**
	 * 添加一个仓库
	 */
	public void addItem(ReposityInfo info);
	
	/**
	 * 删除一个仓库
	 */
	public void deleteItem(ReposityInfo info);
	
	/**
	 * 修改一个项目
	 */
	public void modifyItem(ReposityInfo info);
	
	/**
	 * 查询一个仓库的信息
	 */
	public ReposityInfo getReposityByCode(String repoCode);
	
	/**
	 * 列出所有的符合条件的项目数
	 */
	public Integer getAllItemCount();
	
	/**
	 * 列出所有的符合条件的项目
	 */
	public List<ReposityInfo> getAllItemByCond(Map<String, Object> param);

	/**
	 * 列出所有的项目
	 */
	public List<ReposityInfo> getAllItem();
	
	/**
	 * 申请空间
	 */
	public void storeIn(ReposityInfo info);

	/**
	 * 释放空间
	 */
	public void storeOut(ReposityInfo info);
	
	/**
	 * 更新仓库的名称
	 */
	public void updateRepoName(ReposityInfo info);
	
	/**
	 * 查询仓库名称
	 * @return
	 */
	public ReposityInfo queryRepoName();
}
