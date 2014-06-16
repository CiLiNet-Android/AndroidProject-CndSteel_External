package com.cndsteel.main.bean;

/**
 * 功能模块数据
 * @author zhxl
 *
 */
public class APPModuleBean {
	
	/** 名称资源ID**/
	public int nameResId;
	/** 图片资源ID **/
	public int iconResId;
	
	
	public APPModuleBean(int nameResId,int iconResId){
		this.nameResId = nameResId;
		this.iconResId = iconResId;
	}
}
