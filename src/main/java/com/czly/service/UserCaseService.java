package com.czly.service;

import java.util.List;

import com.czly.common.util.page.PageQuery;
import com.czly.common.util.page.PageResult;
import com.czly.entity.UserCase;
import com.czly.service.base.BaseService;

public interface UserCaseService extends BaseService<UserCase> {
	

	public UserCase getById(Integer id);
	
	public Boolean deleteById(Integer id);
	
	public void addUser(UserCase userCase);
	
	public List<UserCase> queryUserCaseList(String caseType);
	
	public PageResult<UserCase> getAllUserCaseList(String title, PageQuery pageQuery);
}
