package com.cndsteel.framework.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.constant.Constants;

/**
 * 加载品名
 * @author zhxl
 *
 */
public class LoadPNameBean extends WebServiceBean {

	private static final long serialVersionUID = -4621236409641656296L;

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
		return "loadPname";
	}
	
	public ArrayList<PNameBean> pNameBeans;

	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _defaultHandler = new DefaultHandler(){

			private String mCurrentElementName;
			
			private PNameBean mPNameBean;
			
			@Override
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					pNameBeans = new ArrayList<PNameBean>();
				}if(null != pNameBeans && "obj".equals(mCurrentElementName)){
					mPNameBean = new PNameBean();
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
				}else if("id".equals(mCurrentElementName)){
					mPNameBean.id = _text;
				}else if("name".equals(mCurrentElementName)){
					mPNameBean.name = _text;
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					pNameBeans.add(mPNameBean);
					mPNameBean = null;
				}
			}
			
		};
		
		return _defaultHandler;
	}

}
