package com.cndsteel.bill.bean;

import com.cndsteel.framework.bean.BaseBean;

public class BillBean extends BaseBean{

	private static final long serialVersionUID = -7833353333493295760L;

	/** 合同号 **/
	public String txtV_contractInvoiceConCode;
	
	/** 合同金额 **/
	public String txtV_contractInvoiceConAmt;
	
	/** 已开票金额 **/
	public String txtV_contractInvoiceInvedAmt;
	
	/** 未开票金额 **/
	public String txtV_contractInvoiceUninvAmt;

	public BillBean(String title_contract_Num,String contract_amountOfMoney,String alreadyMakeOutAnInvoiceAmount,String HaveNotMakeOutAnInvoiceAmount) {
		this.txtV_contractInvoiceConCode = title_contract_Num;
		this.txtV_contractInvoiceConAmt = contract_amountOfMoney;
		this.txtV_contractInvoiceInvedAmt = alreadyMakeOutAnInvoiceAmount;
		this.txtV_contractInvoiceUninvAmt = HaveNotMakeOutAnInvoiceAmount;
	}
	
	/** 建发收取状态 **/
	public String txtV_contractTrackingWzStatus;
	
	/** 我司收取状态 **/
	public String txtV_contractTrackingCustomerStatus;
	
	public BillBean(String title_contract_Num,String JianFaGetState,String OurCompanyGetState) {
		this.txtV_contractInvoiceConCode = title_contract_Num;
		this.txtV_contractTrackingWzStatus = JianFaGetState;
		this.txtV_contractTrackingCustomerStatus = OurCompanyGetState;
	}
	
}
