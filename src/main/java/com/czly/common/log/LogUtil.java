package com.czly.common.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {
	/**
	 * 系统日志
	 */
	public static Logger systemLog = LogManager.getLogger("system");
	
	/**
	 * 操作日志
	 */
	public static Logger optLog = LogManager.getLogger("opt");
}
