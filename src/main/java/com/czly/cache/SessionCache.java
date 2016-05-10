package com.czly.cache;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

public class SessionCache {
	public static ConcurrentHashMap<String, HttpSession> sessionMap = new ConcurrentHashMap<String, HttpSession>();
	
	/**
	 * 将所有的session干掉
	 */
	public static void cleanAllSession(){
		if (!SessionCache.sessionMap.isEmpty()){
			Set<String> keys = SessionCache.sessionMap.keySet();
			for (String key : keys){
				HttpSession session = SessionCache.sessionMap.get(key);
				session.removeAttribute("user");
			}
		}
		SessionCache.sessionMap.clear();
	}
}