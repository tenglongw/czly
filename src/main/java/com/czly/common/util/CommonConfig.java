package com.czly.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//公共配置
public class CommonConfig {
	private static Logger logger = LogManager.getLogger(CommonConfig.class);
	public static final String DEFAULT_USER = "system";

	private static Map<String, Map<String, String>> staticServerCofigMap = new HashMap<String, Map<String, String>>();

	public static int DEFAULT_PAGE_SIZE = 15;

	public static Map<String, Boolean> hasLoginMap = new ConcurrentHashMap<String, Boolean>();

	// 是否是管理员用户 1是2否
	public static final int ADMIN_YES = 1;

	public static final int ADMIN_NO = 2;
	
	public static final String DEF_CON_NAME = "会诊_";
	
	public static final int SOFT_CLOUD = 0;
	public static final int SOFT_HOSPITAL = 1;
	
	private static int softType = 0;
	
	public static int getSoftType() {
		return softType;
	}

	public static void setSoftType(int softType) {
		CommonConfig.softType = softType;
	}
	

	
	/**
	 * 使用该方法获取到配置
	 * @param category
	 * @param name
	 * @return
	 */
	public static String getValueByCategoryAndName(String category,String name){
		Map<String, String> oneKindServerConfig = staticServerCofigMap.get(category);
		if (oneKindServerConfig != null) return oneKindServerConfig.get(name);
		return null;
	}
	public static void main(String[] args) {
		String PKey = "uya3ka";
		String action = "listdevice";
		String clientid = "187";
		Long utime = new Date().getTime() / 1000;
		
		String vcodePreCode = "action"+action + "clientid" + clientid + "utime" + utime.toString() + PKey;
		
		System.out.println("utime:"+utime);
		
		System.out.println("C+D歩结果："+vcodePreCode);
		
		String vcode = calCodeByMd5(vcodePreCode);
		System.out.println("vcode:" + vcode);
		
		System.out.println("http://192.168.1.165:11982/MAClient?action=listdevice&clientid=187&utime="+utime+"&vcode="+vcode);
		
		
		action = "playsingle";
		String playsinglevcode = "action"+action +"clientid" + clientid + "deviceCSITestVideo0x01utime" + utime.toString() + PKey;
		
		vcode = calCodeByMd5(playsinglevcode);
		System.out.println("http://192.168.1.165:11982/MAClient?action=playsingle&device=CSITestVideo0x01&clientid=187&utime="+utime+"&vcode="+vcode);
	}
	
	public static String calCodeByMd5(String vcodePreCode){
		String vcodeMd5 = MD5Util.md5(vcodePreCode);
		String vcode = vcodeMd5.substring(2, 3) + vcodeMd5.substring(12, 13)
				+ vcodeMd5.substring(28, 29)+ vcodeMd5.substring(6, 7)
				+ vcodeMd5.substring(15, 16)+ vcodeMd5.substring(7, 8)
				+ vcodeMd5.substring(18, 19)+ vcodeMd5.substring(4, 5);
		return vcode;
	}
	
}