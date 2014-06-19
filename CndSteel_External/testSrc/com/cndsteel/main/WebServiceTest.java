package com.cndsteel.main;

import java.util.LinkedHashMap;

import org.ksoap2.serialization.SoapObject;

import android.test.AndroidTestCase;

import com.cndsteel.framework.webService.WebServiceRequest;

public class WebServiceTest extends AndroidTestCase{

	public void testLoginService() {
		String _serverUrl = "http://vip.cndsteel.com/services/LoginService";
		String _namespace = "http://impl.remote.webservice.steel.chinacnd.com";
		String _methodName = "login";
		//注意加入的顺序
		LinkedHashMap<String, String> _requestParams = new LinkedHashMap<String, String>();
		_requestParams.put("username", "zhxl");
		_requestParams.put("password", "zhxl");
		_requestParams.put("deviceToken", "deviceToken");
		
		
		SoapObject _soapObj = WebServiceRequest.send(_serverUrl, _namespace, _methodName, _requestParams);
		System.out.println(_soapObj);
	}

}
