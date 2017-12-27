package com.jeztech.repomanager.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import lombok.Getter;
import lombok.Setter;

import com.jeztech.repomanager.model.FarmerInfo;
import com.jeztech.repomanager.service.IFarmerManage;
import com.jeztech.repomanager.util.JqPage;
import com.opensymphony.xwork2.ActionSupport;



public class FarmerInfoAction extends ActionSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2405355291876290760L;
	
	@Resource
	private IFarmerManage farmerManage;
	
	@Setter
	@Getter
	private JqPage pageInfo = new JqPage();
	
	@Setter
	@Getter
	private String sord = "";
	@Setter
	@Getter
	private String sidx = "";
	
	// 查询条件
	@Getter
	@Setter
	private FarmerInfo cond = new FarmerInfo();
	
	@Getter
	@Setter
	private List<FarmerInfo> datas = new ArrayList<FarmerInfo>();
	
	/**
	 * 列出所有的果农
	 * @return
	 */
	public String listItem(){
		Integer total = farmerManage.getAllItemCount(cond);
		Integer totalPage = (Integer) (total / pageInfo.getRows())
				+ ((total % pageInfo.getRows() == 0) ? 0 : 1);
		pageInfo.setTotalPage(totalPage);
		pageInfo.setRecord(total);
		// 取得当前页
		Integer curPage = pageInfo.getPage();
		Integer startRow = (curPage - 1) * pageInfo.getRows();

		datas = farmerManage.getAllItem(cond, startRow, pageInfo.getRows());
		
		return SUCCESS;
	}
	
	/**
	 * 列出所有的果农, 给定近似条件的果农信息，用于autocomplete框
	 */
	public String listAllItem(){
		datas = farmerManage.getAllItemById("");
		return SUCCESS;
	}

}
