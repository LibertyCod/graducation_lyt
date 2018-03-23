package com.graduation.project.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import static org.apache.commons.lang3.math.NumberUtils.isDigits;

/**
 * 过滤器，存放线程相关变量
 * 
 * @author leejianhao
 *
 */
public class SystemContextFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		int offset = 0;
		int pageSize = 15;

		HttpServletRequest httpReq = (HttpServletRequest) req;

		String firstResultStr = httpReq.getParameter("firstResult");
		String pageSizeStr = httpReq.getParameter("pageSize");

		if (isDigits(firstResultStr)) {
			offset = Integer.parseInt(firstResultStr);
		}

		if (isDigits(pageSizeStr)) {
			pageSize = Integer.parseInt(pageSizeStr);
		}

		try {
			SystemContext.setOrder(httpReq.getParameter("order"));
			SystemContext.setSort(httpReq.getParameter("sort"));
			SystemContext.setFirstResult(offset);
			SystemContext.setPageSize(pageSize);
			chain.doFilter(req, resp);
		} finally {
			SystemContext.removeOrder();
			SystemContext.removeSort();
			SystemContext.removeFirstResult();
			SystemContext.removePageSize();
			SystemContext.removeJwtToken();
			SystemContext.removeUserId();
		}
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
	}

}
