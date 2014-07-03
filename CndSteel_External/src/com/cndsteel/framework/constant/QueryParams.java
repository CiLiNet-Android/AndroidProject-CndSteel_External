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
	public static final String QUERY_PARAM_CON_YEAR = "conYear";
	public static final String QUERY_PARAM_CON_MONTH = "conMonth";
	public static final String QUERY_PARAM_CON_CODE = "conCode";
	public static final String QUERY_PARAM_CON_ID = "conId";

	/**
	 * 计划模块
	 */
	public static final String QUERY_PARAM_PLAN_DATE_YEAR = "year";
	public static final String QUERY_PARAM_PLAN_DATE_MONTH = "month";
	public static final String QUERY_PARAM_PLAN_BOOKING_ID = "bookingId";
	
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
	
	/**
	 * 往来
	 */
	public static final String QUERY_PARAM_PAYMENT_PAY_DATE_FROM = "payDateFrom";
	public static final String QUERY_PARAM_PAYMENT_PAY_DATE_TO = "payDateTo";
	public static final String QUERY_PARAM_PAYMENT_PAY_MODE = "payMode";
	public static final String QUERY_PARAM_PAYMENT_BILL_NO = "billNO";
	public static final String QUERY_PARAM_PAYMENT_PAY_ID = "payId";
	
	
	/**
	 * 资讯
	 */
	public static final String QUERY_PARAM_INFO_MSG_CATE = "msgCate";

	/**
	 * 系统消息
	 */
	public static final String QUETY_PARAM_SYS_INFORM_MSG_CATE = "msgCate";
}
