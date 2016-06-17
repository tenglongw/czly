package com.czly.service;

import com.czly.common.util.page.PageQuery;
import com.czly.common.util.page.PageResult;
import com.czly.entity.User;
import com.czly.service.base.BaseService;

public interface UserService extends BaseService<User> {
	User getUserByName(String userName, String userPwd);
	
	public boolean checkLoginName(String loginName, Integer id);

	public boolean addUser(User user, String loginName, String userName,
			String userPwd, String optUser);
	
	public User getById(Integer id);
	
	public Boolean deleteById(Integer id);
	
	public int addUser(User user);
	
	public Boolean deleteById(Integer id,Integer hospitalId);
	
	public PageResult<User> getAllUserList(String userName, PageQuery pageQuery);
}
