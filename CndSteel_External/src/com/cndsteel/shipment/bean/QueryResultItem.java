package com.cndsteel.shipment.bean;

public class QueryResultItem {
	
	public String number;
	public String tonnage;
	public String numberOfPackages;
	public String warehouse;
	public String date;
	
	public QueryResultItem(String number,String tonnage,String numberOfPackages,String warehouse,String date) {
		this.number = number;
		this.tonnage = tonnage;
		this.numberOfPackages = numberOfPackages;
		this.warehouse = warehouse;
		this.date = date;
	}

	public QueryResultItem(String number,String tonnage,String numberOfPackages) {
		this.number = number;
		this.tonnage = tonnage;
		this.numberOfPackages = numberOfPackages;
	}
}
