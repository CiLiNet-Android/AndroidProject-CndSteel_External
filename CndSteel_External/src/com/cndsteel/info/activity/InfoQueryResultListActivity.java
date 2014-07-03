package com.cndsteel.info.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.info.adapter.InfoQueryResultListAdapter;
import com.cndsteel.info.bean.InfoBean;
import com.cndsteel.info.bean.InfoQueryResultListBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class InfoQueryResultListActivity extends FrameActivity implements OnItemClickListener,View.OnClickListener {
	
	private static final int DEFAULT_PAGE_SIZE = 10;
	
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
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = "20C5DA37D9CF5C8FDE3DD19E858D5614";
	}

	private void initViews() {
		imgBtn_topRight = (ImageButton) findViewById(R.id.imgBtn_topRight);
		imgBtn_topRight.setImageResource(R.drawable.list);
		imgBtn_topRight.setOnClickListener(this);
		imgBtn_topRight.setVisibility(View.VISIBLE);
		
		listV_infoQueryResultList = (PullToRefreshListView)findViewById(R.id.listV_infoQueryResultList);
		listV_infoQueryResultList.setMode(Mode.BOTH);
		
		mInfoQueryResultListAdapter = new InfoQueryResultListAdapter(getApplicationContext());
		mInfoQueryResultListAdapter.initDatas(new ArrayList<InfoBean>());
		listV_infoQueryResultList.setAdapter(mInfoQueryResultListAdapter);
	}
	
	private void loadDatas(int msgWhat){
		if(msgWhat == Constants.HANDLER_MSG_DATA_LOAD_SUCCESS){
			showLoadingProgressDialog();
		}
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_INFO_MSG_CATE, mQueryParamMsgCate);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(DEFAULT_PAGE_SIZE));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		mWebServiceThread = new WebServiceThread(new InfoQueryResultListBean(), _webServiceRequestParams, mTheHandler, msgWhat);
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
							public void onClick(DialogInterface dialog, int which) {
								if(which == DialogInterface.BUTTON_NEUTRAL){
									_theActivity.finish();
								}
							}
						});
					}
					
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
		startActivity(InfoDetailActivity.class);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
