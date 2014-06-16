package com.cndsteel.framework.constant;

import android.os.Environment;

/**
 * 全局配置
 * @author zhxl
 */
public interface Constants {

	/**
	 * 调试标志位
	 */
	public static final boolean DEBUG = true;
	
	/**
	 * 全局编码
	 */
	public static final String CHARACTER_ENCODING = "UTF-8"; 
	
	/**
	 * 根路径
	 */
	public static final String BASE_PATH = Environment.getExternalStorageDirectory() + "/.cndSteel/";
	
	/**
	 * 日志文件路径
	 */
	public static final String LOG_DIR = BASE_PATH;
	public static final String LOG_CRASH_FILE = LOG_DIR + "error.log";
	
	/**
	 * 缓存路径
	 */
	public static final String CACHE_DIR = "/data/data/com.cndsteel/";
	
	/**
	 * 缓存变量
	 */
	public static final String SETTINGS_PARAM_AUTOLOGIN = "AUTOLOGIN";
	public static final String SETTINGS_PARAM_USERNAME = "USERNAME";
	public static final String SETTINGS_PARAM_PASSWORD = "PASSWORD";
	public static final String SETTINGS_PARAM_SESSIONID = "SESSIONID";
	
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	/**
	 * 请求HandlerMsg消息类型
	 */
	public static final int HANDLER_MSG_DATA_LOAD_SUCCESS = 0x000;
	
	public static final int HANDLER_MSG_REQUEST_FAILURE = 0x001;
	public static final int HANDLER_MSG_DATA_PARSE_ERROR = 0xFFF;
	public static final int HANDLER_MSG_THREAD_CANCEL = 0x404;
	
	
}
