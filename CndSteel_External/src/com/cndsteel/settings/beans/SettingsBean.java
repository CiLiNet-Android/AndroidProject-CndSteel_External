package com.cndsteel.settings.beans;

import java.util.HashMap;

import android.text.TextUtils;

import com.cndsteel.framework.bean.BaseBean;
import com.cndsteel.framework.constant.Constants;
import com.cndsteel.framework.log.GlobalLog;
import com.cndsteel.framework.utils.ObjectCacheUtils;

/**
 * 设置（程序运行时，放在内存中）
 * @author zhxl
 *
 */
public class SettingsBean extends BaseBean {

	private static final long serialVersionUID = 2938165386213618706L;
	
	private static SettingsBean mSettingsBean;
	
	/**
	 * 程序运行时，所有配置信息都放在缓存中
	 */
	private HashMap<String, String> mSettingsMap = new HashMap<String, String>();

	private SettingsBean(){}

	public synchronized static SettingsBean getInstance(){
		if(null == mSettingsBean){
			mSettingsBean = (SettingsBean)ObjectCacheUtils.readObject(Constants.CACHE_DIR + SettingsBean.class.getSimpleName().hashCode());
			if(null == mSettingsBean){
				mSettingsBean = new SettingsBean();
			}
		}
		
		return mSettingsBean;
	}
	
	public boolean getBooleanSettingValueByName(String name,boolean defaultValue){
		boolean _settingValue = defaultValue;
		
		String _value = mSettingsMap.get(name);
		if(!TextUtils.isEmpty(_value)){
			_settingValue = Boolean.valueOf(_value);
		}
		
		return _settingValue;
	}
	
	public String getStringSettingValueByName(String name){
		return mSettingsMap.get(name);
	}
	
	public void putSettingValue(String name,String value){
		GlobalLog.i(name + ": " + value);
		if(mSettingsMap.containsKey(name)){
			mSettingsMap.remove(name);
			GlobalLog.i(name + "已经存在 out value: " + value);
		}
		mSettingsMap.put(name, value);
		GlobalLog.i("保存: " + mSettingsMap.get(name));
	}
	
	public void deleteSettingValue(String name){
		if(mSettingsMap.containsKey(name)){
			mSettingsMap.remove(name);
		}
	}
	
	public static void save(){
		ObjectCacheUtils.cacheObject(Constants.CACHE_DIR + SettingsBean.class.getSimpleName().hashCode(), mSettingsBean);
	}
}
