package com.cndsteel.stock.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;

public class StockAccordingGoodsQueryResultDetailBean extends WebServiceBean {

	private static final long serialVersionUID = -7070031785959094676L;

	@Override
	public String getServiceUrl() {
		return Constants.WEB_SERVICE_BASE_URL + "/StoreService";
	}

	@Override
	public String getNamespace() {
		return Constants.WEB_SERVICE_NAMESPACE;
	}

	@Override
	public String getMethodName() {
		return "listItemByGoods";
	}
	
	public ArrayList<StockBean> stockBeans;

	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _defaultHandler = new DefaultHandler(){
			
			private String mCurrentElementName;
			
			private StockBean mStockBean;

			@Override
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					stockBeans = new ArrayList<StockBean>();
				}else if(null != stockBeans && "obj".equals(mCurrentElementName)){
					mStockBean = new StockBean();
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
				}else if("storeDate".equals(mCurrentElementName)){
					mStockBean.storeDate = _text;
				}else if("weight".equals(mCurrentElementName)){
					mStockBean.weight = _text;
				}else if("piece".equals(mCurrentElementName)){
					mStockBean.piece = _text;
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					stockBeans.add(mStockBean);
					mStockBean = null;
				}
			}
			
		};
		
		return _defaultHandler;
	}

}
