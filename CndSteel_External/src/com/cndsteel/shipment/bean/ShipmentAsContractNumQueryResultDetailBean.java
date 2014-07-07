package com.cndsteel.shipment.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;

public class ShipmentAsContractNumQueryResultDetailBean extends WebServiceBean {

	private static final long serialVersionUID = 7480115832139806134L;

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
		return "listItemByCon";
	}
	
	public ArrayList<ShipmentAsContractNumQueryResultDetailListBean> shipmentAsContractNumQueryResultDetailListBeans;

	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _defaultHandler = new DefaultHandler(){

			private String mCurrentElementName;
			
			private ShipmentAsContractNumQueryResultDetailListBean mShipmentAsContractNumQueryResultDetailListBean;
		
			private ShipmentBean mShipmentBean;
			
			@Override
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					shipmentAsContractNumQueryResultDetailListBeans = new ArrayList<ShipmentAsContractNumQueryResultDetailListBean>();
				}else if(null != shipmentAsContractNumQueryResultDetailListBeans && "obj".equals(mCurrentElementName)){
					mShipmentAsContractNumQueryResultDetailListBean = new ShipmentAsContractNumQueryResultDetailListBean();
				}else if(null != mShipmentAsContractNumQueryResultDetailListBean && "itemList".equals(mCurrentElementName)){
					mShipmentAsContractNumQueryResultDetailListBean.shipmentBeans = new ArrayList<ShipmentBean>();
				}else if(null != mShipmentAsContractNumQueryResultDetailListBean && null != mShipmentAsContractNumQueryResultDetailListBean.shipmentBeans && "objItem".equals(mCurrentElementName)){
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
				}else if("shipNO".equals(mCurrentElementName)){
					mShipmentAsContractNumQueryResultDetailListBean.shipNO = _text;
				}else if("shipDate".equals(mCurrentElementName)){
					mShipmentAsContractNumQueryResultDetailListBean.shipDate = _text;
				}else if("storeName".equals(mCurrentElementName)){
					mShipmentAsContractNumQueryResultDetailListBean.storeName = _text;
				}else if("pname".equals(mCurrentElementName)){
					mShipmentBean.pname = _text;
				}else if("material".equals(mCurrentElementName)){
					mShipmentBean.material = _text;
				}else if("spec".equals(mCurrentElementName)){
					mShipmentBean.spec = _text;
				}else if("weight".equals(mCurrentElementName)){
					mShipmentBean.weight = _text;
				}else if("piece".equals(mCurrentElementName)){
					mShipmentBean.piece = _text;
				}else if("amount".equals(mCurrentElementName)){
					mShipmentBean.amount = _text;
				}
				
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				mCurrentElementName = null;
				
				if("objItem".equals(localName)){
					mShipmentAsContractNumQueryResultDetailListBean.shipmentBeans.add(mShipmentBean);
					mShipmentBean = null;
				}else if("itemList".equals(localName)){
					shipmentAsContractNumQueryResultDetailListBeans.add(mShipmentAsContractNumQueryResultDetailListBean);
					mShipmentAsContractNumQueryResultDetailListBean = null;
				}
			}
			
		};
		
		return _defaultHandler;
	}

}
