package com.jeztech.repomanager.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeztech.repomanager.dao.PayRecordDao;
import com.jeztech.repomanager.dao.PayStatDao;
import com.jeztech.repomanager.dao.StorageKeepDao;
import com.jeztech.repomanager.model.PayInfo;
import com.jeztech.repomanager.model.PayStat;
import com.jeztech.repomanager.model.StorageInfo;
import com.jeztech.repomanager.service.IPayManage;
import com.jeztech.repomanager.util.CommonUtils;

@Service
public class PayManage implements IPayManage {

	@Resource
	private PayStatDao payStatDao;

	@Resource
	private PayRecordDao payRecordDao;

	@Resource
	private StorageKeepDao storageKeepDao;

	@Override
	public void pay(PayInfo info) {

		// 1.入库一条支付记录
		Date date = Calendar.getInstance().getTime();
		info.setPayTime(date);
		info.setPsn(queryMaxPsn(info) + 1);
		addRecordItem(info);

		// 2.刷新总支付记录
		PayInfo payStatInfo = queryMoney(info); // 此处，必然有且只有一条
		payStatInfo.setOwePrice(CommonUtils.calculatePriceSub(
				payStatInfo.getOwePrice(), info.getPayPrice()));
		payStatInfo.setPayPrice(CommonUtils.calculatePriceAdd(
				payStatInfo.getPayPrice(), info.getPayPrice()));
		payStatInfo.setPayTime(date);
		modifyStatItem(payStatInfo);
	}

	@Override
	public void addStatItem(PayInfo info) {
		payStatDao.addItem(info);

	}

	@Override
	public void modifyStatItem(PayInfo info) {
		payStatDao.modifyItem(info);
	}

	@Override
	public void addRecordItem(PayInfo info) {
		payRecordDao.addItem(info);

	}

	@Override
	public Integer getAllItemCount(PayInfo cond) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cond", cond);
		return payRecordDao.getAllItemCount(param);
	}

	@Override
	public List<PayInfo> getAllItem(PayInfo cond, Integer startRow, Integer rows) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start_row", startRow);
		param.put("rows", rows);
		param.put("cond", cond);
		return payRecordDao.getAllItem(param);
	}

	@Override
	public PayInfo queryMoney(PayInfo info) {
		return payStatDao.queryMoney(info);
	}

	/**
	 * 取得当前支付的最大记录号，以人为记数
	 * 
	 * @param info
	 * @return
	 */
	private Integer queryMaxPsn(PayInfo info) {
		Integer maxPsn = payRecordDao.queryMaxPsn(info.getStorageSn());
		return maxPsn != null ? maxPsn : 0;
	}

	@Override
	public PayStat getPayStat(StorageInfo cond, List<Integer> statusRange) {
		// 计算总价
		PayStat payStat = new PayStat();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cond", cond);
		param.put("status", statusRange);
		payStat =  payStatDao.getPayStat_mysql(param);
		
		// 计算总货物数, 总价与总货物数之间并不直接相关，，
		// 试想, 出库了, 但是没付款，这时该如何处理
		// 出库会减少货物总数，会不会影响货物总价？
		Integer totalGoodsCount = storageKeepDao.getAllKeepGoodsCount(param);
		payStat.setSumGoods(totalGoodsCount);
		
		return payStat;
	}

	@Override
	public Integer getAllStatItemCount(PayInfo cond) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cond", cond);
		return payStatDao.getAllItemCount(param);
	}

	@Override
	public List<PayInfo> getAllStatItem(PayInfo cond, Integer startRow,
			Integer rows) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start_row", startRow);
		param.put("rows", rows);
		param.put("cond", cond);
		return payStatDao.getAllItem(param);
	}
}
