package com.jeztech.repomanager.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeztech.repomanager.dao.CommonDataDao;
import com.jeztech.repomanager.service.ICommonDataManage;

@Service
public class CommonDataManage implements ICommonDataManage {

	@Resource
	private CommonDataDao commonDataDao;

	@Override
	public List<HashMap<String, String>> queryAllAppleType() {
		return commonDataDao.queryAllAppleType();
	}

	@Override
	public List<HashMap<String, String>> queryAllAppleSize() {
		return commonDataDao.queryAllAppleSize();
	}

	@Override
	public List<HashMap<String, String>> listRepoInfo() {
		return commonDataDao.listRepoInfo();
	}

}
