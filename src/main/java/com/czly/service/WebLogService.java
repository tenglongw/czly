package com.czly.service;

import com.czly.entity.WebLog;
import com.czly.service.base.BaseService;

public interface WebLogService extends BaseService<WebLog> {
	
	
	public void addWebLog(WebLog webLog);
	
}
