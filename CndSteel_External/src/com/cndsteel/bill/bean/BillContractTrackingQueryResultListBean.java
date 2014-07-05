package com.cndsteel.bill.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;

public class BillContractTrackingQueryResultListBean extends WebServiceBean {

	private static final long serialVersionUID = -5390133388912897175L;

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
		return "listConpaper";
	}
	
	public ArrayList<BillBean> billBeans;

	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _xmlParserHandler = new DefaultHandler(){
			/** 记录当前解析的元素名 **/
			private String mCurrentElementName;
			
			private BillBean mBillBean;
			
			@Override
			public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					billBeans = new ArrayList<BillBean>();
				}else if(null != billBeans && "obj".equals(mCurrentElementName)){
					mBillBean = new BillBean();
				}
			}
			
			@Override
			public void characters(char[] ch, int start, int length)
					throws SAXException {
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
					mBillBean.conId = _text;
				}else if("conCode".equals(mCurrentElementName)){
					mBillBean.conCode = _text;
				}else if("customerStatus".equals(mCurrentElementName)){
					mBillBean.customerStatus = _text;
				}else if("wzStatus".equals(mCurrentElementName)){
					mBillBean.wzStatus = _text;
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName)
					throws SAXException {
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					billBeans.add(mBillBean);
					mBillBean = null;
				}
			}
		};
		
		return _xmlParserHandler;
	}

}
