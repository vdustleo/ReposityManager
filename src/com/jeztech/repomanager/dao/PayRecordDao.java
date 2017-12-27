package com.jeztech.repomanager.dao;

import java.util.List;
import java.util.Map;

import com.jeztech.repomanager.model.PayInfo;

public interface PayRecordDao {

	/**
	 * 添加一个项目
	 */
	public void addItem(PayInfo info);

	/**
	 * 列出所有的符合条件的项目数
	 */
	public Integer getAllItemCount(Map<String, Object> param);

	/**
	 * 列出所有的符合条件的项目
	 */
	public List<PayInfo> getAllItem(Map<String, Object> param);

	/**
	 * 取得最大的支付记录号
	 */
	public Integer queryMaxPsn(String storageSn);
}
