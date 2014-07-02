package com.cndsteel.framework.bean;

/**
 * 材质
 * @author zhxl
 *
 */
public class MaterialBean extends BaseBean {
	
	private static final long serialVersionUID = 921555473881202465L;

	
	public String id;
	
	public String name;
	
	public MaterialBean(){}
	
	public MaterialBean(String id,String name){
		this.id = id;
		this.name = name;
	}
}
