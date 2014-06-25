package com.cndsteel.framework.constant;

/**
 * 请求参数名
 * @author zhxl
 *
 */
public interface QueryParams {
	
	/**
	 * 公用
	 */
	public static final String QUERY_PARAM_SESSION_ID = "sessionId";
	public static final String QUERY_PARAM_PAGE_INDEX = "pageindex";
	public static final String QUERY_PARAM_PAGE_SIZE = "pagesize";
	public static final String QUERY_PARAM_STATUS = "status";
	public static final String QUERY_PARAM_ID = "id";

	/**
	 * 计划模块
	 */
	public static final String QUERY_PARAM_PLAN_DATE_YEAR = "year";
	public static final String QUERY_PARAM_PLAN_DATE_MONTH = "month";
	public static final String QUERY_PARAM_PLAN_BOOKING_ID = "bookingId";
	
	/**
	 * 合同
	 */
	public static final String QUERY_PARAM_CONTRACT_CONYEAR = "conYear";
	public static final String QUERY_PARAM_CONTRACT_CONMONTH = "conMonth";
	public static final String QUERY_PARAM_CONTRACT_CONCODE = "conCode";
	
}
