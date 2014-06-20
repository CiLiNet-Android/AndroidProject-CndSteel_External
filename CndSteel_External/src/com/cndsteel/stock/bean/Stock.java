package com.cndsteel.stock.bean;

public class Stock {
	
	public String warehouseName;  //仓库名
	public String contractNum;  //合同号
	
	public String goodsName;  //品名
	public String material;  //材质
	public String standard;  //规格
	public String tonnage;  //吨数
	public String numberOfPackages; //件数
	public String money;  //金额
	public String warehouse;  //仓库
	
	
	public Stock(){}
	
	/** 库存——合同查询结果 **/
	public Stock(String goodsName,String material,String standard,String tonnage,String numberOfPackages,String warehouse) {
		
		this.goodsName = goodsName;
		this.material = material;
		this.standard = standard;
		this.tonnage = tonnage;
		this.numberOfPackages = numberOfPackages;
		this.warehouse = warehouse;
		
	}
	
	/** 库存——商品查询结果 **/
	public Stock(String warehouse,String tonnage,String numberOfPackages) {
		
		this.warehouse = warehouse;
		this.tonnage = tonnage;
		this.numberOfPackages = numberOfPackages;
		
	}
	
}
