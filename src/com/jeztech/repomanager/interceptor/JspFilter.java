package com.jeztech.repomanager.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class JspFilter implements Filter {
	protected final static Logger logger = Logger.getLogger(JspFilter.class);

	@Override
	public void destroy() {

	}

	/**
	 * 留下过滤jsp直接访问的接口
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		if (url != null && url.endsWith(".jsp")) {
			logger.error(req.getRequestURI());
			// HttpSession session = ((HttpServletRequest)req).getSession();
			// session.setAttribute("key", "value");
			// ((HttpServletResponse)response).sendRedirect("/HuiNongZhiFu/");
		}

		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
