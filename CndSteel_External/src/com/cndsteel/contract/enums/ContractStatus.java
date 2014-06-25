package com.cndsteel.contract.enums;

/**
 * 合同状态
 * @author zhxl
 *
 */
public enum ContractStatus {
	TEMPORAL("暂存"),
	OPENED("未完结"),
	CLOSED("完结"),
	INVALID("作废");
	
	private String status;
	
	private ContractStatus(String status){
		this.status = status;
	}
	
	public String value(){
		return status;
	}
}
