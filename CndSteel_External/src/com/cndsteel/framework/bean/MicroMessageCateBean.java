package com.cndsteel.framework.bean;

public class MicroMessageCateBean extends BaseBean {

	private static final long serialVersionUID = 4143199146852657934L;
	
	/** 类别代码 **/
	public String code;
	
	/** 类别名称 **/
	public String name;
	
	public MicroMessageCateBean(){}
	
	public MicroMessageCateBean(String code,String name){
		this.code = code;
		this.name = name;
	}
}
