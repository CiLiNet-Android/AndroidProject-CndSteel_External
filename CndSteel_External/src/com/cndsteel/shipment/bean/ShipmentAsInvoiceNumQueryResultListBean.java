package com.cndsteel.shipment.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.stock.bean.StockAccordingStockQueryResultBean;
import com.cndsteel.stock.bean.StockBean;

public class ShipmentAsInvoiceNumQueryResultListBean extends WebServiceBean {

	private static final long serialVersionUID = -3033566550775482939L;

	@Override
	public String getServiceUrl() {
		return Constants.WEB_SERVICE_BASE_URL + "/ShipmentService";
	}

	@Override
	public String getNamespace() {
		return Constants.WEB_SERVICE_NAMESPACE;
	}

	@Override
	public String getMethodName() {
		return "listByShip";
	}

	public ArrayList<ShipmentBean> shipmentBeans;
	
	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _defaultHandler = new DefaultHandler(){

			private String mCurrentElementName;
			
			private ShipmentBean mShipmentBean;
			
			@Override
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					shipmentBeans = new ArrayList<ShipmentBean>();
				}else if(null != shipmentBeans && "obj".equals(mCurrentElementName)){
					mShipmentBean = new ShipmentBean();
				}
			}
			
			@Override
			public void characters(char[] ch, int start, int length) throws SAXException {
				String _text = new String(ch,start,length);
				
				if("state".equals(mCurrentElementName)){
					state = Constants.TRUE.equals(_text);
				}else if("code".equals(mCurrentElementName)){
					code = Integer.valueOf(_text);
				}else if("detail".equals(mCurrentElementName)){
					if(state == false){
						errorMsg = _text;
					}
				}else if("shipId".equals(mCurrentElementName)){
					mShipmentBean.shipId = _text;
				}else if("shipNO".equals(mCurrentElementName)){
					mShipmentBean.shipNO = _text;
				}else if("shipDate".equals(mCurrentElementName)){
					mShipmentBean.shipDate = _text;
				}else if("storeName".equals(mCurrentElementName)){
					mShipmentBean.storeName = _text;
				}else if("weight".equals(mCurrentElementName)){
					mShipmentBean.weight = _text;
				}else if("piece".equals(mCurrentElementName)){
					mShipmentBean.piece = _text;
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					shipmentBeans.add(mShipmentBean);
					mShipmentBean = null;
				}
			}
			
		};
		
		return _defaultHandler;
	}

}
