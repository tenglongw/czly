package com.czly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.czly.common.util.page.PageQuery;
import com.czly.entity.SugarMedicalCase;
import com.czly.mapper.base.BaseSugarMedicalCaseMapper;

public interface SugarMedicalCaseMapper extends BaseSugarMedicalCaseMapper {

	public List<SugarMedicalCase> querySugarMedicalCaseList(@Param("caseType") String caseType,@Param("searchName") String searchName);
	
	public List<SugarMedicalCase> querySugarMedicalCaseIndex();
	
	public List<SugarMedicalCase> getSugarMedicalCaseListByConditions(
			@Param("title") String title,
			@Param("pageQuery") PageQuery pageQuery);
	public int getCountByConditions(@Param("title") String title);
}