package com.czly.service;

import java.util.List;

import com.czly.common.util.page.PageQuery;
import com.czly.common.util.page.PageResult;
import com.czly.entity.SugarMedicalCase;
import com.czly.service.base.BaseService;

public interface SugarMedicalCaseService extends BaseService<SugarMedicalCase> {
	

	public SugarMedicalCase getById(Integer id);
	
	public Boolean deleteById(Integer id);
	
	public void addSugarMedicalCase(SugarMedicalCase sugarMedicalCase);
	
	public List<SugarMedicalCase> querySugarMedicalCaseList(String caseType,String searchName);
	
	public List<SugarMedicalCase> querySugarMecicalReadNum(List<SugarMedicalCase> smcList);
	
	public List<SugarMedicalCase> querySugarMedicalCaseIndex();
	
	public PageResult<SugarMedicalCase> getAllSugarMedicalCaseList(String title, PageQuery pageQuery);
}
