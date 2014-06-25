package com.cndsteel.contract.beans;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;

public class ContractQueryResultListBean extends WebServiceBean {

	private static final long serialVersionUID = 2565572841029385986L;

	@Override
	public String getServiceUrl() {
		return Constants.WEB_SERVICE_BASE_URL + "/SaleContractService";
	}

	@Override
	public String getNamespace() {
		return Constants.WEB_SERVICE_NAMESPACE;
	}

	@Override
	public String getMethodName() {
		return "listContract";
	}
	
	public ArrayList<ContractBean> contractBeans;

	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _xmlParserHandler = new DefaultHandler(){
			/** 记录当前解析的元素名 **/
			private String mCurrentElementName;
			
			private ContractBean mContractBean;
			
			@Override
			public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					contractBeans = new ArrayList<ContractBean>();
				}if(null != contractBeans && "obj".equals(mCurrentElementName)){
					mContractBean = new ContractBean();
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
				}else if("id".equals(mCurrentElementName)){
					mContractBean.id = _text;
				}else if("conCode".equals(mCurrentElementName)){
					mContractBean.conCode = _text;
				}else if("status".equals(mCurrentElementName)){
					mContractBean.status = _text;
				}else if("conDate".equals(mCurrentElementName)){
					mContractBean.conDate = _text;
				}else if("pname".equals(mCurrentElementName)){
					mContractBean.pname = _text;
				}else if("weight".equals(mCurrentElementName)){
					mContractBean.weight = _text;
				}else if("shipWeight".equals(mCurrentElementName)){
					mContractBean.shipWeight = _text;
				}else if("unshipWeight".equals(mCurrentElementName)){
					mContractBean.unshipWeight = _text;
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName)
					throws SAXException {
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					contractBeans.add(mContractBean);
					mContractBean = null;
				}
			}
		};
		
		return _xmlParserHandler;
	}

}
