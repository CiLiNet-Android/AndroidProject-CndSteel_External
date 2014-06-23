package com.cndsteel.shipment.bean;

public class ShipmentAsInvoice {
	
	public String goodsName;
	public String material;
	public String standard;
	public String tonnage;
	public String numberOfPackages;
	public String money;
	
	public ShipmentAsInvoice(String goodsName,String material,String standard,String tonnage,String numberOfPackages,String money) {
		this.goodsName = goodsName;
		this.material = material;
		this.standard = standard;
		this.tonnage = tonnage;
		this.numberOfPackages = numberOfPackages;
		this.money = money;
	}

}
