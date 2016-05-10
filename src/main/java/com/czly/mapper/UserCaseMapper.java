package com.czly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.czly.common.util.page.PageQuery;
import com.czly.entity.UserCase;
import com.czly.mapper.base.BaseUserCaseMapper;

public interface UserCaseMapper extends BaseUserCaseMapper {

	public List<UserCase> queryUserCaseList(@Param("caseType") String caseType);
	
	public List<UserCase> getUserCaseListByConditions(
			@Param("title") String title,
			@Param("pageQuery") PageQuery pageQuery);
	public int getCountByConditions(@Param("title") String title);
}