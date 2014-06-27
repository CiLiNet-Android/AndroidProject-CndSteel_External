package com.cndsteel.bill.bean;

public class Bill {
	
	/** 合同号 **/
	public String title_contract_Num;
	
	/** 合同金额 **/
	public String contract_amountOfMoney;
	
	/** 已开票金额 **/
	public String alreadyMakeOutAnInvoiceAmount;
	
	/** 未开票金额 **/
	public String HaveNotMakeOutAnInvoiceAmount;

	public Bill(String title_contract_Num,String contract_amountOfMoney,String alreadyMakeOutAnInvoiceAmount,String HaveNotMakeOutAnInvoiceAmount) {
		this.title_contract_Num = title_contract_Num;
		this.contract_amountOfMoney = contract_amountOfMoney;
		this.alreadyMakeOutAnInvoiceAmount = alreadyMakeOutAnInvoiceAmount;
		this.HaveNotMakeOutAnInvoiceAmount = HaveNotMakeOutAnInvoiceAmount;
	}
	
	/** 建发收取状态 **/
	public String JianFaGetState;
	
	/** 我司收取状态 **/
	public String OurCompanyGetState;
	
	public Bill(String title_contract_Num,String JianFaGetState,String OurCompanyGetState) {
		this.title_contract_Num = title_contract_Num;
		this.JianFaGetState = JianFaGetState;
		this.OurCompanyGetState = OurCompanyGetState;
	}
	
}
