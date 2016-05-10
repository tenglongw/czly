package com.czly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.czly.entity.UserGuest;
import com.czly.mapper.UserGuestMapper;
import com.czly.mapper.base.BaseMapper;
import com.czly.service.UserGuestService;
import com.czly.service.base.impl.BaseServiceImpl;

@Service
public class UserGuestServiceImpl extends BaseServiceImpl<UserGuest> implements
		UserGuestService {
	// 注入到父类中
	@Resource(name = "userGuestMapper")
	private UserGuestMapper userGuestMapper;
	@Override
	public BaseMapper<UserGuest> getBaseMapper() {
		return userGuestMapper;
	}
	@Override
	public void addUser(UserGuest userGuest) {
		if(null != userGuest.getId()){
			userGuestMapper.updateByPrimaryKeySelective(userGuest);	
		}else{
			userGuestMapper.insert(userGuest);
		}
	}


}
