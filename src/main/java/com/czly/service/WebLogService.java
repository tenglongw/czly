package com.czly.service;

import com.czly.common.util.page.PageQuery;
import com.czly.common.util.page.PageResult;
import com.czly.entity.UVPVLog;
import com.czly.entity.WebLog;
import com.czly.service.base.BaseService;

public interface WebLogService extends BaseService<WebLog> {
	
	
	public void addWebLog(WebLog webLog);
	public PageResult<UVPVLog> getWebLogList(String module, PageQuery pageQuery);
}
