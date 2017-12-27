package com.jeztech.repomanager.service;

import java.util.List;

import com.jeztech.repomanager.model.PayInfo;
import com.jeztech.repomanager.model.PayStat;
import com.jeztech.repomanager.model.StorageInfo;

public interface IPayManage {
	
	/**
	 * 支付
	 */
	public void pay(PayInfo info);
	
	
	/**
	 * 添加一个统计项
	 */
	public void addStatItem(PayInfo info);
	
	/**
	 * 修改一个统计项
	 */
	public void modifyStatItem(PayInfo info);

	/**
	 * 添加一个记录项
	 */
	public void addRecordItem(PayInfo info);
	
	/**
	 * 列出所有的符合条件的项目数
	 */
	public Integer getAllItemCount(PayInfo cond);
	
	/**
	 * 列出所有的符合条件的项目
	 */
	public List<PayInfo> getAllItem(PayInfo cond, Integer startRow, Integer rows);
	
	/**
	 * 列出所有的符合条件的项目数(财务统计)
	 */
	public Integer getAllStatItemCount(PayInfo cond);

	/**
	 * 列出所有的符合条件的项目(财务统计)
	 */
	public List<PayInfo> getAllStatItem(PayInfo cond, Integer startRow, Integer rows);

	/**
	 * 查询当前的支付信息
	 */
	public PayInfo queryMoney(PayInfo info);

	/**
	 * 列出所有的符合条件的金额统计
	 */
	public PayStat getPayStat(StorageInfo cond, List<Integer> statusRange);
}
