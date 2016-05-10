package com.czly.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czly.common.util.page.PageQuery;
import com.czly.common.util.page.PageResult;
import com.czly.entity.User;
import com.czly.mapper.UserMapper;
import com.czly.mapper.base.BaseMapper;
import com.czly.service.UserService;
import com.czly.service.base.impl.BaseServiceImpl;
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {
	// 注入到父类中
	@Resource(name = "userMapper")
	private UserMapper userMapper;
	@Override
	public BaseMapper<User> getBaseMapper() {
		return userMapper;
	}
	

	@Override
	public User getUserByName(String userName, String userPwd) {
		return userMapper.getUserByName(userName, userPwd);
	}

	@Override
	public PageResult<User> getAllUserList(String userName, PageQuery pageQuery) {
		PageResult<User> result = new PageResult<User>();
		List<User> userList = userMapper.getUserListByConditions(userName,
				pageQuery);
		int totalCount = userMapper.getCountByConditions(userName);
		result.setItems(userList);
		result.setPageQuery(pageQuery);
		result.setCount(totalCount);
		return result;
	}

	@Override
	public boolean checkLoginName(String loginName, Integer id) {
		int cnt = userMapper.checkLoginName(loginName, id);
		if (cnt > 0) {
			return true;
		}
		return false;
	}

	
	public boolean addUser(User user, String loginName, String userName,
			String userPwd,Byte type,Integer hospitalId, String optUser) {
		boolean resultBoolean = true;
		try{
			user.setLoginName(loginName);
			user.setUserName(userName);
			user.setPasswd(userPwd);
			user.setUpdatedby(optUser);
			user.setUpdatetime(new Date());
			if (null == user.getId()) {
				user.setCreatedby(optUser);
				user.setCreationtime(new Date());
				resultBoolean=add(user);
			} else {
				resultBoolean= userMapper.updateByPrimaryKeySelective(user)>0;
			}
			return resultBoolean;
		 }catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public Boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		try{
			return userMapper.deleteByPrimaryKey(id)>0;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	
	@Override
	@Transactional
	public Boolean deleteById(Integer id,Integer hospitalId) {
		// TODO Auto-generated method stub
		try{
			return userMapper.deleteByPrimaryKey(id)>0;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		try{
			return userMapper.insertReturnId(user);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
