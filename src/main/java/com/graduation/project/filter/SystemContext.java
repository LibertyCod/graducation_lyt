package com.graduation.project.filter;

/**
 * 用来传递列表对象的ThreadLocal </p>
 * 
 * @author leejianhao
 *
 */
public class SystemContext {

	// 不可实例化
	private SystemContext() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 分页的大小
	 */
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	/**
	 * 分页的起始页
	 */
	private static ThreadLocal<Integer> firstResult = new ThreadLocal<Integer>();
	/**
	 * 列表的排序字段
	 */
	private static ThreadLocal<String> sort = new ThreadLocal<String>();
	/**
	 * 列表的排序方式
	 */
	private static ThreadLocal<String> order = new ThreadLocal<String>();
	/**
	 * 多语言
	 */
	private static ThreadLocal<String> language = new ThreadLocal<String>();

	/**
	 * Jwt token
	 */
	private static ThreadLocal<String> jwtToken = new ThreadLocal<String>();

	/**
	 * Jwt token, user code
	 */
	private static ThreadLocal<String> userId = new ThreadLocal<String>();


	public static Integer getPageSize() {
		return pageSize.get();
	}

	public static void setPageSize(Integer _pageSize) {
		pageSize.set(_pageSize);
	}

	public static Integer getFirstResult() {
		return firstResult.get();
	}

	public static void setFirstResult(Integer _firstResult) {
		firstResult.set(_firstResult);
	}

	public static String getSort() {
		return sort.get();
	}

	public static void setSort(String _sort) {
		sort.set(_sort);
	}

	public static String getOrder() {
		return order.get();
	}

	public static void setOrder(String _order) {
		order.set(_order);
	}

	public static String getLanguage() {
		return language.get();
	}

	public static void setLanguage(String _language) {
		language.set(_language);
	}

	public static String getJwtToken() {
		return jwtToken.get();
	}

	public static void setJwtToken(String _jwtToken) {
		jwtToken.set(_jwtToken);
	}

	public static String getUserId() {
		return userId.get();
	}

	public static void setUserId(String _appCode) {
		userId.set(_appCode);
	}

	public static void removePageSize() {
		pageSize.remove();
	}

	public static void removeFirstResult() {
		firstResult.remove();
	}

	public static void removeSort() {
		sort.remove();
	}

	public static void removeOrder() {
		order.remove();
	}

	public static void removeLanguage() {
		language.remove();
	}

	public static void removeJwtToken() {
		jwtToken.remove();
	}

	public static void removeUserId() {
		userId.remove();
	}
}
