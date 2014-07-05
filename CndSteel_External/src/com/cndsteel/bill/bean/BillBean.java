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
	
}
