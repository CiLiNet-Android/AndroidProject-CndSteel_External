package com.cndsteel.shipment.bean;

import com.cndsteel.framework.bean.BaseBean;

public class ShipmentBean extends BaseBean {
	
	private static final long serialVersionUID = 1586636084461891278L;
	
	/** 发货单ID **/
	public String shipId;
	
	/** 发货单号 **/
	public String shipNO;
	
	/** 发货日期 **/
	public String shipDate;
	
	/** 仓库名称 **/
	public String storeName;
	
	/** 吨数 **/
	public String weight;
	
	/** 件数 **/
	public String piece;

	/** 品名id **/
	public String pnameId;
	
	/** 品名 **/
	public String pname;
	
	/** 材质id **/
	public String materialId;
	
	/** 材质 **/
	public String material;
	
	/** 规格 **/
	public String spec;
	
	/** 金额 **/
	public String amount;

	/** 捆包号 **/
	public String pkgNo;
	
	/** 合同ID **/
	public String conId;
	
	/** 合同号 **/
	public String conCode;
	
}
