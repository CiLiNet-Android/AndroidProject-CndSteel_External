package com.cndsteel.info.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.bean.LoadMicroMessageCateBean;
import com.cndsteel.framework.bean.MicroMessageCateBean;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.views.dialogs.wheelpicker.TextPickerDialog;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.info.adapter.InfoQueryResultListAdapter;
import com.cndsteel.info.bean.InfoBean;
import com.cndsteel.info.bean.InfoQueryResultListBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class InfoQueryResultListActivity extends FrameActivity implements OnItemClickListener,View.OnClickListener,OnRefreshListener<ListView>{
	
	private static final int DEFAULT_PAGE_SIZE = 10;
	
	private static final int HANDLER_MSG_LOAD_MSG_CATE = 0x100;
	
	private String mQueryParamMsgCate;
	private int mQueryParamPageIndex;
	private int mQueryParamPageSize;
	private String mQueryParamSessionId;
	
	private PullToRefreshListView listV_infoQueryResultList;
	private InfoQueryResultListAdapter mInfoQueryResultListAdapter;
	
	private ImageButton imgBtn_topRight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTopBarTitle(R.string.appModule_info);
		appendFrameworkCenter(R.layout.activity_info_query);
		
		init();
	}

	private void init() {
		initVariables();
		initViews();
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
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
		imgBtn_topRight.setOnClickListener(this);
		imgBtn_topRight.setVisibility(View.VISIBLE);
		
		listV_infoQueryResultList = (PullToRefreshListView)findViewById(R.id.listV_infoQueryResultList);
		listV_infoQueryResultList.setMode(Mode.BOTH);
		listV_infoQueryResultList.setOnRefreshListener(this);
		listV_infoQueryResultList.getRefreshableView().setOnItemClickListener(this);
		
		mInfoQueryResultListAdapter = new InfoQueryResultListAdapter(getApplicationContext());
		mInfoQueryResultListAdapter.initDatas(new ArrayList<InfoBean>());
		listV_infoQueryResultList.setAdapter(mInfoQueryResultListAdapter);
	}
	
	private void loadDatas(int msgWhat){
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		
		if(msgWhat == HANDLER_MSG_LOAD_MSG_CATE){
			_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
			mWebServiceThread = new WebServiceThread(new LoadMicroMessageCateBean(), _webServiceRequestParams, mTheHandler, msgWhat);
		}else {
			if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
				showLoadingProgressDialog();
			}
			
			_webServiceRequestParams.put(QueryParams.QUERY_PARAM_INFO_MSG_CATE, mQueryParamMsgCate);
			_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
			_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
			_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
			
			mWebServiceThread = new WebServiceThread(new InfoQueryResultListBean(), _webServiceRequestParams, mTheHandler, msgWhat);
		}
				
		mWebServiceThread.start();
	}

	private TheHandler mTheHandler = new TheHandler(this);
	
	private static class TheHandler extends Handler {
		private WeakReference<InfoQueryResultListActivity> mWeakReference;
		
		public TheHandler(InfoQueryResultListActivity activity){
			mWeakReference = new WeakReference<InfoQueryResultListActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			final InfoQueryResultListActivity _theActivity = mWeakReference.get();
			if(null != _theActivity){
				int _msgWhat = msg.what;
				switch (_msgWhat) {
				case Constants.HANDLER_MSG_DATA_LOAD_SUCCESS: {
					_theActivity.dismissProgressDialog();
					
					InfoQueryResultListBean _infoQueryResultListBean = (InfoQueryResultListBean)msg.obj;
					if(_infoQueryResultListBean.code > 0){
						_theActivity.mInfoQueryResultListAdapter.refreshDatas(_infoQueryResultListBean.infoBeans);
					}else {
						_theActivity.showAlertDialog(R.string.appModule_info, _theActivity.getString(R.string.DialogMsgNoDatas, _theActivity.getString(R.string.appModule_info)), new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {}
						});
					}
					
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_infoQueryResultList.onRefreshComplete();
					
					InfoQueryResultListBean _infoQueryResultListBean = (InfoQueryResultListBean)msg.obj;
					if(_infoQueryResultListBean.code > 0){
						_theActivity.mInfoQueryResultListAdapter.refreshDatas(_infoQueryResultListBean.infoBeans);
					}
					break;
				}
				case Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS: {
					_theActivity.listV_infoQueryResultList.onRefreshComplete();
					
					InfoQueryResultListBean _infoQueryResultListBean = (InfoQueryResultListBean)msg.obj;
					if(_infoQueryResultListBean.code > 0){
						_theActivity.mInfoQueryResultListAdapter.appendDatas(_infoQueryResultListBean.infoBeans);
					}
					break;
				}
				case HANDLER_MSG_LOAD_MSG_CATE: {
					LoadMicroMessageCateBean _loadMicroMessageCateBean = (LoadMicroMessageCateBean)msg.obj;
					
					final ArrayList<MicroMessageCateBean> _microMessageCateBeans = new ArrayList<MicroMessageCateBean>();
					
					if(_loadMicroMessageCateBean.code > 0){
						_microMessageCateBeans.addAll(_microMessageCateBeans.size(), _loadMicroMessageCateBean.microMessageCateBeans);
					}
					
					ArrayList<String> _microMessageCateNames = new ArrayList<String>(_microMessageCateBeans.size());
					for(MicroMessageCateBean _microMessageCateBean : _microMessageCateBeans){
						_microMessageCateNames.add(_microMessageCateBean.name);
					}
					
					TextPickerDialog.Builder _builder = new TextPickerDialog.Builder(_theActivity);
					_builder.setTitle(R.string.InfoCategroy)
							.setPickableTexts(_microMessageCateNames)
							.setOnTextPickedListener(new TextPickerDialog.OnTextPickedListener() {
								@Override
								public void onTextPicked(TextPickerDialog textPickerDialog, int position) {
									MicroMessageCateBean _microMessageCateBean = _microMessageCateBeans.get(position);
									
									_theActivity.mQueryParamMsgCate = _microMessageCateBean.code;
									_theActivity.mQueryParamPageIndex = 1;
									
									_theActivity.loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);
									
									textPickerDialog.dismiss();
								}
							}).create().show();
					break;
				}
				default:
					break;
				}
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		InfoBean _infoBean = (InfoBean)parent.getAdapter().getItem(position);
		
		Intent _intent = new Intent(this,InfoQueryResultDetailActivity.class);
		
		_intent.putExtra("infoBean", _infoBean);
		
		startActivity(_intent);
	}
	
	@Override
	public void onClick(View view) {
		int _viewId = view.getId();
		switch (_viewId) {
		//类别对话框
		case R.id.imgBtn_topRight: {
			loadDatas(HANDLER_MSG_LOAD_MSG_CATE);
			break;
		}

		default:
			break;
		}
		
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		//下拉更新
		if(listV_infoQueryResultList.isHeaderShown()){
			mQueryParamPageIndex = 1;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_START_TO_REFRESH_DATA_SUCCESS);
		}
		//上拉更新
		else if(listV_infoQueryResultList.isFooterShown()){
			mQueryParamPageIndex ++;
			loadDatas(Constants.HANDLER_MSG_PULL_FROM_END_TO_REFRESH_DATA_SUCCESS);
		}
	}

}

