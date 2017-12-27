package com.jeztech.repomanager.service;

import java.util.List;

import com.jeztech.repomanager.model.ReposityInfo;

public interface IReposityManage {
	
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
	 * 仓库是否被引用
	 */
	public Boolean hasStorage(ReposityInfo info);
	
	/**
	 * 查询仓库的详情
	 */
	public ReposityInfo queryById(ReposityInfo info);
	
	/**
	 * 列出所有的符合条件的项目数
	 */
	public Integer getAllItemCount();
	
	/**
	 * 列出所有的符合条件的项目
	 */
	public List<ReposityInfo> getAllItem(Integer startRow, Integer rows);

	/**
	 * 列出所有的项目
	 */
	public List<ReposityInfo> getAllItem();
	
	/**
	 * 刷新仓库的名称
	 * 使用repoCode存储要修改的名称
	 */
	public void updateRepoName(ReposityInfo info);
	
	/**
	 * 查询仓库名称
	 */
	public ReposityInfo queryRepoName();
}
