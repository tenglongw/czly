package com.czly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.czly.common.util.page.PageQuery;
import com.czly.common.util.page.PageResult;
import com.czly.entity.UserCase;
import com.czly.mapper.UserCaseMapper;
import com.czly.mapper.base.BaseMapper;
import com.czly.service.UserCaseService;
import com.czly.service.base.impl.BaseServiceImpl;

@Service
public class UserCaseServiceImpl extends BaseServiceImpl<UserCase> implements
		UserCaseService {
	// 注入到父类中
	@Resource(name = "userCaseMapper")
	private UserCaseMapper userCaseMapper;
	@Override
	public BaseMapper<UserCase> getBaseMapper() {
		return userCaseMapper;
	}
	@Override
	public void addUser(UserCase userCase) {
		if(null != userCase.getId()){
		 userCaseMapper.updateByPrimaryKeySelective(userCase);	
		}else{
			userCaseMapper.insert(userCase);
		}
	}
	@Override
	public List<UserCase> queryUserCaseList(String caseType) {
		// TODO Auto-generated method stub
		return userCaseMapper.queryUserCaseList(caseType);
	}
	@Override
	public PageResult<UserCase> getAllUserCaseList(String title,
			PageQuery pageQuery) {
		PageResult<UserCase> result = new PageResult<UserCase>();
		List<UserCase> userCaseList = userCaseMapper.getUserCaseListByConditions(title,
				pageQuery);
		int totalCount = userCaseMapper.getCountByConditions(title);
		result.setItems(userCaseList);
		result.setPageQuery(pageQuery);
		result.setCount(totalCount);
		return result;
	}


}
