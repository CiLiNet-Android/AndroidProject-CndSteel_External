package com.cndsteel.main.activities;

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
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.handler.AbsActivityHandler;
import com.cndsteel.framework.views.dialogs.loadingprogress.LoadingProgressDialog;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.main.beans.LoginBean;
import com.cndsteel.settings.beans.SettingsBean;

/**
 * 用户登录界面
 * @author zhxl
 */
public class LoginActivity extends FrameActivity implements View.OnClickListener,DialogInterface.OnCancelListener {
	
	private String mQueryParamUserName;
	private String mQueryParamPassword;
	
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
		initVariables();
		initViews();
	}

	private void initVariables() {
		mQueryParamUserName = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_USERNAME);
		mQueryParamPassword = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_PASSWORD);
	}

	private void initViews() {
		edTxt_loginUsername = (EditText)findViewById(R.id.edTxt_loginUsername);
		edTxt_loginUsername.setText(mQueryParamUserName);
		
		edTxt_loginPassword = (EditText)findViewById(R.id.edTxt_loginPassword);
		edTxt_loginPassword.setText(mQueryParamPassword);
		
		findViewById(R.id.btn_login).setOnClickListener(this);
		
		mLoadingProgressDialog = new LoadingProgressDialog(this);
		mLoadingProgressDialog.setOnCancelListener(this);
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
		//缓存参数
		SettingsBean.getInstance().putSettingValue(Constants.SETTINGS_PARAM_USERNAME,mQueryParamUserName);
		SettingsBean.getInstance().putSettingValue(Constants.SETTINGS_PARAM_PASSWORD, mQueryParamPassword);
		SettingsBean.getInstance().putSettingValue(Constants.SETTINGS_PARAM_SESSIONID, _loginBean.sessionId);
		//将数据写入文件
		SettingsBean.save();
		
		//启动Activity
		startActivity(MainActivity.class);
		
		//finish();
	}

	@Override
	public void onClick(View view) {
		if(view.getId() == R.id.btn_login){
			mQueryParamUserName = edTxt_loginUsername.getText().toString().trim();
			if(TextUtils.isEmpty(mQueryParamUserName)){
				showToast(R.string.login_please_input_username);
				return;
			}
			
			mQueryParamPassword = edTxt_loginPassword.getText().toString();
			if(TextUtils.isEmpty(mQueryParamPassword)){
				showToast(R.string.login_please_input_password);
				return;
			}
			
			LinkedHashMap<String, String> _requestParams = new LinkedHashMap<String, String>();
			_requestParams.put(QueryParams.QUERY_PARAM_USERNAME, mQueryParamUserName);
			_requestParams.put(QueryParams.QUERY_PARAM_PASSWORD, mQueryParamPassword);
			_requestParams.put(QueryParams.QUERY_PARAM_DEVICE_TOKEN, "deviceToken");
			
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
