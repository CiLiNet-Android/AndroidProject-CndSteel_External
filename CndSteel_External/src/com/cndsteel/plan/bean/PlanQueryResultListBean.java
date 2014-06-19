package com.cndsteel.plan.bean;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.ksoap2.serialization.SoapObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.log.GlobalLog;


public class PlanQueryResultListBean extends WebServiceBean {
	
	private static final long serialVersionUID = 7790784698237961633L;
	
	public ArrayList<PlanBean> mPlanBeans;
	
	@Override
	public String getServiceUrl() {
		return "http://vip.cndsteel.com/services/BookingService";
	}
	@Override
	public String getNamespace() {
		return "http://impl.remote.webservice.steel.chinacnd.com";
	}
	@Override
	public String getMethodName() {
		return "listBooking";
	}
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
		
		private PlanBean planBean;
		
		@Override
		public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
			currentElementName = localName;
			
			if(true == state && "list".equals(currentElementName)){
				mPlanBeans = new ArrayList<PlanBean>();
			}if(null != mPlanBeans && "obj".equals(currentElementName)){
				planBean = new PlanBean();
			}
			
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
			}else if("bookingId".equals(currentElementName)){
				planBean.bookingId = _text;
			}else if("schDate".equals(currentElementName)){
				planBean.schDate = _text;
			}else if("weight".equals(currentElementName)){
				planBean.weight = _text;
			}else if("actWeight".equals(currentElementName)){
				planBean.actWeight = _text;
			}else if("receivedMargin".equals(currentElementName)){
				planBean.receivedMargin = _text;
			}else if("receivedMarginDate".equals(currentElementName)){
				planBean.receivedMarginDate = _text;
			}else if("status".equals(currentElementName)){
				planBean.status = _text;
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			if("obj".equals(localName)){
				mPlanBeans.add(planBean);
				planBean = null;
			}
		}
		
	}

}
