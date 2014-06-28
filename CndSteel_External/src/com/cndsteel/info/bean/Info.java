package com.cndsteel.info.bean;

public class Info {
	public String title;
	public String date;
	public String name;
	public String comments;
	
	public Info(String title,String date,String name,String comments) {
		this.title = title;
		this.date = date;
		this.name = name;
		this.comments = comments;
	}
	
	public Info(String name,String date,String comments) {
		this.name = name;
		this.date = date;
		this.comments = comments;
	}
}
