package com.cndsteel.bill.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;

public class BillContractInvoiceQueryResulltDetailBean extends WebServiceBean {

	private static final long serialVersionUID = -4077378154471389908L;
	
	public ArrayList<BillBean> mBillBeansList;

	@Override
	public String getServiceUrl() {
		return Constants.WEB_SERVICE_BASE_URL + "/BillPaperService";
	}

	@Override
	public String getNamespace() {
		return Constants.WEB_SERVICE_NAMESPACE;
	}

	@Override
	public String getMethodName() {
		return "getConinv";
	}

	@Override
	public DefaultHandler getXmlParserHandler() {
		
		DefaultHandler _xmlParserHandler = new DefaultHandler(){
			
			private String mCurrentElementName;

			private BillBean billBean;
			
			@Override
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					mBillBeansList = new ArrayList<BillBean>();
				}
				if(null != mBillBeansList && "obj".equals(mCurrentElementName)){
					billBean = new BillBean();
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
				}else if("invNO".equals(mCurrentElementName)){
					billBean.invNO = _text;
				}else if("invDate".equals(mCurrentElementName)){
					billBean.invDate = _text;
				}else if("amount".equals(mCurrentElementName)){
					billBean.amount = _text;
				}else if("signStatus".equals(mCurrentElementName)){
					billBean.signStatus = _text;
				}
				
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					mBillBeansList.add(billBean);
					billBean = null;
				}
			}			
		};
		
		return _xmlParserHandler;
	}
}
