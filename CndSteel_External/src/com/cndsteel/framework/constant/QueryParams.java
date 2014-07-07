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
	public static final String QUERY_PARAM_WARE_ID = "wareId";
	public static final String QUERY_PARAM_WEIGHT = "weight";
	public static final String QUERY_PARAM_PIECE = "piece";
	public static final String QUERY_PARAM_SPEC = "spec";
	
	/**
	 * 登录
	 */
	public static final String QUERY_PARAM_USERNAME = "username";
	public static final String QUERY_PARAM_PASSWORD = "password";
	public static final String QUERY_PARAM_DEVICE_TOKEN = "deviceToken";

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
	 * 出仓
	 */
	public static final String QUERY_PARAM_SHIPMENT_SHIP_DATE_FROM = "shipDateFrom";
	public static final String QUERY_PARAM_SHIPMENT_SHIP_DATE_TO = "shipDateTo";
	public static final String QUERY_PARAM_SHIPMENT_SHIP_NO = "shipNO";
	public static final String QUERY_PARAM_SHIPMENT_SHIP_ID = "shipId";
	
	/**
	 * 票据
	 */
	public static final String QUERY_PARAM_BILL_CUSTOMER_STATUS = "customerStatus";
	public static final String QUERY_PARAM_BILL_WZ_STATUS = "wzStatus";
	
	public static final String QUERY_PARAM_BILL_CON_YEAR_MONT_FROM = "conYearMontFrom";
	public static final String QUERY_PARAM_BILL_CON_YEAR_MONT_TO = "conYearMontTo";
	
	public static final String QUERY_PARAM_BILL_INVEDAMT = "invedAmt";
	/**
	 * 资讯
	 */
	public static final String QUERY_PARAM_INFO_MSG_CATE = "msgCate";

	/**
	 * 系统消息
	 */
	public static final String QUETY_PARAM_SYS_INFORM_MSG_CATE = "msgCate";
}
