package com.czly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.czly.common.util.page.PageQuery;
import com.czly.entity.User;
import com.czly.mapper.base.BaseUserMapper;

public interface UserMapper extends BaseUserMapper {
	User getUserByName(@Param("loginName") String loginName,
			@Param("passwd") String passwd);

	public List<User> getUserListByConditions(
			@Param("userName") String userName,
			@Param("pageQuery") PageQuery pageQuery);
	public int getCountByConditions(@Param("userName") String userName);

	public int checkLoginName(@Param("loginName") String loginname,
			@Param("id") Integer id);
	
	public int insertReturnId(@Param("user") User user);
	
	public List<User> queryAllUser();
}