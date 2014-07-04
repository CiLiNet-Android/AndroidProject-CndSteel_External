package com.cndsteel.framework.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.constant.Constants;

public class LoadSysMsgCateBean extends WebServiceBean {

	private static final long serialVersionUID = -4449303405435074555L;
	
	/** 返回的类别代码 **/
	public String sysMsgCeteCode;
	
	public ArrayList<SysMsgCateBean> mSysMsgCateBeansList;

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
		return "loadSysMsgCate";
	}

	@Override
	public DefaultHandler getXmlParserHandler() {
		
		DefaultHandler _xmlParserHandler = new DefaultHandler(){
			
			private String mCurrentElementName;
			
			private SysMsgCateBean sysMsgCateBean;
			
			@Override
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					mSysMsgCateBeansList = new ArrayList<SysMsgCateBean>();
				}
				
				if(null != mSysMsgCateBeansList && "obj".equals(mCurrentElementName)){
					sysMsgCateBean = new SysMsgCateBean();
				}
				
			}

			@Override
			public void characters(char[] ch, int start, int length) throws SAXException {
				
				String _text = new String(ch,start,length);
				
				if("state".equals(mCurrentElementName)){
					state = Constants.TRUE.equals(_text);
				}else if("code".equals(mCurrentElementName)){
					sysMsgCeteCode = _text;
					if(null != sysMsgCateBean){
						sysMsgCateBean.code = _text;
					}
				}else if("detail".equals(mCurrentElementName)){
					if(state == false){
						errorMsg = _text;
					}
				}else if("name".equals(mCurrentElementName)){
					sysMsgCateBean.name = _text;
				}
				
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					mSysMsgCateBeansList.add(sysMsgCateBean);
					sysMsgCateBean = null;
				}
				
			}
		
		};
		
		return _xmlParserHandler;
	}

}
