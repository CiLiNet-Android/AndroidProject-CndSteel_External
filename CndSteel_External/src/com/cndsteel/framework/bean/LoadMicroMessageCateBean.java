package com.cndsteel.framework.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.text.TextUtils;

import com.cndsteel.framework.constant.Constants;

public class LoadMicroMessageCateBean extends WebServiceBean {

	private static final long serialVersionUID = -3651452139255653568L;

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
		return "loadMicroMessageCate";
	}

	public ArrayList<MicroMessageCateBean> microMessageCateBeans;
	
	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _xmlParserHandler = new DefaultHandler(){
			/** 记录当前解析的元素名 **/
			private String mCurrentElementName;
			
			private MicroMessageCateBean mMicroMessageCateBean;
			
			private boolean mIsObjStart;
			
			@Override
			public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					microMessageCateBeans = new ArrayList<MicroMessageCateBean>();
				}else if(null != microMessageCateBeans && "obj".equals(mCurrentElementName)){
					mMicroMessageCateBean = new MicroMessageCateBean();
					mIsObjStart = true;
				}
			}
			
			@Override
			public void characters(char[] ch, int start, int length)
					throws SAXException {
				String _text = new String(ch,start,length);
				
				if("state".equals(mCurrentElementName)){
					state = Constants.TRUE.equals(_text);
				}else if("code".equals(mCurrentElementName) && !mIsObjStart){
					code = Integer.valueOf(_text);
				}else if("detail".equals(mCurrentElementName)){
					if(state == false){
						errorMsg = _text;
					}
				}else if("code".equals(mCurrentElementName) && mIsObjStart){
					mMicroMessageCateBean.code = _text;
				}else if("name".equals(mCurrentElementName)){
					mMicroMessageCateBean.name = _text;
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName)
					throws SAXException {
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					if(TextUtils.isEmpty(mMicroMessageCateBean.code)){
						mMicroMessageCateBean.code = "";
					}
					microMessageCateBeans.add(mMicroMessageCateBean);
					mMicroMessageCateBean = null;
					mIsObjStart = false;
				}
			}
		};
		
		return _xmlParserHandler;
	}

}
