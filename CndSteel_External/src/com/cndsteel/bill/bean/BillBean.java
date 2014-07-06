package com.cndsteel.bill.bean;

import com.cndsteel.framework.bean.BaseBean;

public class BillBean extends BaseBean{

	private static final long serialVersionUID = -7833353333493295760L;

	/** 合同Id **/
	public String conId;
	
	/** 合同号 **/
	public String conCode;
	
	/** 我司收发状态 **/
	public String customerStatus;
	
	/** 物资收发状态 **/
	public String wzStatus;
	
	/** 合同金额 **/
	public String conAmt;
	
	/** 已开票金额 **/
	public String invedAmt;
	
	/** 未开票金额 **/
	public String uninvAmt;
	
	/** 发票号 **/
	public String invNO;
	
	/** 开票日期 **/
	public String invDate;
	
	/** 发票金额 **/
	public String amount;
	
	/** 签收状态 **/
	public String signStatus;
}
