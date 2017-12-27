package com.jeztech.repomanager.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import lombok.Getter;
import lombok.Setter;

import com.jeztech.repomanager.model.PayInfo;
import com.jeztech.repomanager.model.PayStat;
import com.jeztech.repomanager.model.StorageInfo;
import com.jeztech.repomanager.service.IPayManage;
import com.jeztech.repomanager.util.JqPage;
import com.opensymphony.xwork2.ActionSupport;


@Setter
@Getter
public class PayInfoAction extends ActionSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2405355291876290760L;
	
	@Resource
	private IPayManage payManage;
	
	private JqPage pageInfo = new JqPage();
	private String sord = "";
	private String sidx = "";
	
	// 查询条件
	private PayInfo cond = new PayInfo();
	
	private List<PayInfo> datas = new ArrayList<PayInfo>();
	
	private PayStat payStat = new PayStat();
	
	/**
	 * 列出所有的支付记录
	 * @return
	 */
	public String listItem(){
		Integer total = payManage.getAllItemCount(cond);
		Integer totalPage = (Integer) (total / pageInfo.getRows())
				+ ((total % pageInfo.getRows() == 0) ? 0 : 1);
		pageInfo.setTotalPage(totalPage);
		pageInfo.setRecord(total);
		// 取得当前页
		Integer curPage = pageInfo.getPage();
		Integer startRow = (curPage - 1) * pageInfo.getRows();

		datas = payManage.getAllItem(cond, startRow, pageInfo.getRows());
		
		// 同步取得统计信息
		StorageInfo storageCond = new StorageInfo();
		storageCond.setFarmerName(cond.getFarmerName());
		storageCond.setStorageSn(cond.getStorageSn());
		payStat = payManage.getPayStat(storageCond, null);
		if (payStat == null) {
			payStat = new PayStat();
		}
		return SUCCESS;
	}

	/**
	 * 列出统计信息
	 * @return
	 */
	public String listStatItem(){
		Integer total = payManage.getAllStatItemCount(cond);
		Integer totalPage = (Integer) (total / pageInfo.getRows())
				+ ((total % pageInfo.getRows() == 0) ? 0 : 1);
		pageInfo.setTotalPage(totalPage);
		pageInfo.setRecord(total);
		// 取得当前页
		Integer curPage = pageInfo.getPage();
		Integer startRow = (curPage - 1) * pageInfo.getRows();

		datas = payManage.getAllStatItem(cond, startRow, pageInfo.getRows());
		
		// 同步取得统计信息
		StorageInfo storageCond = new StorageInfo();
		storageCond.setFarmerName(cond.getFarmerName());
		storageCond.setStorageSn(cond.getStorageSn());
		payStat = payManage.getPayStat(storageCond, null);
		if (payStat == null) {
			payStat = new PayStat();
		}
		return SUCCESS;
	}
}
