package com.cndsteel.shipment.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;

public class ShipmentAsInvoiceNumQueryResultDetailBean extends WebServiceBean {

	private static final long serialVersionUID = -8564182605662327257L;

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
		return "listItemByShip";
	}
	
	public ArrayList<ShipmentAsInvoiceNumQueryResultDetailListBean> shipmentAsInvoiceNumQueryResultDetailListBeans;

	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _defaultHandler = new DefaultHandler(){

			private String mCurrentElementName;
			
			private ShipmentAsInvoiceNumQueryResultDetailListBean mShipmentAsInvoiceNumQueryResultDetailListBean;
		
			private ShipmentBean mShipmentBean;
			
			@Override
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					shipmentAsInvoiceNumQueryResultDetailListBeans = new ArrayList<ShipmentAsInvoiceNumQueryResultDetailListBean>();
				}else if(null != shipmentAsInvoiceNumQueryResultDetailListBeans && "obj".equals(mCurrentElementName)){
					mShipmentAsInvoiceNumQueryResultDetailListBean = new ShipmentAsInvoiceNumQueryResultDetailListBean();
				}else if(null != mShipmentAsInvoiceNumQueryResultDetailListBean && "itemList".equals(mCurrentElementName)){
					mShipmentAsInvoiceNumQueryResultDetailListBean.shipmentBeans = new ArrayList<ShipmentBean>();
				}else if(null != mShipmentAsInvoiceNumQueryResultDetailListBean && null != mShipmentAsInvoiceNumQueryResultDetailListBean.shipmentBeans && "objItem".equals(mCurrentElementName)){
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
				}else if("conCode".equals(mCurrentElementName)){
					mShipmentAsInvoiceNumQueryResultDetailListBean.conCode = _text;
				}else if("conId".equals(mCurrentElementName)){
					mShipmentAsInvoiceNumQueryResultDetailListBean.conId = _text;
				}else if("pnameId".equals(mCurrentElementName)){
					mShipmentBean.pnameId = _text;
				}else if("pname".equals(mCurrentElementName)){
					mShipmentBean.pname = _text;
				}else if("materialId".equals(mCurrentElementName)){
					mShipmentBean.materialId = _text;
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
					mShipmentAsInvoiceNumQueryResultDetailListBean.shipmentBeans.add(mShipmentBean);
					mShipmentBean = null;
				}else if("itemList".equals(localName)){
					shipmentAsInvoiceNumQueryResultDetailListBeans.add(mShipmentAsInvoiceNumQueryResultDetailListBean);
					mShipmentAsInvoiceNumQueryResultDetailListBean = null;
				}
			}
			
		};
		
		return _defaultHandler;
	}

}
