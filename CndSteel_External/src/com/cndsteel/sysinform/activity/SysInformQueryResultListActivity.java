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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;

import com.cndsteel.R;
import com.cndsteel.framework.activity.FrameActivity;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.constant.QueryParams;
import com.cndsteel.framework.webService.WebServiceThread;
import com.cndsteel.info.bean.InfoQueryResultListBean;
import com.cndsteel.sysinform.adapter.SysInformQueryResultListAdapter;
import com.cndsteel.sysinform.bean.SysInformBean;
import com.cndsteel.sysinform.bean.SysInformQueryResultListBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class SysInformQueryResultListActivity extends FrameActivity implements OnItemClickListener {
	
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTopBarTitle(R.string.appModule_info);

		appendFrameworkCenter(R.layout.activity_sysinform_query);

		init();
		
		loadDatas(Constants.HANDLER_MSG_DATA_LOAD_SUCCESS);

	}

	private void loadDatas(int msgWhat) {
		
		showLoadingProgressDialog();
		
		LinkedHashMap<String, String> _webServiceRequestParams = new LinkedHashMap<String, String>();
		_webServiceRequestParams.put(QueryParams.QUETY_PARAM_SYS_INFORM_MSG_CATE, mQueryParamMsgCate);
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_INDEX, String.valueOf(mQueryParamPageIndex));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_PAGE_SIZE, String.valueOf(mQueryParamPageSize));
		_webServiceRequestParams.put(QueryParams.QUERY_PARAM_SESSION_ID, mQueryParamSessionId);
		
		WebServiceThread _webServiceThread = new WebServiceThread(new SysInformQueryResultListBean(), _webServiceRequestParams, new MyHandler(this), msgWhat);
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
							public void onClick(DialogInterface dialog, int which) {
								if(which == DialogInterface.BUTTON_NEUTRAL){
									_mActivity.finish();
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

	private void init() {

		initVariables();
		initViews();

	}

	private void initVariables() {
		
		mQueryParamMsgCate = "";
		mQueryParamPageIndex = 1;
		mQueryParamPageSize = Constants.DEFAULT_PAGE_SIZE;
		mQueryParamSessionId = "20C5DA37D9CF5C8FDE3DD19E858D5614";
	}

	private void initViews() {
		
		imgBtn_topRight = (ImageButton) findViewById(R.id.imgBtn_topRight);
		imgBtn_topRight.setVisibility(View.VISIBLE);
		imgBtn_topRight.setImageResource(R.drawable.list);

		listV_sysInformQueryResultList = (PullToRefreshListView) findViewById(R.id.listV_sysInformQueryResultList);
		listV_sysInformQueryResultList.setMode(Mode.BOTH);
		
		mSysInformQueryResultListAdapter = new SysInformQueryResultListAdapter(getApplicationContext());
		mSysInformQueryResultListAdapter.initDatas(new ArrayList<SysInformBean>());
		listV_sysInformQueryResultList.setAdapter(mSysInformQueryResultListAdapter);
		
		listV_sysInformQueryResultList.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		
		Intent _intent = new Intent(this, SysInformResultDetailActivity.class);
		
		String _title = sysInformQueryResultListBean.mSysInformBeanList.get(position - 1).title.toString();
//		String _cateName = sysInformQueryResultListBean.mSysInformBeanList.get(position - 1).cateName.toString();
		String _date = sysInformQueryResultListBean.mSysInformBeanList.get(position - 1).date.toString();
		String _author = sysInformQueryResultListBean.mSysInformBeanList.get(position - 1).author.toString();
		String _content = sysInformQueryResultListBean.mSysInformBeanList.get(position - 1).content.toString();
		
		Bundle _bundle = new Bundle();
		
		_bundle.putString("title", _title);
		_bundle.putString("cateName", "??");
		_bundle.putString("date", _date);
		_bundle.putString("author", _author);
		_bundle.putString("content", _content);
		_intent.putExtras(_bundle);
		
		startActivity(_intent);
		
	}

}
