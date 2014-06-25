package com.cndsteel.plan.beans;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;

public class PlanQueryResultDetailBean extends WebServiceBean {

	private static final long serialVersionUID = -2408490768480491391L;

	@Override
	public String getServiceUrl() {
		return Constants.WEB_SERVICE_BASE_URL;
	}

	@Override
	public String getNamespace() {
		return Constants.WEB_SERVICE_NAMESPACE;
	}

	@Override
	public String getMethodName() {
		return "loadBookingDetail";
	}
	
	/** 计划ID **/
	public String bookingId;
	/** 计划年月 **/
	public String schDate;
	/** 应收保证金金额 **/
	public String receivedMargin;
	/** 应收保证日期 **/
	public String receivedMarginDate;
	/** 状态 **/
	public String status;
	/** 计划吨数 **/
	public String weight;
	/** 实际吨数 **/
	public String actWeight;
	
	public ArrayList<PlanBean> planBeans;
	

	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _xmlParserHandler = new DefaultHandler(){
			/** 记录当前解析的元素名 **/
			private String mCurrentElementName;
			
			private PlanBean mPlanBean;
			
			private boolean mObjElementStart = false;

			@Override
			public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "itemList".equals(mCurrentElementName)){
					planBeans = new ArrayList<PlanBean>();
				}if(null != planBeans && "obj".equals(mCurrentElementName)){
					mPlanBean = new PlanBean();
					mObjElementStart = true;
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
				}else if("bookingId".equals(mCurrentElementName)){
					bookingId = _text;
				}else if("schDate".equals(mCurrentElementName)){
					schDate = _text;
				}else if("receivedMargin".equals(mCurrentElementName)){
					receivedMargin = _text;
				}else if("receivedMarginDate".equals(mCurrentElementName)){
					receivedMarginDate = _text;
				}else if("status".equals(mCurrentElementName)){
					status = _text;
				}else if("weight".equals(mCurrentElementName) && !mObjElementStart){
					weight = _text;
				}else if("actWeight".equals(mCurrentElementName) && !mObjElementStart){
					actWeight = _text;
				}
				
				//Obj
				else if("pname".equals(mCurrentElementName)){
					mPlanBean.pname = _text;
				}else if("material".equals(mCurrentElementName)){
					mPlanBean.material = _text;
				}else if("spec".equals(mCurrentElementName)){
					mPlanBean.spec = _text;
				}else if("weight".equals(mCurrentElementName) && mObjElementStart){
					mPlanBean.weight = _text;
				}else if("actWeight".equals(mCurrentElementName) && mObjElementStart){
					mPlanBean.actWeight = _text;
				}else if("steel".equals(mCurrentElementName)){
					mPlanBean.steel = _text;
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName)throws SAXException {
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					planBeans.add(mPlanBean);
					mPlanBean = null;
					
					mObjElementStart = false;
				}
			}
			
		};
		
		return _xmlParserHandler;
	}

}
