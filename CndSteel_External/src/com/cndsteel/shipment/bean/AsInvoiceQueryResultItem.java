package com.cndsteel.shipment.bean;

public class AsInvoiceQueryResultItem {
	
	public String number;
	public String tonnage;
	public String numberOfPackages;
	public String warehouse;
	public String date;
	
	public AsInvoiceQueryResultItem(String number,String tonnage,String numberOfPackages,String warehouse,String date) {
		this.number = number;
		this.tonnage = tonnage;
		this.numberOfPackages = numberOfPackages;
		this.warehouse = warehouse;
		this.date = date;
	}

}
