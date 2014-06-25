package com.cndsteel.settings.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.handler.AbsActivityHandler;
import com.cndsteel.framework.views.toggle.Toggle;
import com.cndsteel.main.activity.LoginActivity;
import com.cndsteel.settings.bean.SettingsBean;

/**
 * 配置页面
 * @author zhxl
 *
 */
public class SettingsActivity extends FrameActivity implements Toggle.OnToggleStatusChangeListener,View.OnClickListener ,DialogInterface.OnClickListener {
	
	private Toggle toggle_settingsAutoLogin;
	private Button btn_logout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendFrameworkCenter(R.layout.activity_settings);
		
		initViews();
	}

	private void initViews(){
		setTopBarTitle(R.string.topBarTitle_settings);
		
		
		toggle_settingsAutoLogin = (Toggle)findViewById(R.id.toggle_settingsAutoLogin);
		
		boolean _autoLogin = SettingsBean.getInstance().getBooleanSettingValueByName(Constants.SETTINGS_PARAM_AUTOLOGIN, false);
		toggle_settingsAutoLogin.init(_autoLogin,R.drawable.toggle_slider, R.drawable.toggle_on_bg, R.drawable.toggle_off_bg);
		
		toggle_settingsAutoLogin.setOnToggleStatusChangeListener(this);
		
		
		btn_logout = (Button)findViewById(R.id.btn_logout);
		btn_logout.setOnClickListener(this);
	}

	@Override
	public void onToggleStatusChanged(boolean toggleStatus) {
		if(toggleStatus == true){
			mActivityHandler.sendEmptyMessage(HANDLE_MSG_TOGGLE_ON);
		}else {
			mActivityHandler.sendEmptyMessage(HANDLE_MSG_TOGGLE_OFF);
		}
	}
	
	public static final int HANDLE_MSG_TOGGLE_ON = 0xfff;
	public static final int HANDLE_MSG_TOGGLE_OFF = 0x000;
	public static final int HANDLE_MSG_LOG_OUT = 0X001;
	
	private AbsActivityHandler<SettingsActivity> mActivityHandler = new AbsActivityHandler<SettingsActivity>(this) {
		@Override
		protected void handleMessage(SettingsActivity theActivity, Message msg) {
			int _msgWhat = msg.what;
			switch(_msgWhat){
			case HANDLE_MSG_TOGGLE_ON: 
				SettingsBean.getInstance().putSettingValue(Constants.SETTINGS_PARAM_AUTOLOGIN, Constants.TRUE);
				showToast(R.string.settings_autoLogin_on);
				break;
			case HANDLE_MSG_TOGGLE_OFF:
				SettingsBean.getInstance().putSettingValue(Constants.SETTINGS_PARAM_AUTOLOGIN, Constants.FALSE);
				showToast(R.string.settings_autoLogin_off);
				break;
			case HANDLE_MSG_LOG_OUT:
				SettingsBean.getInstance().deleteSettingValue(Constants.SETTINGS_PARAM_SESSIONID);
				startActivity(LoginActivity.class);
				break;
			default: 
				break;
			}
			
			SettingsBean.save();
		}
		
	};

	@Override
	public void onClick(View view) {
		showConfirmDialog(R.string.DialogTitleLogout, R.string.DialogMessageLogout, this);
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_NEUTRAL){
			mActivityHandler.sendEmptyMessage(HANDLE_MSG_LOG_OUT);
		}
	}
}
