package com.cndsteel.framework.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.cndsteel.R;
import com.cndsteel.framework.application.BaseApplication;
import com.cndsteel.framework.webService.WebServiceThread;

public abstract class BaseActivity extends Activity {
	
	private static final int TOAST_DURATION =  Toast.LENGTH_SHORT;
	
	private ProgressDialog mProgressDialog;

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
	
	protected void showProgressDialog(String dialogTitle,String dialogMessage){
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setTitle(dialogTitle);
		mProgressDialog.setMessage(dialogMessage);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(true);
		
		mProgressDialog.show();
	}
	
	protected void showLoadingProgressDialog(){
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCanceledOnTouchOutside(false);
		mProgressDialog.setCancelable(true);
		
		mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				WebServiceThread _webServiceThread = getWebServiceThread();
				if(null != _webServiceThread){
					_webServiceThread.cancel();
					_webServiceThread = null;
				}
				
				finish();
			}
		});
		
		mProgressDialog.show();
	}
	
	protected void dismissProgressDialog(){
		if(null != mProgressDialog){
			mProgressDialog.dismiss();
			mProgressDialog = null;
		}
	}
	
	protected void cancelProgressDialog(){
		if(null != mProgressDialog){
			mProgressDialog.cancel();
			mProgressDialog = null;
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
	
	/** 娣诲姞涓�釜鍚姩Activity鐨勬柟娉�**/
	protected void startActivity(Class<?> activityClass){
		Intent intent = new Intent(this,activityClass);
		super.startActivity(intent);
	}

	/**
	 * 鍔犺浇甯冨眬鏂囦欢
	 */
	protected View inflateView(int layoutResId){
		return LayoutInflater.from(getApplicationContext()).inflate(layoutResId, null);
	}
	
	/**
	 * 鏄剧ず鍦熷徃瀵硅瘽妗�
	 */
	protected void showToast(int resId){
		Toast.makeText(getApplicationContext(), resId,TOAST_DURATION).show();
	}
	
	protected void showToast(String msg){
		Toast.makeText(getApplicationContext(), msg, TOAST_DURATION).show();
	}
	
	/** 鍒涘缓涓�埇鐨刟lertDialog **/
	protected AlertDialog showConfirmDialog(int titleResId,int messageResId,DialogInterface.OnClickListener neutralButtonOnClickListener){
		AlertDialog.Builder _alertDialogBuilder = new AlertDialog.Builder(this);
		return _alertDialogBuilder.setTitle(getApplicationContext().getString(titleResId))
				   				  .setMessage(messageResId)
				                  .setNeutralButton(R.string.ButtonTextYes, neutralButtonOnClickListener)
				                  .setNegativeButton(R.string.ButtonTextNo, null)
				                  .show();
	}
	
	protected AlertDialog showConfirmDialog(int titleResId,String message,DialogInterface.OnClickListener neutralButtonOnClickListener){
		AlertDialog.Builder _alertDialogBuilder = new AlertDialog.Builder(this);
		return _alertDialogBuilder.setTitle(getApplicationContext().getString(titleResId))
				   				  .setMessage(message)
				                  .setNeutralButton(R.string.ButtonTextYes, neutralButtonOnClickListener)
				                  .setNegativeButton(R.string.ButtonTextNo, null)
				                  .show();
	}
	
	protected AlertDialog showAlertDialog(int titleResId,String message,DialogInterface.OnClickListener neutralButtonOnClickListener){
		AlertDialog.Builder _alertDialogBuilder = new AlertDialog.Builder(this);
		return _alertDialogBuilder.setTitle(getApplicationContext().getString(titleResId))
				   				  .setMessage(message)
				                  .setNeutralButton(R.string.ButtonTextOk, neutralButtonOnClickListener)
				                  .show();
	}
	
	protected abstract WebServiceThread getWebServiceThread(); 
}
