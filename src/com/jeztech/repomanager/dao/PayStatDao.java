package com.jeztech.repomanager.dao;

import java.util.List;
import java.util.Map;

import com.jeztech.repomanager.model.PayInfo;
import com.jeztech.repomanager.model.PayStat;

public interface PayStatDao {
	
	/**
	 * 添加一个项目
	 */
	public void addItem(PayInfo info);
	
	/**
	 * 修改一个项目
	 */
	public void modifyItem(PayInfo info);
	
	/**
	 * 列出所有的符合条件的项目数
	 */
	public Integer getAllItemCount(Map<String, Object> param);
	
	/**
	 * 列出所有的符合条件的项目
	 */
	public List<PayInfo> getAllItem(Map<String, Object> param);

	/**
	 * 查询总价
	 */
	public PayInfo queryMoney(PayInfo info);
	
	/**
	 * 查询汇总信息
	 */
	public PayStat getPayStat_mysql(Map<String, Object> param);
}
