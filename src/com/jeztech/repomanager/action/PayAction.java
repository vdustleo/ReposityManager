package com.jeztech.repomanager.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

import com.jeztech.repomanager.model.PayInfo;
import com.jeztech.repomanager.service.IPayManage;
import com.jeztech.repomanager.util.CommonUtils;
import com.opensymphony.xwork2.ActionSupport;

public class PayAction  extends ActionSupport{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8906144945281317185L;
	
	@Resource
	private IPayManage payManage;
	
	/**
	 * 库存信息
	 */
	@Setter
	@Getter
	private PayInfo info;

	@Setter
	@Getter
	private PayInfo cond;

	/**
	 * 增加记录
	 * 
	 * @return
	 */
	public String add() {
		payManage.pay(info);
		return SUCCESS;
	}

	/**
	 * 查询总价
	 * 
	 * @return
	 */
	public String queryMoney() {
		info = payManage.queryMoney(cond);
		
		info.setOwePrice(parseValidValue(info.getOwePrice()));
		info.setPayPrice(parseValidValue(info.getPayPrice()));
		info.setTotalPrice(parseValidValue(info.getTotalPrice()));
		
		if(info != null){
			payPrice();
		}
		return SUCCESS;
	}
	
	private void payPrice(){
		Double money = Double.valueOf(info.getTotalPrice()) - Double.valueOf(info.getOwePrice());
		info.setPayPrice(CommonUtils.convertMoneyString(String.valueOf(money)));
	}
	
	private String parseValidValue(String value){
		if(StringUtils.isEmpty(value)){
			return "0.0";
		}
		return value;
	}
}
