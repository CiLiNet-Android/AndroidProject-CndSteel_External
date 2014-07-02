package com.cndsteel.stock.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;

public class StockAccordingContractQueryResultListBean extends WebServiceBean {
	
	private static final long serialVersionUID = -8293192780993064748L;

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
		return "listByCon";
	}
	
	public ArrayList<StockAccordingContractQueryResultBean> stockAccordingContractQueryResultBeans;

	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _defaultHandler = new DefaultHandler(){

			private String mCurrentElementName;
			
			private StockAccordingContractQueryResultBean mStockAccordingContractQueryResultBean;
		
			private StockBean mStockBean;
			
			@Override
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					stockAccordingContractQueryResultBeans = new ArrayList<StockAccordingContractQueryResultBean>();
				}else if(null != stockAccordingContractQueryResultBeans && "obj".equals(mCurrentElementName)){
					mStockAccordingContractQueryResultBean = new StockAccordingContractQueryResultBean();
				}else if(null != mStockAccordingContractQueryResultBean && "itemList".equals(mCurrentElementName)){
					mStockAccordingContractQueryResultBean.stockBeans = new ArrayList<StockBean>();
				}else if(null != mStockAccordingContractQueryResultBean && null != mStockAccordingContractQueryResultBean.stockBeans && "objItem".equals(mCurrentElementName)){
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
				}else if("conId".equals(mCurrentElementName)){
					mStockAccordingContractQueryResultBean.conId = _text;
				}else if("conCode".equals(mCurrentElementName)){
					mStockAccordingContractQueryResultBean.conCode = _text;
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
				}else if("wareId".equals(mCurrentElementName)){
					mStockBean.wareId = _text;
				}else if("wareName".equals(mCurrentElementName)){
					mStockBean.wareName = _text;
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
					mStockAccordingContractQueryResultBean.stockBeans.add(mStockBean);
					mStockBean = null;
				}else if("itemList".equals(localName)){
					stockAccordingContractQueryResultBeans.add(mStockAccordingContractQueryResultBean);
					mStockAccordingContractQueryResultBean = null;
				}
			}
			
		};
		
		return _defaultHandler;
	}

}
