package com.jeztech.repomanager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeztech.repomanager.dao.FarmerDao;
import com.jeztech.repomanager.model.FarmerInfo;
import com.jeztech.repomanager.service.IFarmerManage;


@Service
public class FarmerManage implements IFarmerManage{
	
	@Resource
	private FarmerDao farmerDao;

	@Override
	public void addItem(FarmerInfo info) {
		farmerDao.addItem(info);
	}

	@Override
	public void deleteItem(FarmerInfo info) {
		farmerDao.deleteItem(info);
	}

	@Override
	public void modifyItem(FarmerInfo info) {
		farmerDao.modifyItem(info);
	}

	@Override
	public FarmerInfo queryById(FarmerInfo info) {
		return farmerDao.getFarmerByStorageSn(info.getStorageSn());
	}
	
	@Override
	public Integer getAllItemCount(FarmerInfo cond) {
		Integer count = farmerDao.getAllItemCount(cond);
		return count;
	}

	@Override
	public List<FarmerInfo> getAllItem(FarmerInfo cond, Integer startRow, Integer rows) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start_row", startRow);
		param.put("rows", rows);
		param.put("cond", cond);
		
		List<FarmerInfo> infos = farmerDao.getAllItem(param);
		return infos;
	}

	@Override
	public Integer getMaxStorageSn() {
		Integer sn = farmerDao.getMaxStorageSn();
		// 如果为空，从1开始计数
		return ( sn == null || sn == 0 ) ? 1 : sn + 1;
	}

	@Override
	public List<FarmerInfo> getAllItemById(String storageSn) {
		
		return farmerDao.getAllItemById(storageSn);
	}
}
