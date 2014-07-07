package com.cndsteel.shipment.bean;

import java.util.ArrayList;

import com.cndsteel.framework.bean.BaseBean;

public class ShipmentAsContractNumQueryResultDetailListBean extends BaseBean {
	
	private static final long serialVersionUID = 2089993068374624687L;

	/** 发货单号 **/
	public String shipNO;
	
	/** 发货日期 **/
	public String shipDate;
	
	/** 仓库名称 **/
	public String storeName;
	
	public ArrayList<ShipmentBean> shipmentBeans;

}
