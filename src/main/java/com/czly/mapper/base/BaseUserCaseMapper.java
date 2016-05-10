package com.czly.mapper.base;

import com.czly.entity.UserCase;

public interface BaseUserCaseMapper extends BaseMapper<UserCase> {

    int insert(UserCase record);
}