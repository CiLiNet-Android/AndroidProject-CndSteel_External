package com.cndsteel.framework.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.constant.Constants;

public class LoadPayModeBean extends WebServiceBean {

	private static final long serialVersionUID = -3590847349321666069L;

	@Override
	public String getServiceUrl() {
		return Constants.WEB_SERVICE_BASE_URL + "/CommonService";
	}

	@Override
	public String getNamespace() {
		return Constants.WEB_SERVICE_NAMESPACE;
	}

	@Override
	public String getMethodName() {
		return "loadPayMode";
	}
	
	public ArrayList<PayModeBean> payModeBeans;

	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _defaultHandler = new DefaultHandler(){

			private String mCurrentElementName;
			
			private PayModeBean mPayModeBean;
			
			@Override
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					payModeBeans = new ArrayList<PayModeBean>();
				}else if(null != payModeBeans && "obj".equals(mCurrentElementName)){
					mPayModeBean = new PayModeBean();
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
				}else if("value".equals(mCurrentElementName)){
					mPayModeBean.value = _text;
				}else if("text".equals(mCurrentElementName)){
					mPayModeBean.text = _text;
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					payModeBeans.add(mPayModeBean);
					mPayModeBean = null;
				}
			}			
			
		};
		
		return _defaultHandler;
	}

}
