package com.cndsteel.shipment.bean;

import java.util.ArrayList;

import com.cndsteel.framework.bean.BaseBean;

public class ShipmentAsInvoiceNumQueryResultDetailListBean extends BaseBean {
	
	private static final long serialVersionUID = 2089993068374624687L;

	/** 合同号 **/
	public String conCode;
	
	/** 合同id **/
	public String conId;
	
	public ArrayList<ShipmentBean> shipmentBeans;

	
}
