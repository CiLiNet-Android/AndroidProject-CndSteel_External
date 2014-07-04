package com.cndsteel.sysinform.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.bean.LoadSysMsgCateBean;
import com.cndsteel.framework.bean.SysMsgCateBean;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.views.dialogs.wheelpicker.TextPickerDialog;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.sysinform.adapter.SysInformQueryResultListAdapter;
import com.cndsteel.sysinform.bean.SysInformBean;
import com.cndsteel.sysinform.bean.SysInformQueryResultListBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class SysInformQueryResultListActivity extends FrameActivity implements OnItemClickListener, OnClickListener, OnRefreshListener<ListView> {
	
	private static final int DEFAULT_PAGE_SIZE = 10;
	
	private static final int HANDLER_LOAD_MSG_CATE = 0x100;
	
	/*
	 *请求参数 
	 */
	private String mQueryParamMsgCate;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;

	private PullToRefreshListView listV_sysInformQueryResultList;
	
	private SysInformQueryResultListAdapter mSysInformQueryResultListAdapter;

	private ImageButton imgBtn_topRight;
	
	private SysInformQueryResultListBean sysInformQueryResultListBean;
	
	private WebServiceThread _webServiceThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTopBarTitle(R.string.appModule_sysInform);

		appendFrameworkCenter(R.layout.activity_sysinform_query);

		init();
		
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);

	}

	private void init() {

		initVariables();
		initViews();

	}

	private void initVariables() {
		
		mQueryParamMsgCate = "";
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = "20C5DA37D9CF5C8FDE3DD19E858D5614";
	}

	private void initViews() {
		
		imgBtn_topRight = (ImageButton) findViewById(R.id.imgBtn_topRight);
		imgBtn_topRight.setImageResource(R.drawable.list);
		imgBtn_topRight.setVisibility(View.VISIBLE);
		imgBtn_topRight.setOnClickListener(this);
		

		listV_sysInformQueryResultList = (PullToRefreshListView) findViewById(R.id.listV_sysInformQueryResultList);
		listV_sysInformQueryResultList.setMode(Mode.BOTH);
		listV_sysInformQueryResultList.setOnItemClickListener(this);
		listV_sysInformQueryResultList.setOnRefreshListener(this);
		
		mSysInformQueryResultListAdapter = new SysInformQueryResultListAdapter(getApplicationContext());
		mSysInformQueryResultListAdapter.initDatas(new ArrayList<SysInformBean>());
		listV_sysInformQueryResultList.setAdapter(mSysInformQueryResultListAdapter);
		


	}
	
	private MyHandler myHandler = new MyHandler(this);
	
	private void loadDatas(int msgWhat) {
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();		
		
		
		if(msgWhat == HANDLER_LOAD_MSG_CATE){
			
			_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
			_webServiceThread = new WebServiceThread(new LoadSysMsgCateBean(), _webServiceRequestParams, myHandler, msgWhat);
			
		}else {
			
			if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
				showLoadingProgressDialog();
			}		
			
			_webServiceRequestParams.put(QueryParams.QUETY_PARAM_SYS_INFORM_MSG_CATE, mQueryParamMsgCate);
			_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
			_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
			_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
			_webServiceThread = new WebServiceThread(new SysInformQueryResultListBean(), _webServiceRequestParams, myHandler, msgWhat);
		}
		
		_webServiceThread.start();
	
	}

	//  Handler
	private static class MyHandler extends Handler {
		
		private WeakReference<SysInformQueryResultListActivity> _mWeakReference;
		
		public MyHandler(SysInformQueryResultListActivity activity) {
			
			_mWeakReference = new WeakReference<SysInformQueryResultListActivity>(activity);
			
		}

		@Override
		public void handleMessage(Message msg) {
			final SysInformQueryResultListActivity _mActivity = (SysInformQueryResultListActivity) _mWeakReference.get();
			
			if(null != _mActivity){
				
				int _msgWhat = msg.what;
				
				switch (_msgWhat) {
				
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					
					_mActivity.dismissProgressDialog();
					
					_mActivity.sysInformQueryResultListBean = (SysInformQueryResultListBean)msg.obj;
					
					if(_mActivity.sysInformQueryResultListBean.code > 0){
						
						_mActivity.mSysInformQueryResultListAdapter.refreshDatas(_mActivity.sysInformQueryResultListBean.mSysInformBeanList);
						
					}else {
						
						_mActivity.showAlertDialog(R.string.appModule_sysInform, _mActivity.getString(R.string.DialogMsgNoDatas, _mActivity.getString(R.string.appModule_sysInform)), new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {}
						});
					}
					
					break;
				}
				
				case HANDLER_LOAD_MSG_CATE:{
					
					LoadSysMsgCateBean _loadSysMsgCateBean = (LoadSysMsgCateBean) msg.obj;
					
					final ArrayList<SysMsgCateBean> _sysMsgCateBeansList = new ArrayList<SysMsgCateBean>();
					
					if(_loadSysMsgCateBean.sysMsgCeteCode != null ){
						_sysMsgCateBeansList.addAll(_sysMsgCateBeansList.size(), _loadSysMsgCateBean.mSysMsgCateBeansList);
					}
					
					ArrayList<String> _sysMsgCateNamesList = new ArrayList<String>(_sysMsgCateBeansList.size());
					for(SysMsgCateBean _sysMsgCateBean : _sysMsgCateBeansList){
						_sysMsgCateNamesList.add(_sysMsgCateBean.name);
					}
					
					
					TextPickerDialog.Builder _builder = new TextPickerDialog.Builder(_mActivity);
					
					_builder.setTitle(R.string.SysInformCategroy)
					
							.setPickableTexts(_sysMsgCateNamesList)
							
							.setOnTextPickedListener(new TextPickerDialog.OnTextPickedListener() {
								@Override
								public void onTextPicked(TextPickerDialog textPickerDialog, int position) {
									
									SysMsgCateBean _sysMsgCateBean = _sysMsgCateBeansList.get(position);
									
									_mActivity.mQueryParamMsgCate = _sysMsgCateBean.code;
									
									_mActivity.mQueryParamPageIndex = 1;
									
									_mActivity.loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
									
									textPickerDialog.dismiss();
								}
							}).create().show();
					
					break;
				}	
				case Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS:{
					
					_mActivity.listV_sysInformQueryResultList.onRefreshComplete();
					
					SysInformQueryResultListBean _sysInformQueryResultListBean= (SysInformQueryResultListBean) msg.obj;
					if(_sysInformQueryResultListBean.code > 0 ){
						_mActivity.mSysInformQueryResultListAdapter.refreshDatas(_sysInformQueryResultListBean.mSysInformBeanList);
					}
					
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS:{
					
					_mActivity.listV_sysInformQueryResultList.onRefreshComplete();
					
					SysInformQueryResultListBean _sysInformQueryResultListBean= (SysInformQueryResultListBean) msg.obj;
					if(_sysInformQueryResultListBean.code > 0 ){
						_mActivity.mSysInformQueryResultListAdapter.appendDatas(_sysInformQueryResultListBean.mSysInformBeanList);
					}
					
					break;
				}
				
				default:{
					break;
				}
				}
			}
		}
		
	}
	
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		
		Intent _intent = new Intent(this, SysInformResultDetailActivity.class);
		
		SysInformBean _sysInformBean = (SysInformBean)parent.getAdapter().getItem(position);
		
		_intent.putExtra("sysInformBean", _sysInformBean);
		
		startActivity(_intent);
		
	}

	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		
		case R.id.imgBtn_topRight:
			
			loadDatas(HANDLER_LOAD_MSG_CATE);
			
			break;

		default:
			break;
		}
		
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		
		//下拉更新
		if(listV_sysInformQueryResultList.isHeaderShown()){
			mQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
		}
		
		//上拉更新
		else if(listV_sysInformQueryResultList.isFooterShown()){
			mQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
		}
		
	}

}
