package com.cndsteel.payment.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.contract.beans.ContractBean;
import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;

public class PaymentAsInformationQueryResultListBean extends WebServiceBean {

	private static final long serialVersionUID = -9203961511265556614L;

	@Override
	public String getServiceUrl() {
		return Constants.WEB_SERVICE_BASE_URL + "/PaymentService";
	}

	@Override
	public String getNamespace() {
		return Constants.WEB_SERVICE_NAMESPACE;
	}

	@Override
	public String getMethodName() {
		return "listPayment";
	}
	
	public ArrayList<PaymentBean> paymentBeans;

	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _defaultHandler = new DefaultHandler(){
			
			private String mCurrentElementName;
			
			private PaymentBean mPaymentBean;
			
			@Override
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					paymentBeans = new ArrayList<PaymentBean>();
				}else if(null != paymentBeans && "obj".equals(mCurrentElementName)){
					mPaymentBean = new PaymentBean();
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
				}else if("payId".equals(mCurrentElementName)){
					mPaymentBean.payId = _text;
				}else if("billNO".equals(mCurrentElementName)){
					mPaymentBean.billNO = _text;
				}else if("payDate".equals(mCurrentElementName)){
					mPaymentBean.payDate = _text;
				}else if("payMode".equals(mCurrentElementName)){
					mPaymentBean.payMode = _text;
				}else if("amount".equals(mCurrentElementName)){
					mPaymentBean.amount = _text;
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					paymentBeans.add(mPaymentBean);
					mPaymentBean = null;
				}
			}

		};
		
		return _defaultHandler;
	}

}
