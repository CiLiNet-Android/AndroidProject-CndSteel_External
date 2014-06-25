package com.cndsteel.contract.beans;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.text.TextUtils;

import com.cndsteel.framework.bean.WebServiceBean;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.plan.beans.PlanBean;

public class ContractQueryResultDetailBean extends WebServiceBean {

	private static final long serialVersionUID = -400539904682036689L;

	@Override
	public String getServiceUrl() {
		return Constants.WEB_SERVICE_BASE_URL + "/SaleContractService";
	}

	@Override
	public String getNamespace() {
		return Constants.WEB_SERVICE_NAMESPACE;
	}

	@Override
	public String getMethodName() {
		return "loadContractById";
	}
	
	/** 合同号 **/
	public String conCode;
	/** 签约日期 **/
	public String signDate;
	/** 合同吨数 **/
	public String weight;
	/** 合同金额 **/
	public String amount;
	/** 应收保证日期 **/
	public String marginDate;
	/** 应收保证金金额 **/
	public String marginAmt;
	/** 实收保证日期 **/
	public String marginDated;
	/** 实收保证金金额 **/
	public String marginAmted;
	/** 到期日 **/
	public String dueDate;
	
	public ArrayList<ContractBean> contractBeans;

	@Override
	public DefaultHandler getXmlParserHandler() {
		DefaultHandler _xmlParserHandler = new DefaultHandler(){
			/** 记录当前解析的元素名 **/
			private String mCurrentElementName;
			
			private ContractBean mContractBean;
			
			private boolean mObjElementStart = false;

			@Override
			public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
				mCurrentElementName = localName;
				
				if(true == state && "itemList".equals(mCurrentElementName)){
					contractBeans = new ArrayList<ContractBean>();
				}if(null != contractBeans && "obj".equals(mCurrentElementName)){
					mContractBean = new ContractBean();
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
				}else if("conCode".equals(mCurrentElementName)){
					conCode = _text;
				}else if("signDate".equals(mCurrentElementName)){
					signDate = _text;
				}else if("weight".equals(mCurrentElementName) && !mObjElementStart){
					weight = _text;
				}else if("amount".equals(mCurrentElementName)){
					amount = _text;
				}else if("marginDate".equals(mCurrentElementName)){
					marginDate = _text;
				}else if("marginAmt".equals(mCurrentElementName)){
					marginAmt = _text;
				}else if("marginDated".equals(mCurrentElementName)){
					marginDated = _text;
				}else if("marginAmted".equals(mCurrentElementName)){
					marginAmted = _text;
				}else if("dueDate".equals(mCurrentElementName)){
					dueDate = _text;
				}
				
				//Obj
				else if("pname".equals(mCurrentElementName)){
					mContractBean.pname = _text;
				}else if("material".equals(mCurrentElementName)){
					mContractBean.material = _text;
				}else if("spec".equals(mCurrentElementName)){
					mContractBean.spec = _text;
				}else if("weight".equals(mCurrentElementName) && mObjElementStart){
					mContractBean.weight = _text;
				}else if("steel".equals(mCurrentElementName)){
					if(TextUtils.isEmpty(mContractBean.steel)){
						mContractBean.steel = _text;
					}
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName)throws SAXException {
				mCurrentElementName = null;
				
				if("obj".equals(localName)){
					contractBeans.add(mContractBean);
					mContractBean = null;
					
					mObjElementStart = false;
				}
			}
			
		};
		
		return _xmlParserHandler;
	}

}
