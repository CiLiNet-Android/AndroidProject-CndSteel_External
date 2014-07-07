package com.cndsteel.shipment;

import java.util.LinkedHashMap;

import org.ksoap2.serialization.SoapObject;

import android.test.AndroidTestCase;

import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceRequest;
import com.cndsteel.settings.beans.SettingsBean;

public class WebServiceTest extends AndroidTestCase{

	public void testListItemByShipCon() {
		String _serverUrl = "http://vip.cndsteel.com/services/ShipmentService";
		String _namespace = "http://impl.remote.webservice.steel.chinacnd.com";
		String _methodName = "listItemByShipCon";
		//注意加入的顺序
		LinkedHashMap<String, String> _requestParams = new LinkedHashMap<String, String>();
		_requestParams.put(QueryParams.QUERY_PARAM_SHIPMENT_SHIP_ID, "321555043");
		_requestParams.put(QueryParams.QUERY_PARAM_CON_ID, "316866041");
		_requestParams.put(QueryParams.QUERY_PARAM_PNAME_ID, "435");
		_requestParams.put(QueryParams.QUERY_PARAM_MATERIAL_ID, "3478");
		_requestParams.put(QueryParams.QUERY_PARAM_SPEC, "4.5000*1500*580000");
		_requestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, "1");
		_requestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, "4");
		_requestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_SESSIONID));
		
		
		SoapObject _soapObj = WebServiceRequest.send(_serverUrl, _namespace, _methodName, _requestParams);
		System.out.println(_soapObj);
	}

}
