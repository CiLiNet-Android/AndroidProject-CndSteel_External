package com.cndsteel.sysinform.bean;

public class SysInform {
	
	public String userName;
	public String title;
	public String date;
	public String content;

	
	public SysInform(String title,String date) {
		this.title = title;
		this.date = date;
	}
	
	
	public SysInform(String userName,String date,String content) {
		this.userName = userName;
		this.date = date;
		this.content = content;
	}

}
