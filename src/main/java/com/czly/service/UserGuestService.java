package com.czly.service;

import com.czly.entity.UserGuest;
import com.czly.service.base.BaseService;

public interface UserGuestService extends BaseService<UserGuest> {
	

	public void addUser(UserGuest userguest);
}
