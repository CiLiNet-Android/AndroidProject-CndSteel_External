package com.cndsteel.main.activities;

import java.lang.ref.WeakReference;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.settings.beans.SettingsBean;

/**
 * @author zhxl
 */
public class LoadingActivity extends FrameActivity {
	
	private static final int LOADING_DELAY_MILLIS = 3 * 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appendFrameworkCenter(R.layout.activity_loading);
		hideFrameworkTop();
		
		loading();
	}
	
	private void loading() {
		new LoadingThread().start();
	}
	
	/**
	 * 访问网络，登陆校验
	 */
//	private void autoLogin(){
//		String _username = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_USERNAME);
//		String _password = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_PASSWORD);
//		
//		HashMap<String, String> _requestParams = new HashMap<String, String>();
//		_requestParams.put("username", _username);
//		_requestParams.put("password", _password);
//		
//		//mWebServiceThread = new WebServiceThread(new LoginBean(), _requestParams, _requestHandler, 0);
//	}

	/**
	 * 检查是否配置自动登录
	 */
	private boolean isSetAutoLogin(){
		return Constants.TRUE.equals(SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_AUTOLOGIN));
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
	
	private class LoadingThread extends Thread {
		@Override
		public void run() {
			Runnable _afterLoadingTask = null;
			
			boolean _firstLoading = SettingsBean.getInstance().getBooleanSettingValueByName(Constants.SETTINGS_PARAM_FIRSTLOADING, true);
			//如果是第一次登陆
			if(_firstLoading){
				_afterLoadingTask = new Runnable() {
					@Override
					public void run() {
						startActivity(WelcomeActivity.class);
						finish();
					}
				};
			}else {
				//是否配置了自动登录
				if(isSetAutoLogin()){
					String _sessionId = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_SESSIONID);
					if(TextUtils.isEmpty(_sessionId)){
						_afterLoadingTask = new Runnable() {
							@Override
							public void run() {
								startActivity(LoginActivity.class);
								finish();
							}
						};
					}else {
						_afterLoadingTask = new Runnable() {
							@Override
							public void run() {
								startActivity(MainActivity.class);
								finish();
							}
						};
					}
					
				}else{
					_afterLoadingTask = new Runnable() {
						@Override
						public void run() {
							startActivity(LoginActivity.class);
							finish();
						}
					};
				}
			}
			
			mTheHandler.postDelayed(_afterLoadingTask, LOADING_DELAY_MILLIS);
		}
		
	}

	
	/**
	 * ActivityHandler
	 * @author zhxl
	 *
	 */
	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<LoadingActivity> mWeakReference;
		
		public TheHandler(LoadingActivity activity){
			mWeakReference = new WeakReference<LoadingActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final LoadingActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int _msgWhat = msg.what;
				switch (_msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					
					break;
				}
				}
			}
		}
	}
	
	

}
