package com.jeztech.repomanager.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeztech.repomanager.dao.StorageKeepDao;
import com.jeztech.repomanager.dao.StorageRecordDao;
import com.jeztech.repomanager.model.PayInfo;
import com.jeztech.repomanager.model.StorageInfo;
import com.jeztech.repomanager.model.StorageStat;
import com.jeztech.repomanager.model.StorageType;
import com.jeztech.repomanager.service.IStorageManage;
import com.jeztech.repomanager.util.CommonUtils;

@Service
public class StorageManage implements IStorageManage {

	@Resource
	private StorageKeepDao storageKeepDao;

	@Resource
	private StorageRecordDao storageRecordDao;

	@Resource
	private PayManage payManage;

	/**
	 * @return
	 */
	private String calculateTotalPrice(StorageInfo info) {
		Double money = info.getGoodsCount()
				* Double.valueOf(info.getUnitPrice());
		return CommonUtils.convertMoneyString(String.valueOf(money));
	}

	@Override
	public void in(StorageInfo info) {

		// 1.存一条入库记录
		inStorageRecord(info);

		// 2.存一条在库记录(引用主ID)，用来跟踪出库变化
		inStorageKeep(info);

		// 3.存一条付款总额记录
		inPayStat(info);

	}

	/**
	 * 统计单人的待付款总额
	 * 
	 * @param info
	 */
	private void inPayStat(StorageInfo info) {
		PayInfo payInfo = new PayInfo();
		payInfo.setStorageSn(info.getStorageSn());
		PayInfo payStatInfo = payManage.queryMoney(payInfo);
		// 更新
		if (payStatInfo != null) {
			String delta = calculateTotalPrice(info);
			payStatInfo.setTotalPrice(CommonUtils.calculatePriceAdd(
					payStatInfo.getTotalPrice(), delta));
			payStatInfo.setOwePrice(CommonUtils.calculatePriceAdd(
					payStatInfo.getOwePrice(), delta));
			payStatInfo.setPayTime(info.getOperateTime());
			payManage.modifyStatItem(payStatInfo);
		} else { // 新增
			PayInfo payNew = new PayInfo();
			payNew.setStorageSn(info.getStorageSn());
			payNew.setTotalPrice(info.getTotalPrice());
			payNew.setOwePrice(info.getTotalPrice());
			payNew.setPayPrice("0.00");
			payNew.setPayTime(info.getOperateTime());
			payManage.addStatItem(payNew);
		}
	}

	/**
	 * 存一条入库记录
	 * 
	 * @param info
	 */
	private void inStorageRecord(StorageInfo info) {
		Date date = Calendar.getInstance().getTime();
		info.setTotalPrice(calculateTotalPrice(info));
		info.setOperateTime(date);
		info.setType(StorageType.IN_STORAGE);
		storageRecordDao.addItem(info);
	}

	/**
	 * 存一条在库记录
	 * 
	 * @param info
	 */
	private void inStorageKeep(StorageInfo info) {
		StorageInfo keepStorage = storageKeepDao.getItemByKey(info);

		// 如果存在，就刷新, 不存在，就新插入
		if (keepStorage != null) {
			Integer totalGoodsCount = keepStorage.getGoodsCount()
					+ info.getGoodsCount();
			keepStorage.setGoodsCount(totalGoodsCount);
			keepStorage.setUnitPrice(info.getUnitPrice());
			keepStorage.setTotalPrice(calculateTotalPrice(keepStorage));
			storageKeepDao.modifyItem(keepStorage);
		} else {
			storageKeepDao.addItem(info);
		}
	}

	@Override
	public void out(StorageInfo info) {

		// 1.查询对应的库存信息
		StorageInfo localInfo = storageKeepDao.getItemById(info);
		Integer goodsCountBefore = localInfo.getGoodsCount();
		Integer sid = localInfo.getSid();

		// 2.增加一条出库记录；
		Date date = Calendar.getInstance().getTime();
		localInfo.setOperateTime(date);
		localInfo.setType(StorageType.OUT_STORAGE);
		localInfo.setGoodsCount(info.getGoodsCount());
		localInfo.setTotalPrice(calculateTotalPrice(localInfo));
		storageRecordDao.addItem(localInfo);

		// 3.刷新库存的信息
		localInfo.setSid(sid); // 解决插入接口刷新主键数据的问题
		localInfo.setGoodsCount(Math.max(
				goodsCountBefore - info.getGoodsCount(), 0));
		localInfo.setTotalPrice(calculateTotalPrice(localInfo));
		
		// 4.刷新库存的总价，和已付款
		// TODO
		
		storageKeepDao.modifyItem(localInfo);

	}

	@Override
	public Integer getAllItemCount(StorageInfo cond, List<Integer> statusRange) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cond", cond);
		param.put("status", statusRange);
		return storageRecordDao.getAllItemCount(param);
	}

	@Override
	public List<StorageInfo> getAllItem(StorageInfo cond,
			List<Integer> statusRange, Integer startRow, Integer rows) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start_row", startRow);
		param.put("rows", rows);
		param.put("cond", cond);
		param.put("status", statusRange);
		return storageRecordDao.getAllItem(param);
	}

	@Override
	public void deleteItem(StorageInfo info) {
		storageRecordDao.deleteItem(info);
	}

	@Override
	public void deleteKeepItem(StorageInfo info) {
		storageKeepDao.deleteItem(info);
	}

	@Override
	public StorageStat getStorageStat(StorageInfo cond,
			List<Integer> statusRange) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cond", cond);
		param.put("status", statusRange);
		return storageRecordDao.getStorageStat(param);
	}

	@Override
	public Integer getAllKeepItemCount(StorageInfo cond) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cond", cond);
		return storageKeepDao.getAllItemCount(param);
	}

	@Override
	public List<StorageInfo> getAllKeepItem(StorageInfo cond, Integer startRow,
			Integer rows) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start_row", startRow);
		param.put("rows", rows);
		param.put("cond", cond);
		return storageKeepDao.getAllItem(param);
	}

	@Override
	public List<StorageInfo> getAllKeepItem(StorageInfo cond) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cond", cond);		
		return storageKeepDao.getAllItems(param);
	}
	
	public Integer getAllKeepGoodsCount(StorageInfo cond) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cond", cond);		
		return storageKeepDao.getAllKeepGoodsCount(param);
	}
}
