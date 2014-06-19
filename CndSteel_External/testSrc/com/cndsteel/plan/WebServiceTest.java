package com.cndsteel.plan;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.ksoap2.serialization.SoapObject;

import android.test.AndroidTestCase;

import com.cndsteel.framework.log.GlobalLog;
import com.cndsteel.framework.webService.WebServiceRequest;

public class WebServiceTest extends AndroidTestCase {

	public void testPlanQueryResultList(){
		
		//参数的设置顺序
		LinkedHashMap<String, String> _requestParams = new LinkedHashMap<String, String>();
		_requestParams.put("year", "2012");
		_requestParams.put("month","05");
		_requestParams.put("status", "DONE");
		_requestParams.put("pageindex", "1");
		_requestParams.put("pagesize", "5");
		_requestParams.put("sessionId", "20C5DA37D9CF5C8FDE3DD19E858D5614");
		
		String _serverUrl = "http://vip.cndsteel.com/services/BookingService";
		String _namespace = "http://impl.remote.webservice.steel.chinacnd.com";
		String _methodName = "listBooking";
		
		SoapObject _soapObj = WebServiceRequest.send(_serverUrl, _namespace, _methodName, _requestParams);
		GlobalLog.i(_soapObj.toString());
	}
}
