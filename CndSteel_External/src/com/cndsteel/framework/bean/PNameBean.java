package com.cndsteel.framework.bean;

/**
 * 品名
 * @author zhxl
 *
 */
public class PNameBean extends BaseBean {

	private static final long serialVersionUID = 5290937410178142704L;
	
	public String id;
	
	public String name;
	
	public PNameBean(){}
	
	public PNameBean(String id,String name){
		this.id = id;
		this.name = name;
	}

}
