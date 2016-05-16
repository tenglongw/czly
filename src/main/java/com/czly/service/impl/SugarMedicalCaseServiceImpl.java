package com.czly.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.czly.common.util.HttpUtil;
import com.czly.common.util.page.PageQuery;
import com.czly.common.util.page.PageResult;
import com.czly.entity.SugarMedicalCase;
import com.czly.mapper.SugarMedicalCaseMapper;
import com.czly.mapper.base.BaseMapper;
import com.czly.service.SugarMedicalCaseService;
import com.czly.service.base.impl.BaseServiceImpl;

import net.sf.json.JSONObject;

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
	private static final String URL = "http://query.qoofan.com/single";
	private static final String TOKEN = "ede7f79bc7ca9149e0a0a4bb160a6080";
	@Override
	public void addSugarMedicalCase(SugarMedicalCase sugarMedicalCase) {
		if(null != sugarMedicalCase.getId()){
			sugarMedicalCaseMapper.updateByPrimaryKeySelective(sugarMedicalCase);	
		}else{
			sugarMedicalCaseMapper.insert(sugarMedicalCase);
		}
	}
	@Override
	public List<SugarMedicalCase> querySugarMedicalCaseList(String caseType,String searchName) {
		// TODO Auto-generated method stub
		return sugarMedicalCaseMapper.querySugarMedicalCaseList(caseType,searchName);
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
	@Override
	public List<SugarMedicalCase> querySugarMecicalReadNum(List<SugarMedicalCase> smcList) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("token", TOKEN);
		List <SugarMedicalCase> tempList = new ArrayList<SugarMedicalCase>();
		for(SugarMedicalCase smc : smcList){
			JSONObject jsonObject = new JSONObject();
			param.put("url", smc.getUrl());
			jsonObject = JSONObject.fromObject(HttpUtil.post(URL, param));
			if(jsonObject.get("msg").equals("success")){
				Map<String,Integer> dataMap = (Map<String, Integer>) jsonObject.get("data");
				if(null != dataMap){
					Integer readNum = dataMap.get("read_num");
					smc.setReadNum(readNum);
				}
			}
			tempList.add(smc);
		}
		return tempList;
	}


}
