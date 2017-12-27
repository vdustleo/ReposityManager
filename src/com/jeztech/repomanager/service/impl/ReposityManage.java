package com.jeztech.repomanager.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeztech.repomanager.dao.ReposityDao;
import com.jeztech.repomanager.dao.StorageRecordDao;
import com.jeztech.repomanager.model.ReposityInfo;
import com.jeztech.repomanager.model.StorageInfo;
import com.jeztech.repomanager.model.StorageType;
import com.jeztech.repomanager.service.IReposityManage;


@Service
public class ReposityManage implements IReposityManage{
	
	@Resource
	private ReposityDao reposityDao;
	
	@Resource
	private StorageRecordDao storageRecordDao;

	@Override
	public void addItem(ReposityInfo info) {
		reposityDao.addItem(info);
	}

	@Override
	public void deleteItem(ReposityInfo info) {
		reposityDao.deleteItem(info);
	}

	@Override
	public void modifyItem(ReposityInfo info) {
		reposityDao.modifyItem(info);
	}

	@Override
	public ReposityInfo queryById(ReposityInfo info) {
		return reposityDao.getReposityByCode(info.getRepoCode());
	}
	
	@Override
	public Integer getAllItemCount() {
		Integer count = reposityDao.getAllItemCount();
		return count;
	}

	@Override
	public List<ReposityInfo> getAllItem(Integer startRow, Integer rows) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start_row", startRow);
		param.put("rows", rows);
		
		List<ReposityInfo> infos = reposityDao.getAllItemByCond(param);
		return infos;
	}

	@Override
	public Boolean hasStorage(ReposityInfo info) {
		
		// 找出引用的数量
		StorageInfo cond = new StorageInfo();
		cond.setRepoCode(info.getRepoCode());
		
		List<Integer> statusRange = new ArrayList<Integer>();
		statusRange.add(StorageType.IN_STORAGE);
		statusRange.add(StorageType.OUT_STORAGE);
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cond", cond);
		param.put("status", statusRange);		
		
		return storageRecordDao.getAllItemCount(param) > 0;
	}

	@Override
	public void updateRepoName(ReposityInfo info) {
		reposityDao.updateRepoName(info);
	}

	@Override
	public ReposityInfo queryRepoName() {
		return reposityDao.queryRepoName();
	}

	@Override
	public List<ReposityInfo> getAllItem() {
		return reposityDao.getAllItem();
	}
}
