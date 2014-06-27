package com.cndsteel.bill.bean;

public class BillContractTrackingDetailItem {
	
	/** 发送方 **/
	public String sendOffCompany;

	/** 快递公司 **/
	public String expressCompany;
	
	/** 快递单号 **/
	public String expressNumber;
	
	/** 快递时间 **/
	public String expressTime;
	
	/** 收件人 **/
	public String addressee;
	
	/** 签收人 **/
	public String signForPeople;
	
	/** 签收时间 **/
	public String signForTime;
	
	/** 备注 **/
	public String Comment;
	
	public BillContractTrackingDetailItem(String sendOffCompany,String expressCompany,String expressNumber,String expressTime,String addressee,
			String signForPeople,String signForTime,String Comment) {
		this.sendOffCompany = sendOffCompany;
		this.expressCompany = expressCompany;
		this.expressNumber = expressNumber;
		this.expressTime = expressTime;
		this.addressee = addressee;
		this.signForPeople = signForPeople;
		this.signForTime = signForTime;
		this.Comment = Comment;
	}
}
