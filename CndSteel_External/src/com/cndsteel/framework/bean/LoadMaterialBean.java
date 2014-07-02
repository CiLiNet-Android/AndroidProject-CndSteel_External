package com.cndsteel.framework.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.constant.Constants;


/**
 * 加载材质
 * @author zhxl
 *
 */
public class LoadMaterialBean extends WebServiceBean {

	private static final long serialVersionUID = 1578711164801257420L;

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
		return "loadMaterial";
	}
	
	public ArrayList<MaterialBean> materialBeans;

	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _defaultHandler = new DefaultHandler(){
			
			private String mCurrentElementName;
			
			private MaterialBean mMaterialBean;
			
			@Override
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					materialBeans = new ArrayList<MaterialBean>();
				}if(null != materialBeans && "obj".equals(mCurrentElementName)){
					mMaterialBean = new MaterialBean();
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
					mMaterialBean.id = _text;
				}else if("name".equals(mCurrentElementName)){
					mMaterialBean.name = _text;
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					materialBeans.add(mMaterialBean);
					mMaterialBean = null;
				}
			}
			
		};
		
		return _defaultHandler;
	}

}
