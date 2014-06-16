package com.cndsteel.framework.constant;

/**
 * 错误代码信息对照表
 * @author zhxl
 *
 */
public class ErrorMsg {

	public static String getErrorMsg(int errorCode){
		String _errorMsg = null;
		
		switch(errorCode){
		case 1:
			_errorMsg = "未知错误";
			break;
		case 11:
			_errorMsg = "远程服务器返回错误: (404) 未找到";
			break;
		case 12:
			_errorMsg = "服务器维护中...";
			break;
		case 100:
			_errorMsg = "数据库操作失败";
			break;
		case 202:
			_errorMsg = "用户名或密码错误";
			break;
		case 203:
			_errorMsg = "用户不存在";
			break;
		case 204:
			_errorMsg = "用户未登录或已在其它地点登录";
			break;
		case 205:
			_errorMsg = "该用户无此权限";
			break;
		case 301:
			_errorMsg = "此Id不存在";
			break;
		case 401:
			_errorMsg = "参数错误";
			break;
		default:
			_errorMsg = "";
			break;
		}
		
		return _errorMsg;
	}
	
}
