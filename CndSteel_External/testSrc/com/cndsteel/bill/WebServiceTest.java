package com.cndsteel.bill;

import java.util.LinkedHashMap;

import org.ksoap2.serialization.SoapObject;

import android.test.AndroidTestCase;

import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.webService.WebServiceRequest;
import com.cndsteel.settings.beans.SettingsBean;

public class WebServiceTest extends AndroidTestCase{

	public void testGetConpaperService() {
		String _serverUrl = "http://vip.cndsteel.com/services/BillPaperService";
		String _namespace = "http://impl.remote.webservice.steel.chinacnd.com";
		String _methodName = "getConpaper";
		//注意加入的顺序
		LinkedHashMap<String, String> _requestParams = new LinkedHashMap<String, String>();
		_requestParams.put("conId", "53337181");
		_requestParams.put("sessionId", SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_SESSIONID));
		
		
		SoapObject _soapObj = WebServiceRequest.send(_serverUrl, _namespace, _methodName, _requestParams);
		System.out.println(_soapObj);
	}

}
