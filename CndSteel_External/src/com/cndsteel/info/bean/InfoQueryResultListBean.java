package com.cndsteel.info.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;

public class InfoQueryResultListBean extends WebServiceBean {
	
	private static final long serialVersionUID = 3750971024885525932L;

	@Override
	public String getServiceUrl() {
		return Constants.WEB_SERVICE_BASE_URL + "/MicroMessageService";
	}

	@Override
	public String getNamespace() {
		return Constants.WEB_SERVICE_NAMESPACE;
	}

	@Override
	public String getMethodName() {
		return "listMessage";
	}
	
	public ArrayList<InfoBean> infoBeans;

	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _defaultHandler = new DefaultHandler(){
			
			private String mCurrentElementName;
			private InfoBean mInfoBean;
			
			@Override
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					infoBeans = new ArrayList<InfoBean>();
				}if(null != infoBeans && "obj".equals(mCurrentElementName)){
					mInfoBean = new InfoBean();
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
				}else if("msgId".equals(mCurrentElementName)){
					mInfoBean.msgId = _text;
				}else if("title".equals(mCurrentElementName)){
					mInfoBean.title = _text;
				}else if("content".equals(mCurrentElementName)){
					mInfoBean.content = _text;
				}else if("cateName".equals(mCurrentElementName)){
					mInfoBean.cateName = _text;
				}else if("replyCount".equals(mCurrentElementName)){
					mInfoBean.replyCount = _text;
				}else if("author".equals(mCurrentElementName)){
					mInfoBean.author = _text;
				}else if("date".equals(mCurrentElementName)){
					mInfoBean.date = _text;
				}
			}
			
			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					infoBeans.add(mInfoBean);
					mInfoBean = null;
				}
			}
			
			
			
			
		};
		
		return _defaultHandler;
	}
}
