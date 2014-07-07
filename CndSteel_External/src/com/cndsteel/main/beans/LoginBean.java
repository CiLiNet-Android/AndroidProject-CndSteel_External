package com.cndsteel.main.beans;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;

public class LoginBean extends WebServiceBean {
	private static final long serialVersionUID = 2120861506404482772L;

	@Override
	public String getServiceUrl() {
		return "http://vip.cndsteel.com/services/LoginService";
	}

	@Override
	public String getNamespace() {
		return "http://impl.remote.webservice.steel.chinacnd.com";
	}

	@Override
	public String getMethodName() {
		return "login";
	}
	
	public String username;
	public String fullname;
	public String mail;
	public String cellphone;
	public String email;
	public String sessionId;
	

	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _xmlParserHandler = new DefaultHandler(){
			/** 记录当前解析的元素名 **/
			private String currentElementName;
			
			@Override
			public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
				currentElementName = localName;
			}
			
			@Override
			public void characters(char[] ch, int start, int length)
					throws SAXException {
				String _text = new String(ch,start,length);
				
				if("state".equals(currentElementName)){
					state = Constants.TRUE.equals(_text);
				}else if("code".equals(currentElementName)){
					code = Integer.valueOf(_text);
				}else if("detail".equals(currentElementName)){
					if(state == false){
						errorMsg = _text;
					}
				}else if("username".equals(currentElementName)){
					username = _text;
				}else if("fullname".equals(currentElementName)){
					fullname = _text;
				}else if("mail".equals(currentElementName)){
					mail = _text;
				}else if("cellphone".equals(currentElementName)){
					cellphone = _text;
				}else if("email".equals(currentElementName)){
					email = _text;
				}else if("sessionid".equals(currentElementName)){
					sessionId = _text;
				}
			}
			
		};
		
		return _xmlParserHandler;
	}

}
