package com.cndsteel.main.bean;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.ksoap2.serialization.SoapObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.log.GlobalLog;

public class LoginBean extends WebServiceBean {
	private static final long serialVersionUID = 2120861506404482772L;

	@Override
	public String getServiceUrl() {
		return "http://vip.cndsteel.com/services/LoginService.LoginServiceHttpSoap11Endpoint/";
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

	/**
	 * 数据解析
	 */
	@Override
	public void obtainDataInSoapObj(SoapObject soapObj) {
		String _responseStr = soapObj.getProperty("return").toString();
		
		GlobalLog.i("responseStr: " + _responseStr);
		
		try{
			SAXParserFactory _parserFactory = SAXParserFactory.newInstance();
			SAXParser _parser = _parserFactory.newSAXParser();
			_parser.parse(new ByteArrayInputStream(_responseStr.getBytes(Constants.CHARACTER_ENCODING)), new XmlParserHandler());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * sax解析XML
	 * @author zhxl
	 *
	 */
	private class XmlParserHandler extends DefaultHandler {
		
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
			}else if("sessionId".equals(currentElementName)){
				sessionId = _text;
			}
		}
		
	}

}
