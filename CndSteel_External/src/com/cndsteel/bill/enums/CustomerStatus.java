package com.cndsteel.bill.enums;

/**
 * 我司收发状态
 * @author zhxl
 *
 */
public enum CustomerStatus {
	UNRECEIVED("未收取"),
	SENDOUT("已寄出"),
	RECEIVED("已收取");
	
	private String value;
	
	private CustomerStatus(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
