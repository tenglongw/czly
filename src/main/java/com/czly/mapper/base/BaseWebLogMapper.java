package com.czly.mapper.base;

import com.czly.entity.WebLog;

public interface BaseWebLogMapper extends BaseMapper<WebLog> {

    int insert(WebLog webLog);
}