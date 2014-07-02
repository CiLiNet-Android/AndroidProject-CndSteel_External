package com.cndsteel.framework.constant;

/**
 * 请求参数名
 * @author zhxl
 *
 */
public interface QueryParams {
	
	public static final String DEFAULT_QUERY_PARAM_DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 公用
	 */
	public static final String QUERY_PARAM_SESSION_ID = "sessionId";
	public static final String QUERY_PARAM_PAGE_INDEX = "pageindex";
	public static final String QUERY_PARAM_PAGE_SIZE = "pagesize";
	public static final String QUERY_PARAM_STATUS = "status";
	public static final String QUERY_PARAM_ID = "id";
	public static final String QUERY_PARAM_PNAME_ID = "pnameId";
	public static final String QUERY_PARAM_MATERIAL_ID = "materialId";

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
	
	/**
	 * 库存
	 */
	public static final String QUERY_PARAM_STOCK_DATE_FROM = "storeDateFrom";
	public static final String QUERY_PARAM_STOCK_DATE_TO = "storeDateTo";
	public static final String QUERY_PARAM_STOCK_WARE_ID = "wareId";
	public static final String QUERY_PARAM_STOCK_CONCODE = "conCode";
	public static final String QUERY_PARAM_STOCK_MATERIAL_ID = "materialId";
	public static final String QUERY_PARAM_STOCK_PNAME_ID = "pnameId";
	public static final String QUERY_PARAM_STOCK_SPEC = "spec";
	public static final String QUERY_PARAM_STOCK_STORE_DATE = "storeDate";
	public static final String QUERY_PARAM_STOCK_CON_ID = "conId";
}
