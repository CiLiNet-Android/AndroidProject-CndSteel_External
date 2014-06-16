package com.cndsteel.framework.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.cndsteel.R;
import com.cndsteel.framework.application.BaseApplication;

public class BaseActivity extends Activity {
	
	private static final int TOAST_DURATION =  Toast.LENGTH_SHORT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		BaseApplication _baseApplication = getBaseApplication();
		if(_baseApplication.isProgramExit){
			finish();
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		BaseApplication _baseApplication = getBaseApplication();
		if(_baseApplication.isProgramExit){
			finish();
		}
	}
	
	protected BaseApplication getBaseApplication() {
		return (BaseApplication)getApplication();
	}
	
	protected Context getContext(){
		return getApplicationContext();
	}
	
	/** 添加一个启动Activity的方法 **/
	protected void startActivity(Class<?> activityClass){
		Intent intent = new Intent(this,activityClass);
		super.startActivity(intent);
	}

	/**
	 * 加载布局文件
	 */
	protected View inflateView(int layoutResId){
		return LayoutInflater.from(getApplicationContext()).inflate(layoutResId, null);
	}
	
	/**
	 * 显示土司对话框
	 */
	protected void showToast(int resId){
		Toast.makeText(getApplicationContext(), resId,TOAST_DURATION).show();
	}
	
	protected void showToast(String msg){
		Toast.makeText(getApplicationContext(), msg, TOAST_DURATION).show();
	}
	
	/** 创建一般的alertDialog **/
	protected AlertDialog showAlertDialog(int titleResId,int messageResId,DialogInterface.OnClickListener neutralButtonOnClickListener){
		AlertDialog.Builder _alertDialogBuilder = new AlertDialog.Builder(this);
		return _alertDialogBuilder.setTitle(getApplicationContext().getString(titleResId))
				   				  .setMessage(messageResId)
				                  .setNeutralButton(R.string.ButtonTextYes, neutralButtonOnClickListener)
				                  .setNegativeButton(R.string.ButtonTextNo, null)
				                  .show();
	}
	
}
