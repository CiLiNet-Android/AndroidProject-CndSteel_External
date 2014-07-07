package com.cndsteel.contract.activities;

import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.contract.adapters.ContractQueryResultListAdapter;
import com.cndsteel.contract.beans.ContractBean;
import com.cndsteel.contract.beans.ContractQueryResultListBean;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.settings.beans.SettingsBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ContractQueryResultListActivity extends FrameActivity implements OnItemClickListener,OnRefreshListener<ListView>{
	
	private PullToRefreshListView listV_ContractList;
	private ContractQueryResultListAdapter mContractQueryResultListAdapter;
	
	/** 查询条件 **/
	private String mQueryParamConYear;
	private String mQueryParamConMonth;
	private String mQueryParamConCode;
	private String mQueryParamStatus;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appendFrameworkCenter(R.layout.activity_contract_query_result_list);
		
		init();
	}

	private void init() {
		initVariables();
		initViews();
		
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
	}
	
	private void loadDatas(int msgWhat){
		showLoadingProgressDialog();
		
		LinkedHashMap<String, String> _webServiceRequestParams = createWebServiceRequestParams();
		
		mWebServiceThread = new WebServiceThread(new ContractQueryResultListBean(), _webServiceRequestParams, mHandler, msgWhat);
		mWebServiceThread.start();
	}

	private void initVariables() {
		Bundle _queryParams = getIntent().getExtras();
		
		mQueryParamConYear = _queryParams.getString(QueryParams.QUERY_PARAM_CON_YEAR);
		mQueryParamConMonth = _queryParams.getString(QueryParams.QUERY_PARAM_CON_MONTH);
		mQueryParamConCode = _queryParams.getString(QueryParams.QUERY_PARAM_CON_CODE);
		mQueryParamStatus = _queryParams.getString(QueryParams.QUERY_PARAM_STATUS);
		
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = SettingsBean.getInstance().getStringSettingValueByName(Constants.SETTINGS_PARAM_SESSIONID);
	}

	private void initViews() {
		setTopBarTitle(R.string.appModule_contract);
		
		listV_ContractList = (PullToRefreshListView)findViewById(R.id.listV_ContractList);
		listV_ContractList.setMode(Mode.BOTH);
		
		listV_ContractList.setOnRefreshListener(this);
		listV_ContractList.setOnItemClickListener(this);
		
		mContractQueryResultListAdapter = new ContractQueryResultListAdapter(getApplicationContext());
		mContractQueryResultListAdapter.initDatas(new LinkedList<ContractBean>());
		
		listV_ContractList.setAdapter(mContractQueryResultListAdapter);
	}

	/** ListView的item的事件处理 **/
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent _intent = new Intent(this,ContractQueryResultDetailActivity.class);
		
		ContractBean _contractBean = (ContractBean)parent.getAdapter().getItem(position);
		_intent.putExtra(QueryParams.QUERY_PARAM_ID, _contractBean.id);
		
		//启动合同款项
		startActivity(_intent);
	}
	
	
	private LinkedHashMap<String,String> createWebServiceRequestParams(){
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_CON_YEAR, mQueryParamConYear);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_CON_MONTH,mQueryParamConMonth);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_CON_CODE, mQueryParamConCode);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_STATUS, mQueryParamStatus);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		return _webServiceRequestParams;
	}
	
	
	/**
	 * ActivityHandler
	 * @author zhxl
	 *
	 */
	private ContractQueryResultListActivityHandler mHandler = new ContractQueryResultListActivityHandler(this);
	
	private static class ContractQueryResultListActivityHandler extends Handler {
		private WeakReference<ContractQueryResultListActivity> mWeakReference;
		
		public ContractQueryResultListActivityHandler(ContractQueryResultListActivity activity){
			mWeakReference = new WeakReference<ContractQueryResultListActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final ContractQueryResultListActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int _msgWhat = msg.what;
				switch (_msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					ContractQueryResultListBean _contractQueryResultListBean = (ContractQueryResultListBean)msg.obj;
					if(_contractQueryResultListBean.code > 0){
						_theActivity.mContractQueryResultListAdapter.refreshDatas(_contractQueryResultListBean.contractBeans);
					}else {
						_theActivity.showAlertDialog(R.string.appModule_contract, _theActivity.getString(R.string.DialogMsgNoDatas, _theActivity.getString(R.string.appModule_contract)), new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								if(which == DialogInterface.BUTTON_NEUTRAL){
									_theActivity.finish();
								}
							}
						});
					}
					
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					ContractQueryResultListBean _contractQueryResultListBean = (ContractQueryResultListBean)msg.obj;
					if(_contractQueryResultListBean.code > 0){
						_theActivity.mContractQueryResultListAdapter.refreshDatas(_contractQueryResultListBean.contractBeans);
					}
					
					_theActivity.listV_ContractList.onRefreshComplete();
					
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					ContractQueryResultListBean _contractQueryResultListBean = (ContractQueryResultListBean)msg.obj;
					if(_contractQueryResultListBean.code > 0){
						_theActivity.mContractQueryResultListAdapter.appendDatas(_contractQueryResultListBean.contractBeans);
					}
					
					_theActivity.listV_ContractList.onRefreshComplete();
					
					break;
				}
				default:
					break;
				}
			}
		}
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		//下拉更新
		if(listV_ContractList.isHeaderShown()){
			mQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
		}
		//上拉更新
		else if(listV_ContractList.isFooterShown()){
			mQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
		}
	}
}
