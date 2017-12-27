package com.jeztech.repomanager.action;

import javax.annotation.Resource;

import lombok.Getter;
import lombok.Setter;

import com.jeztech.repomanager.model.ReposityInfo;
import com.jeztech.repomanager.service.IReposityManage;
import com.opensymphony.xwork2.ActionSupport;

public class ReposityAction extends ActionSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1732406981477097391L;

	@Resource
	private IReposityManage reposityManage;

	/**
	 * 仓库信息
	 */
	@Getter
	@Setter
	private ReposityInfo info;

	/**
	 * 是否被引用
	 */
	@Getter
	@Setter
	private boolean hasStorage;

	/**
	 * 增加仓库
	 * 
	 * @return
	 */
	public String add() {
		reposityManage.addItem(info);
		return SUCCESS;
	}

	/**
	 * 删除仓库
	 * 
	 * @return
	 */
	public String delete() {
		reposityManage.deleteItem(info);
		return SUCCESS;
	}

	/**
	 * 测试是仓库是否有引用，被引用的仓库不能删除
	 * 
	 * @return
	 */
	public String hasStorage() {
		hasStorage = reposityManage.hasStorage(info);
		return SUCCESS;
	}

	/**
	 * 刷新仓库的名称
	 * 
	 * @return
	 */
	public String updateRepoName() {
		reposityManage.updateRepoName(info);
		return SUCCESS;
	}

	/**
	 * 刷新仓库的名称
	 * 
	 * @return
	 */
	public String queryRepoName() {
		info = reposityManage.queryRepoName();
		return SUCCESS;
	}
}
