package com.cndsteel.plan.beans;

import com.cndsteel.framework.bean.BaseBean;

public class PlanBean extends BaseBean {
	private static final long serialVersionUID = -1629323268097871772L;
	
	/**
	 * 计划Id
	 */
	public String bookingId;
	
	/**
	 * 计划年月
	 */
	public String schDate;
	
	/**
	 * 预订吨数
	 */
	public String weight;
	
	/**
	 * 实际吨数
	 */
	public String actWeight;
	
	/**
	 * 应收保证金金额
	 */
	public String receivedMargin;
	
	/**
	 * 应收保证金日期
	 */
	public String receivedMarginDate;
	
	/**
	 * 计划状态
	 */
	public String status;
	
	/** 钢厂 **/
	public String steel;
	
	/** 规格 **/
	public String pname;
	/** 材质 **/
	public String material;
	/** 规格 **/
	public String spec;

}
