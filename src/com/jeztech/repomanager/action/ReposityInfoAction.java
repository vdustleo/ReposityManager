package com.jeztech.repomanager.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import lombok.Getter;
import lombok.Setter;

import com.jeztech.repomanager.model.ReposityInfo;
import com.jeztech.repomanager.service.IReposityManage;
import com.jeztech.repomanager.util.JqPage;
import com.opensymphony.xwork2.ActionSupport;


@Setter
@Getter
public class ReposityInfoAction extends ActionSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2405355291876290760L;
	
	@Resource
	private IReposityManage reposityManage;
	
	private JqPage pageInfo = new JqPage();
	private String sord = "";
	private String sidx = "";
	
	// 查询条件
	private ReposityInfo cond = new ReposityInfo();
	private List<ReposityInfo> datas = new ArrayList<ReposityInfo>();
	
	
	/**
	 * 列出所有的仓库
	 * @return
	 */
	public String listItem(){
		Integer total = reposityManage.getAllItemCount();
		Integer totalPage = (Integer) (total / pageInfo.getRows())
				+ ((total % pageInfo.getRows() == 0) ? 0 : 1);
		pageInfo.setTotalPage(totalPage);
		pageInfo.setRecord(total);
		// 取得当前页
		Integer curPage = pageInfo.getPage();
		Integer startRow = (curPage - 1) * pageInfo.getRows();

		datas = reposityManage.getAllItem(startRow, pageInfo.getRows());
		return SUCCESS;
	}
	
	/**
	 * 列出所有的仓库
	 */
	public String listAllItem(){
		datas = reposityManage.getAllItem();
		return SUCCESS;
	}
}
