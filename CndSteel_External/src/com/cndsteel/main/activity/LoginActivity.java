package com.cndsteel.main.activity;

import java.util.LinkedHashMap;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.ErrorMsg;
import com.cndsteel.framework.handler.AbsActivityHandler;
import com.cndsteel.framework.views.dialogs.loadingprogress.LoadingProgressDialog;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.main.bean.LoginBean;
import com.cndsteel.settings.bean.SettingsBean;

/**
 * 用户登录界面
 * @author zhxl
 */
public class LoginActivity extends FrameActivity implements View.OnClickListener,DialogInterface.OnCancelListener {
	
	/** 用户名 **/
	private EditText edTxt_loginUsername;
	/** 密码 **/
	private EditText edTxt_loginPassword;
	
	/** 服务器访问线程 **/
	private WebServiceThread mWebServiceThread;
	
	/** 加载进度条 **/
	private LoadingProgressDialog mLoadingProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_login);
		hideFrameworkTop();
		
		init();
	}

	private void init() {
		initViews();
		
		readCacheValue();
	}

	private void initViews() {
		edTxt_loginUsername = (EditText)findViewById(R.id.edTxt_loginUsername);
		
		edTxt_loginPassword = (EditText)findViewById(R.id.edTxt_loginPassword);
		
		findViewById(R.id.btn_login).setOnClickListener(this);
		
		mLoadingProgressDialog = new LoadingProgressDialog(this);
		mLoadingProgressDialog.setOnCancelListener(this);
	}
	
	private void readCacheValue() {
		boolean _autoLogin  = SettingsBean.getInstance().getBooleanSettingValueByName(Constants.SETTINGS_PARAM_AUTOLOGIN, false);
		if(_autoLogin){
			String _username = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_USERNAME);
			edTxt_loginUsername.setText(_username);
			
			String _password = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_PASSWORD);
			edTxt_loginPassword.setText(_password);
		}
	}
	
	private AbsActivityHandler<LoginActivity> mActivityHandler = new AbsActivityHandler<LoginActivity>(this) {
		@Override
		protected void handleMessage(LoginActivity theActivity, Message msg) {
			mLoadingProgressDialog.dismiss();
			
			int _msgWhat = msg.what;
			switch(_msgWhat){
			//获取数据，并解析成功
			case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS:
				LoginBean _loginBean = (LoginBean)msg.obj;
				if(true == _loginBean.state){
					onLoginSuccess(_loginBean);
				}else {
					showToast(_loginBean.errorMsg);
				}
				break;
				
			default:
				break;
			}
		}
	};
	
	/**
	 * 登陆校验成功后的处理
	 * @param _loginBean
	 */
	private void onLoginSuccess(LoginBean _loginBean) {
		showToast(getString(R.string.login_successed_msg, _loginBean.fullname));
		
		//缓存参数
		SettingsBean.getInstance().putSettingValue(Constants.SETTINGS_PARAM_USERNAME,_loginBean.username);
		SettingsBean.getInstance().putSettingValue(Constants.SETTINGS_PARAM_PASSWORD, edTxt_loginPassword.getText().toString());
		SettingsBean.getInstance().putSettingValue(Constants.SETTINGS_PARAM_SESSIONID, _loginBean.sessionId);
		//将数据写入文件
		SettingsBean.save();
		
		//启动Activity
		startActivity(MainActivity.class);
		
		finish();
	}

	@Override
	public void onClick(View view) {
		if(view.getId() == R.id.btn_login){
			String _username = edTxt_loginUsername.getText().toString().trim();
			if(TextUtils.isEmpty(_username)){
				showToast(R.string.login_please_input_username);
				return;
			}
			
			String _password = edTxt_loginPassword.getText().toString();
			if(TextUtils.isEmpty(_password)){
				showToast(R.string.login_please_input_password);
				return;
			}
			
			LinkedHashMap<String, String> _requestParams = new LinkedHashMap<String, String>();
			_requestParams.put("username", _username);
			_requestParams.put("password", _password);
			_requestParams.put("deviceToken", "deviceToken");
			
			mLoadingProgressDialog.show();
			
			mWebServiceThread = new WebServiceThread(new LoginBean(), _requestParams, mActivityHandler, Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
			mWebServiceThread.start();
		}
	}
	
	@Override
	public void onCancel(DialogInterface dialog) {
		if(null != mWebServiceThread){
			mWebServiceThread.cancel();
			mWebServiceThread = null;
		}
	}
	
}
