package com.jeztech.repomanager.interceptor;


import org.apache.log4j.Logger;

import com.jeztech.repomanager.util.LicenseUtils;
import com.jeztech.repomanager.util.SystemConfig;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	private final static Logger logger = Logger.getLogger(LoginInterceptor.class);

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1407510631751748820L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		/*
		//检查session中是否有user标志
		Map<?, ?> session = invocation.getInvocationContext().getSession();
		
		String username = (String)session.get("user");
		if(username!= null && username.length() > 0 ){
			//存在用户
			logger.error("already login!");
			return invocation.invoke();
		}
		else{
			//终止login，退回到主登录界面
			logger.error("no login, forward login page!");
			return Action.LOGIN;
		}
		if(!LicenseUtils.checkValid(SystemConfig.getInstance().getDeadlineDate())){
			logger.error("License is overdue!");
			return "overdue";
		}
		 */
		
		return invocation.invoke();
	}

}
