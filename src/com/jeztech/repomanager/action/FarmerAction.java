package com.jeztech.repomanager.action;

import javax.annotation.Resource;

import lombok.Getter;
import lombok.Setter;

import com.jeztech.repomanager.model.FarmerInfo;
import com.jeztech.repomanager.service.IFarmerManage;
import com.opensymphony.xwork2.ActionSupport;

public class FarmerAction extends ActionSupport {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2755750223275605052L;

	@Resource
	private IFarmerManage farmerManage;

	/**
	 * 果农信息
	 */
	@Getter
	@Setter
	private FarmerInfo info;

	/**
	 * 增加果农
	 * 
	 * @return
	 */
	public String add() {
		Integer sn = farmerManage.getMaxStorageSn();
		info.setStorageSn(String.valueOf(sn));
		farmerManage.addItem(info);
		return SUCCESS;
	}

	/**
	 * 删除果农
	 * 
	 * @return
	 */
	public String delete() {
		farmerManage.deleteItem(info);
		return SUCCESS;
	}
	
}
