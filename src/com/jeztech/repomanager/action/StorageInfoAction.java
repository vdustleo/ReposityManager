package com.jeztech.repomanager.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import lombok.Getter;
import lombok.Setter;

import com.jeztech.repomanager.model.PayStat;
import com.jeztech.repomanager.model.StorageInfo;
import com.jeztech.repomanager.model.StorageStat;
import com.jeztech.repomanager.model.StorageType;
import com.jeztech.repomanager.service.IPayManage;
import com.jeztech.repomanager.service.IStorageManage;
import com.jeztech.repomanager.util.JqPage;
import com.opensymphony.xwork2.ActionSupport;

@Setter
@Getter
public class StorageInfoAction extends ActionSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2405355291876290760L;

	@Resource
	private IStorageManage storageManage;
	
	@Resource
	private IPayManage payManage;

	private JqPage pageInfo = new JqPage();
	private String sord = "";
	private String sidx = "";

	// 查询条件
	private StorageInfo cond = new StorageInfo();

	private List<StorageInfo> datas = new ArrayList<StorageInfo>();

	private PayStat payStat = new PayStat();

	private StorageStat storageStat = new StorageStat();

	/**
	 * 分页列出所有的存库记录
	 * 
	 * @return
	 */
	public String listStoreItem() {

		/* 
		 * 存库记录设计上，由于要汇总，和入库、出库记录不使用同样的表与接口
		 */
		Integer total = storageManage.getAllKeepItemCount(cond);
		Integer totalPage = (Integer) (total / pageInfo.getRows())
				+ ((total % pageInfo.getRows() == 0) ? 0 : 1);
		pageInfo.setTotalPage(totalPage);
		pageInfo.setRecord(total);
		// 取得当前页
		Integer curPage = pageInfo.getPage();
		Integer startRow = (curPage - 1) * pageInfo.getRows();

		datas = storageManage.getAllKeepItem(cond, startRow,
				pageInfo.getRows());

		
		payStat = payManage.getPayStat(cond, null);
		if (payStat == null) {
			payStat = new PayStat();
		}
		
		return SUCCESS;
	}
	
	/**
	 * 列出所有的存库记录
	 * 
	 * @return
	 */	
	public String listAllStoreItem() {
		datas = storageManage.getAllKeepItem(cond);
		return SUCCESS;
	}
	

	/**
	 * 列出所有的入库记录
	 * 
	 * @return
	 */
	public String listInItem() {
		// 状态
		List<Integer> statusRange = new ArrayList<Integer>();

		statusRange.add(StorageType.IN_STORAGE);
		commonList(statusRange);

		return SUCCESS;
	}

	/**
	 * 列出所有的出库记录
	 */
	public String listOutItem() {
		// 状态
		List<Integer> statusRange = new ArrayList<Integer>();

		statusRange.add(StorageType.OUT_STORAGE);
		commonList(statusRange);
		return SUCCESS;
	}

	/**
	 * 公共的查询
	 */
	private void commonList(List<Integer> statusRange) {

		Integer total = storageManage.getAllItemCount(cond, statusRange);
		Integer totalPage = (Integer) (total / pageInfo.getRows())
				+ ((total % pageInfo.getRows() == 0) ? 0 : 1);
		pageInfo.setTotalPage(totalPage);
		pageInfo.setRecord(total);
		// 取得当前页
		Integer curPage = pageInfo.getPage();
		Integer startRow = (curPage - 1) * pageInfo.getRows();

		datas = storageManage.getAllItem(cond, statusRange, startRow,
				pageInfo.getRows());

		storageStat = storageManage.getStorageStat(cond, statusRange);
		payStat = payManage.getPayStat(cond, statusRange);
		if (payStat == null) {
			payStat = new PayStat();
		}
	}
}
