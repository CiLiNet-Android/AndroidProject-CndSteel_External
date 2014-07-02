package com.cndsteel.stock.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;

public class StockAccordingStockQueryResultListBean extends WebServiceBean {
	
	private static final long serialVersionUID = 8819461483717777069L;

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
		return "listByWare";
	}

	public ArrayList<StockAccordingStockQueryResultBean> accordingStockQueryBeans; 
	
	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _defaultHandler = new DefaultHandler(){

			private String mCurrentElementName;
			
			private StockAccordingStockQueryResultBean mAccordingStockQueryResultBean;
		
			private StockBean mStockBean;
			
			@Override
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					accordingStockQueryBeans = new ArrayList<StockAccordingStockQueryResultBean>();
				}else if(null != accordingStockQueryBeans && "obj".equals(mCurrentElementName)){
					mAccordingStockQueryResultBean = new StockAccordingStockQueryResultBean();
				}else if(null != mAccordingStockQueryResultBean && "itemList".equals(mCurrentElementName)){
					mAccordingStockQueryResultBean.stockBeans = new ArrayList<StockBean>();
				}else if(null != mAccordingStockQueryResultBean && null != mAccordingStockQueryResultBean.stockBeans && "objItem".equals(mCurrentElementName)){
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
				}else if("wareId".equals(mCurrentElementName)){
					mAccordingStockQueryResultBean.wareId = _text;
				}else if("wareName".equals(mCurrentElementName)){
					mAccordingStockQueryResultBean.wareName = _text;
				}else if("pnameId".equals(mCurrentElementName)){
					mStockBean.pnameId = _text;
				}else if("pname".equals(mCurrentElementName)){
					mStockBean.pname = _text;
				}else if("materialId".equals(mCurrentElementName)){
					mStockBean.materialId = _text;
				}else if("material".equals(mCurrentElementName)){
					mStockBean.material = _text;
				}else if("spec".equals(mCurrentElementName)){
					mStockBean.spec = _text;
				}else if("weight".equals(mCurrentElementName)){
					mStockBean.weight = _text;
				}else if("piece".equals(mCurrentElementName)){
					mStockBean.piece = _text;
				}else if("amount".equals(mCurrentElementName)){
					mStockBean.amount = _text;
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				mCurrentElementName = null;
				
				if("objItem".equals(localName)){
					mAccordingStockQueryResultBean.stockBeans.add(mStockBean);
					mStockBean = null;
				}else if("itemList".equals(localName)){
					accordingStockQueryBeans.add(mAccordingStockQueryResultBean);
					mAccordingStockQueryResultBean = null;
				}
			}
			
		};
		
		return _defaultHandler;
	}

}
