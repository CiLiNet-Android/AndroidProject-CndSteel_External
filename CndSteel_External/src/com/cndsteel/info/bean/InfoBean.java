package com.cndsteel.info.bean;

import com.cndsteel.framework.bean.BaseBean;

public class InfoBean extends BaseBean {

	private static final long serialVersionUID = -4492597898273360023L;

	/** 咨询ID **/
	public String msgId;
	
	/** 标题 **/
	public String title;
	
	/** 资讯内容 **/
	public String content;
	
	/** 资讯类别 **/
	public String cateName;
	
	/** 评论数 **/
	public String replyCount;
	
	/** 作者 **/
	public String author;
	
	/** 日期 **/
	public String date;
}
