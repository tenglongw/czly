package com.czly.mapper.base;

import com.czly.entity.UserGuest;

public interface BaseUserGuestMapper extends BaseMapper<UserGuest> {

    int insert(UserGuest record);
}