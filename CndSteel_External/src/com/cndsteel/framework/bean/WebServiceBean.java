package com.cndsteel.framework.bean;

import org.ksoap2.serialization.SoapObject;

public abstract class WebServiceBean extends BaseBean {

	private static final long serialVersionUID = 135889134607703605L;

	/** 返回值状态，标识接口调用成功或者失败 true为成功，false为失败 **/
	public boolean state = false;
	
	/** 状态码
	 * 成功：影响的数据个数；
	 *  失败：错误码
	**/
	public int code;
	
	/**
	 * 错误信息
	 */
	public String errorMsg;
	
	/**
	 * 返回WebService的serviceUrl
	 */
	public abstract String getServiceUrl();

	/**
	 * 返回WebService的Namespace
	 */
	public abstract String getNamespace();

	/**
	 * 返回WebService的调用方法名
	 */
	public abstract String getMethodName();
	
	/**
	 * 解析响应Soap 
	 */
	public abstract void obtainDataInSoapObj(SoapObject soapObj);
	
}
