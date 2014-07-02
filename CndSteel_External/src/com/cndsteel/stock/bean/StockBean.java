package com.cndsteel.stock.bean;

import com.cndsteel.framework.bean.BaseBean;

public class StockBean extends BaseBean {

	private static final long serialVersionUID = -5030513389008905318L;
	
	/** 品名 **/
	public String pnameId;
	public String pname;
	
	/** 材质 **/
	public String materialId;
	public String material;
	
	/** 规格 **/
	public String spec;
	
	/** 吨数 **/
	public String weight;
	
	/** 件数 **/
	public String piece;
	
	/** 金额 **/
	public String amount;
	
	/** 入库日期 **/
	public String storeDate;
	
	/** 捆包号 **/
	public String pkgNo;
	
	/** 仓库id **/
	public String wareId;
	/** 仓库名 **/
	public String wareName;
}
