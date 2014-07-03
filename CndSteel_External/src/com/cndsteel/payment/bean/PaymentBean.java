package com.cndsteel.payment.bean;

import com.cndsteel.framework.bean.BaseBean;

public class PaymentBean extends BaseBean {
	
	private static final long serialVersionUID = -826059188400603129L;
	
	/** 付款Id **/
	public String payId;
	
	/** 票据号 **/
	public String billNO;	
	
	/** 付款日期 **/
	public String payDate;	
	
	/** 支付方式 **/
	public String payMode;
	
	/** 金额 **/
	public String amount;
	
	/** 合同号 **/
	public String conCode;
	
	/** 用途 **/
	public String expense;
	
	/** 合同id **/
	public String conId;
	
	/** 已付款金额 **/
	public String paidAmount;
	
	/** 未付款金额 **/
	public String needAmount;
	
}
