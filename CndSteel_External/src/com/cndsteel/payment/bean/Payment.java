package com.cndsteel.payment.bean;

public class Payment {
	
	public String billNum;  //票据号
	public String paymentDate; //付款日期
	public String paymentMethod;  //付款方式
	public String money;  //金额
	public String contractNum;  //合同号
	public String use;  //用途
	
	public Payment(String billNum,String paymentDate,String paymentMethod,String money) {
		this.billNum = billNum;
		this.paymentDate = paymentDate;
		this.paymentMethod = paymentMethod;
		this.money = money;
	}
	
	public Payment(String contractNum,String paymentDate,String money,String use,String str) {
		this.contractNum = contractNum;
		this.paymentDate = paymentDate;
		this.money = money;
		this.use = use;
	}
	
}
