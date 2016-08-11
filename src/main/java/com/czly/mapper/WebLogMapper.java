package com.czly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.czly.common.util.page.PageQuery;
import com.czly.entity.UVPVLog;
import com.czly.mapper.base.BaseWebLogMapper;


public interface WebLogMapper extends BaseWebLogMapper {
	public List<UVPVLog> getWebLogByConditions(
			@Param("module") String module,
			@Param("pageQuery") PageQuery pageQuery);
	public int getCountByConditions(@Param("module") String module);
}