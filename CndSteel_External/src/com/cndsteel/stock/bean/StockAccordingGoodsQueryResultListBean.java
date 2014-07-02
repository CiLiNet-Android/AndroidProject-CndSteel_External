package com.cndsteel.stock.bean;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;

public class StockAccordingGoodsQueryResultListBean extends WebServiceBean {

	private static final long serialVersionUID = -8480882604513716497L;

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
		return "listByGoods";
	}
	
	public ArrayList<StockAccordingGoodsQueryResultBean> stockAccordingGoodsQueryResultBeans;

	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _defaultHandler = new DefaultHandler(){

			private String mCurrentElementName;
			
			private StockAccordingGoodsQueryResultBean mStockAccordingGoodsQueryResultBean;
		
			private StockBean mStockBean;
			
			@Override
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "list".equals(mCurrentElementName)){
					stockAccordingGoodsQueryResultBeans = new ArrayList<StockAccordingGoodsQueryResultBean>();
				}else if(null != stockAccordingGoodsQueryResultBeans && "obj".equals(mCurrentElementName)){
					mStockAccordingGoodsQueryResultBean = new StockAccordingGoodsQueryResultBean();
				}else if(null != mStockAccordingGoodsQueryResultBean && "itemList".equals(mCurrentElementName)){
					mStockAccordingGoodsQueryResultBean.stockBeans = new ArrayList<StockBean>();
				}else if(null != mStockAccordingGoodsQueryResultBean && null != mStockAccordingGoodsQueryResultBean.stockBeans && "objItem".equals(mCurrentElementName)){
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
				}else if("pnameId".equals(mCurrentElementName)){
					mStockAccordingGoodsQueryResultBean.pNameId = _text;
				}else if("pname".equals(mCurrentElementName)){
					mStockAccordingGoodsQueryResultBean.pName = _text;
				}else if("materialId".equals(mCurrentElementName)){
					mStockAccordingGoodsQueryResultBean.materialId = _text;
				}else if("material".equals(mCurrentElementName)){
					mStockAccordingGoodsQueryResultBean.material = _text;
				}else if("spec".equals(mCurrentElementName)){
					mStockAccordingGoodsQueryResultBean.spec = _text;
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
					mStockAccordingGoodsQueryResultBean.stockBeans.add(mStockBean);
					mStockBean = null;
				}else if("itemList".equals(localName)){
					stockAccordingGoodsQueryResultBeans.add(mStockAccordingGoodsQueryResultBean);
					mStockAccordingGoodsQueryResultBean = null;
				}
			}
			
		};
		
		return _defaultHandler;
	}

}
