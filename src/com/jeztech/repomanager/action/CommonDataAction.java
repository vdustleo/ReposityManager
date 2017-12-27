package com.jeztech.repomanager.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import lombok.Getter;
import lombok.Setter;

import com.jeztech.repomanager.service.ICommonDataManage;
import com.jeztech.repomanager.util.SystemConfig;
import com.opensymphony.xwork2.ActionSupport;


public class CommonDataAction extends ActionSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2864369802006361273L;
	
	@Resource
	private ICommonDataManage commonDataManage;
	
	/**
	 *  公共的Key-value信息
	 */
	@Setter
	@Getter
	private List<HashMap<String, String>> infos = new ArrayList<HashMap<String, String>>();
	
	@Setter
	@Getter
	private String ipAddr = "";
	
	/**
	 * 列出所有苹果类型
	 * 
	 * @return
	 */
	public String listAppleType() {
		infos = commonDataManage.queryAllAppleType();
		return SUCCESS;
	}

	/**
	 * 列出所有苹果尺寸
	 * 
	 * @return
	 */
	public String listAppleSize() {
		infos = commonDataManage.queryAllAppleSize();
		return SUCCESS;
	}
	
	/**
	 * 列出所有仓库信息
	 * 
	 * @return
	 */
	public String listRepoInfo() {
		infos = commonDataManage.listRepoInfo();
		return SUCCESS;
	}
	
	/**
	 * 取出服务器的IP地址
	 * 
	 * @return
	 */
	public String serverIp() {
		ipAddr = SystemConfig.getInstance().getLocalIp();
		return SUCCESS;
	}
	
}
