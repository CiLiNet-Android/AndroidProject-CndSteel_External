package com.cndsteel.framework.bean;

public class PayModeBean extends BaseBean {

	private static final long serialVersionUID = 7432897211223321443L;
	
	/** 支付方式显示的值 **/
	public String value;
	
	/** 支付方式显示字符串 **/
	public String text;
	
	public PayModeBean(){}
	
	public PayModeBean(String value,String text){
		this.value = value;
		this.text = text;
	}

}
