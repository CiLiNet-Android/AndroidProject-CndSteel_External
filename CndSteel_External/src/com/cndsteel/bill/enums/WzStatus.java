package com.cndsteel.bill.enums;

/**
 * 物资收发状态
 * @author zhxl
 *
 */
public enum WzStatus {
	UNRECEIVED("未收取"),
	SENDOUT("已寄出"),
	RECEIVED("已收取");
	
	private String value;
	
	private WzStatus(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
