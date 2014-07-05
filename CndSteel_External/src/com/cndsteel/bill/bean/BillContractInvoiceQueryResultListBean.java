package com.cndsteel.bill.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;

public class BillContractInvoiceQueryResultListBean extends WebServiceBean {

	private static final long serialVersionUID = -8635407532460787297L;

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
		return "listConinv";
	}

	public ArrayList<BillBean> mBillBeansList;
	
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
				}else if("conId".equals(mCurrentElementName)){
					billBean.conId = _text;
				}else if("conCode".equals(mCurrentElementName)){
					billBean.conCode = _text;
				}else if("conAmt".equals(mCurrentElementName)){
					billBean.conAmt = _text;
				}else if("invedAmt".equals(mCurrentElementName)){
					billBean.invedAmt = _text;
				}else if("uninvAmt".equals(mCurrentElementName)){
					billBean.uninvAmt = _text;
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
