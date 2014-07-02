package com.cndsteel.stock.bean;

import java.util.ArrayList;

import com.cndsteel.framework.bean.BaseBean;

public class StockAccordingContractQueryResultBean extends BaseBean {

	private static final long serialVersionUID = -6521707344825051646L;

	/** 合同Id **/
	public String conId;
	
	/** 合同号 **/
	public String conCode;
	
	/** 合同明细 **/
	public ArrayList<StockBean> stockBeans;
}
