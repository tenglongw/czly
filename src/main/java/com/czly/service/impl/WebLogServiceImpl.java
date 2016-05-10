package com.czly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.czly.entity.WebLog;
import com.czly.mapper.WebLogMapper;
import com.czly.mapper.base.BaseMapper;
import com.czly.service.WebLogService;
import com.czly.service.base.impl.BaseServiceImpl;

@Service
public class WebLogServiceImpl extends BaseServiceImpl<WebLog> implements
	WebLogService {
	// 注入到父类中
	@Resource(name = "webLogMapper")
	private WebLogMapper webLogMapper;
	@Override
	public BaseMapper<WebLog> getBaseMapper() {
		return webLogMapper;
	}
	@Override
	public void addWebLog(WebLog webLog) {
		webLogMapper.insert(webLog);
	}

}
