package com.jeztech.repomanager.action;

import java.util.List;

import javax.annotation.Resource;

import lombok.Getter;
import lombok.Setter;

import com.jeztech.repomanager.model.StorageInfo;
import com.jeztech.repomanager.service.IPayManage;
import com.jeztech.repomanager.service.IStorageManage;
import com.opensymphony.xwork2.ActionSupport;

public class StorageAction extends ActionSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1732406981477097391L;

	@Resource
	private IStorageManage storageManage;
	
	@Resource
	private IPayManage payManage;
	
	/**
	 * 库存信息
	 */
	@Getter
	@Setter
	private StorageInfo info;
	
	/**
	 * 批量操作信息 
	 */
	@Getter
	@Setter
	private List<Integer> ids;

	/**
	 * 入库
	 * 
	 * @return
	 */
	public String in() {
		storageManage.in(info);
		return SUCCESS;
	}



	/**
	 * 删除仓库
	 * 
	 * @return
	 */
	public String delete() {
		// TODO
		// 这里要处理2件事:
		// 1.对于已经有出库的库存记录，不可以删除 
		
		// 2.删除入库记录，要同步删除库存记录
		storageManage.deleteItem(info);
		storageManage.deleteKeepItem(info);
		return SUCCESS;
	}
	
	/**
	 * 出库
	 * 
	 * @return
	 */
	public String out() {
		storageManage.out(info);
		return SUCCESS;
	}

}
