package com.cndsteel.shipmentIntent.bean;

public class ShipmentIntent {
	
	public String goodsName;  //品名
	public String material;  //材质
	public String standard;  //规格
	public String tonnage;  //吨数
	public String numberOfPackages;  //件数
	public int imgResId;
	
	public ShipmentIntent(String goodsName,String material,String standard,String tonnage,String NumberOfPackages,int imgResId) {
		this.goodsName = goodsName;
		this.material = material;
		this.standard = standard;
		this.tonnage = tonnage;
		this.numberOfPackages = NumberOfPackages;
		this.imgResId = imgResId;
	}

}
