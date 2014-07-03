package com.cndsteel.sysinform.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;

public class SysInformQueryResultListBean extends WebServiceBean {

	private static final long serialVersionUID = -5812894086821160652L;
	
	public ArrayList<SysInformBean> mSysInformBeanList;

	@Override
	public String getServiceUrl() {
		return Constants.WEB_SERVICE_BASE_URL + "/SystemMessageService";
	}

	@Override
	public String getNamespace() {
		return Constants.WEB_SERVICE_NAMESPACE;
	}

	@Override
	public String getMethodName() {
		return "listSysMessage";
	}

	@Override
	public DefaultHandler getXmlParserHandler() {
		
		DefaultHandler _defaultHandler = new DefaultHandler(){
			
			private String mCurrentElementName;
			private SysInformBean mSysInformBean;

			@Override
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					mSysInformBeanList = new ArrayList<SysInformBean>();
				}
				
				if(null != mSysInformBeanList && "obj".equals(mCurrentElementName)){
					mSysInformBean = new SysInformBean();
				}
				
			}

			@Override
			public void characters(char[] ch, int start, int length)throws SAXException {
				
				String _text = new String(ch,start,length);
				
				if("state".equals(mCurrentElementName)){
					state = Constants.TRUE.equals(_text);
				}else if("code".equals(mCurrentElementName)){
					code = Integer.valueOf(_text);
				}else if("detail".equals(mCurrentElementName)){
					if(state == false){
						errorMsg = _text;
					}
				}else if("title".equals(mCurrentElementName)){
					mSysInformBean.title = _text;
				}else if("content".equals(mCurrentElementName)){
					mSysInformBean.content = _text;
				}else if("cateName".equals(mCurrentElementName)){
					mSysInformBean.cateName = _text;
				}else if("author".equals(mCurrentElementName)){
					mSysInformBean.author = _text;
				}else if("date".equals(mCurrentElementName)){
					mSysInformBean.date = _text;
				}
				
			}

			@Override
			public void endElement(String uri, String localName, String qName)throws SAXException {		
				
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					mSysInformBeanList.add(mSysInformBean);
					mSysInformBean = null;
				}	
			}
		
		};
		
		return _defaultHandler;
	}

}
