package com.czly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.czly.common.util.page.PageQuery;
import com.czly.common.util.page.PageResult;
import com.czly.entity.SugarMedicalCase;
import com.czly.mapper.SugarMedicalCaseMapper;
import com.czly.mapper.base.BaseMapper;
import com.czly.service.SugarMedicalCaseService;
import com.czly.service.base.impl.BaseServiceImpl;

@Service
public class SugarMedicalCaseServiceImpl extends BaseServiceImpl<SugarMedicalCase> implements
	SugarMedicalCaseService {
	// 注入到父类中
	@Resource(name = "sugarMedicalCaseMapper")
	private SugarMedicalCaseMapper sugarMedicalCaseMapper;
	@Override
	public BaseMapper<SugarMedicalCase> getBaseMapper() {
		return sugarMedicalCaseMapper;
	}
	@Override
	public void addSugarMedicalCase(SugarMedicalCase sugarMedicalCase) {
		if(null != sugarMedicalCase.getId()){
			sugarMedicalCaseMapper.updateByPrimaryKeySelective(sugarMedicalCase);	
		}else{
			sugarMedicalCaseMapper.insert(sugarMedicalCase);
		}
	}
	@Override
	public List<SugarMedicalCase> querySugarMedicalCaseList(String caseType,String description) {
		// TODO Auto-generated method stub
		return sugarMedicalCaseMapper.querySugarMedicalCaseList(caseType,description);
	}
	@Override
	public PageResult<SugarMedicalCase> getAllSugarMedicalCaseList(String title,
			PageQuery pageQuery) {
		PageResult<SugarMedicalCase> result = new PageResult<SugarMedicalCase>();
		List<SugarMedicalCase> userCaseList = sugarMedicalCaseMapper.getSugarMedicalCaseListByConditions(title,
				pageQuery);
		int totalCount = sugarMedicalCaseMapper.getCountByConditions(title);
		result.setItems(userCaseList);
		result.setPageQuery(pageQuery);
		result.setCount(totalCount);
		return result;
	}
	@Override
	public List<SugarMedicalCase> querySugarMedicalCaseIndex() {
		// TODO Auto-generated method stub
		return sugarMedicalCaseMapper.querySugarMedicalCaseIndex();
	}


}
