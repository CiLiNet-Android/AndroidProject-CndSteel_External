package com.cndsteel.framework.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.plan.beans.PlanBean;

/**
 * 仓库加载
 * @author zhxl
 *
 */
public class LoadWareHouseBean extends WebServiceBean {

	private static final long serialVersionUID = -7524333061697216121L;

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
		return "loadWarehouseName";
	}
	
	public ArrayList<WareHouseBean> wareHouseBeans;
	
	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _defaultHandler = new DefaultHandler(){
			
			private String mCurrentElementName;
			
			private WareHouseBean mWareHouseBean;
			
			@Override
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					wareHouseBeans = new ArrayList<WareHouseBean>();
				}if(null != wareHouseBeans && "obj".equals(mCurrentElementName)){
					mWareHouseBean = new WareHouseBean();
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
				}else if("id".equals(mCurrentElementName)){
					mWareHouseBean.id = _text;
				}else if("name".equals(mCurrentElementName)){
					mWareHouseBean.name = _text;
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName)throws SAXException {
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					wareHouseBeans.add(mWareHouseBean);
					mWareHouseBean = null;
				}
			}
			
		};
		
		return _defaultHandler;
	}
}
