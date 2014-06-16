package com.cndsteel.main.activity;

import java.util.HashMap;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.handler.AbsActivityHandler;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.main.bean.LoginBean;
import com.cndsteel.settings.bean.SettingsBean;

/**
 * @author zhxl
 */
public class LoadingActivity extends FrameActivity {
	
	private WebServiceThread mWebServiceThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_loading);
		hideFrameworkTop();
		
		init();
	}
	
	private void init() {
		if(isAutoLogin()){
			String _sessionId = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_SESSIONID);
			if(!TextUtils.isEmpty(_sessionId)){
				startActivity(MainActivity.class);
			}else {
				String _username = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_USERNAME);
				//如果sessionId不存在，并且已经有用户名，则登录校验
				if(!TextUtils.isEmpty(_username)){
					autoLogin();
				}else {
					startActivity(LoginActivity.class);
				}
			}
		}else {
			startActivity(LoginActivity.class);
		}
	}
	
	/**
	 * 访问网络，登陆校验
	 */
	private void autoLogin(){
		String _username = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_USERNAME);
		String _password = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_PASSWORD);
		
		HashMap<String, String> _requestParams = new HashMap<String, String>();
		_requestParams.put("username", _username);
		_requestParams.put("password", _password);
		
		mWebServiceThread = new WebServiceThread(new LoginBean(), _requestParams, _requestHandler, 0);
	}

	/**
	 * 检查是否配置自动登录
	 */
	private boolean isAutoLogin(){
		return Constants.TRUE.equals(SettingsBean.getInstance().getBooleanSettingValueByName(Constants.SETTINGS_PARAM_AUTOLOGIN, false));
	}

	/**
	 * 监听返回键，停止线程
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			
		}
		return super.onKeyUp(keyCode, event);
	}

	
	/****
	 * 专门的ActivityHandler
	 */
	private AbsActivityHandler<LoadingActivity> _requestHandler = new AbsActivityHandler<LoadingActivity>(this){
		@Override
		protected void handleMessage(LoadingActivity theActivity, Message msg) {
			
		}
		
	};
	
	

}
