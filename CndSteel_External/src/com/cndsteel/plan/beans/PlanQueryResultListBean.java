package com.cndsteel.plan.beans;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;


public class PlanQueryResultListBean extends WebServiceBean {
	
	private static final long serialVersionUID = 7790784698237961633L;
	
	public ArrayList<PlanBean> mPlanBeans;
	
	@Override
	public String getServiceUrl() {
		return Constants.WEB_SERVICE_BASE_URL + "/BookingService";
	}
	@Override
	public String getNamespace() {
		return Constants.WEB_SERVICE_NAMESPACE;
	}
	@Override
	public String getMethodName() {
		return "listBooking";
	}

	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _xmlParserHandler = new DefaultHandler(){
			/** 记录当前解析的元素名 **/
			private String mCurrentElementName;
			
			private PlanBean planBean;
			
			@Override
			public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					mPlanBeans = new ArrayList<PlanBean>();
				}if(null != mPlanBeans && "obj".equals(mCurrentElementName)){
					planBean = new PlanBean();
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
				}else if("bookingId".equals(mCurrentElementName)){
					planBean.bookingId = _text;
				}else if("schDate".equals(mCurrentElementName)){
					planBean.schDate = _text;
				}else if("weight".equals(mCurrentElementName)){
					planBean.weight = _text;
				}else if("actWeight".equals(mCurrentElementName)){
					planBean.actWeight = _text;
				}else if("receivedMargin".equals(mCurrentElementName)){
					planBean.receivedMargin = _text;
				}else if("receivedMarginDate".equals(mCurrentElementName)){
					planBean.receivedMarginDate = _text;
				}else if("status".equals(mCurrentElementName)){
					planBean.status = _text;
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName)
					throws SAXException {
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					mPlanBeans.add(planBean);
					planBean = null;
				}
			}
		};
		
		return _xmlParserHandler;
	}

}
