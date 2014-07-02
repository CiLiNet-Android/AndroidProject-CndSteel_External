package com.cndsteel.stock.bean;

import java.util.ArrayList;

import com.cndsteel.framework.bean.BaseBean;

public class StockAccordingStockQueryResultBean extends BaseBean {

	private static final long serialVersionUID = -6200709647030974026L;

	/** 仓库id **/
	public String wareId;
	/** 仓库名称 **/
	public String wareName;
	
	/** 仓库库存 **/
	public ArrayList<StockBean> stockBeans;
	
}
