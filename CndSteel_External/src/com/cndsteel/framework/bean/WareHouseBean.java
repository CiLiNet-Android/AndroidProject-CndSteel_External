package com.cndsteel.framework.bean;

/**
 * 仓库
 * @author zhxl
 *
 */
public class WareHouseBean extends BaseBean {
	private static final long serialVersionUID = -722094991919179192L;
	
	/** 仓库ID **/
	public String id;
	/** 仓库名称 **/
	public String name;
	
	public WareHouseBean(){}
	
	public WareHouseBean(String id,String name){
		this.id = id;
		this.name = name;
	}
}
