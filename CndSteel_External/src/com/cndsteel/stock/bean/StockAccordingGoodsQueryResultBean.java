package com.cndsteel.stock.bean;

import java.util.ArrayList;

import com.cndsteel.framework.bean.BaseBean;

public class StockAccordingGoodsQueryResultBean extends BaseBean {

	private static final long serialVersionUID = 9203235591437125909L;
	
	public ArrayList<StockBean> stockBeans;
	
	public String pNameId;
	public String pName;
	
	public String materialId;
	public String material;
	
	public String spec;
	
}
