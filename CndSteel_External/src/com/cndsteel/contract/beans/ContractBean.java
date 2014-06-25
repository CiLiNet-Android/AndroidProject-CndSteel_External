package com.cndsteel.contract.beans;

import com.cndsteel.framework.bean.BaseBean;

public class ContractBean extends BaseBean{

	private static final long serialVersionUID = 5958490587286960992L;
	
	
	/** 合同Id **/
	public String id;
	/** 合同号 **/
	public String conCode;
	/** 状态[枚举，同参数] **/
	public String status;
	/** 合同年月 **/
	public String conDate;
	/** 品名 **/
	public String pname;
	/** 吨数 **/
	public String weight;
	/** 已提吨数 **/
	public String shipWeight;
	/** 未提吨数 **/
	public String unshipWeight;
	
	
	/** 材质 **/
	public String material;
	/** 规格 **/
	public String spec;
	/** 钢厂 **/
	public String steel;

}
