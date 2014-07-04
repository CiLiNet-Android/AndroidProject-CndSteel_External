package com.cndsteel.shipment.bean;

import java.util.ArrayList;

import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;

public class ShipmentDetailQueryResultListBean extends WebServiceBean {

	private static final long serialVersionUID = -3013683492586589781L;

	@Override
	public String getServiceUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNamespace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMethodName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<ShipmentBean> shipmentBeans;

	@Override
	public DefaultHandler getXmlParserHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
