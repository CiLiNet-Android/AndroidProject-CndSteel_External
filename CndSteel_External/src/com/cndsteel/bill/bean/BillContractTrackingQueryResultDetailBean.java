package com.cndsteel.bill.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.plan.beans.PlanBean;

public class BillContractTrackingQueryResultDetailBean extends WebServiceBean {
	
	private static final long serialVersionUID = 2073152962562422792L;

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
		return "getConpaper";
	}
	
	/** 合同号 **/
	public String conCode;
	/** JF快递公司 **/
	public String wzDelivcom;
	/** JF快递单号 **/
	public String wzDelivNO;
	/** JF快递时间 **/
	public String wzDelivDate;
	/** JF收件人 **/
	public String wzDelivReceiver;
	/** JF签收人 **/
	public String wzDelivSigner;
	/** JF签收时间 **/
	public String wzDelivSignDate;
	/** JF备注 **/
	public String wzRemark;
	/** 快递公司 **/
	public String cuDelivcom;
	/** 快递单号 **/
	public String cuDelivNO;
	/** 快递时间 **/
	public String cuDelivDate;
	/** 收件人 **/
	public String cuDelivReceiver;
	/** 签收人 **/
	public String cuDelivSigner;
	/** 签收时间 **/
	public String cuDelivSignDate;
	/** 备注 **/
	public String cuRemark;
	
	
	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _xmlParserHandler = new DefaultHandler(){
			/** 记录当前解析的元素名 **/
			private String mCurrentElementName;
			
			@Override
			public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
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
				}else if("conCode".equals(mCurrentElementName)){
					conCode = _text;
				}else if("wzDelivcom".equals(mCurrentElementName)){
					wzDelivcom = _text;
				}else if("wzDelivNO".equals(mCurrentElementName)){
					wzDelivNO = _text;
				}else if("wzDelivDate".equals(mCurrentElementName)){
					wzDelivDate = _text;
				}else if("wzDelivReceiver".equals(mCurrentElementName)){
					wzDelivReceiver = _text;
				}else if("wzDelivSigner".equals(mCurrentElementName)){
					wzDelivSigner = _text;
				}else if("wzDelivSignDate".equals(mCurrentElementName)){
					wzDelivSignDate = _text;
				}else if("wzRemark".equals(mCurrentElementName)){
					wzRemark = _text;
				}else if("cuDelivcom".equals(mCurrentElementName)){
					cuDelivcom = _text;
				}else if("cuDelivNO".equals(mCurrentElementName)){
					cuDelivNO = _text;
				}else if("cuDelivDate".equals(mCurrentElementName)){
					cuDelivDate = _text;
				}else if("cuDelivReceiver".equals(mCurrentElementName)){
					cuDelivReceiver = _text;
				}else if("cuDelivSigner".equals(mCurrentElementName)){
					cuDelivSigner = _text;
				}else if("cuDelivSignDate".equals(mCurrentElementName)){
					cuDelivSignDate = _text;
				}else if("cuRemark".equals(mCurrentElementName)){
					cuRemark = _text;
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName)
					throws SAXException {
				mCurrentElementName = null;
			}
		};
		
		return _xmlParserHandler;
	}

}
