package com.czly.mapper.base;

import com.czly.entity.PartyPerson;

public interface BasePartyPersonMapper extends BaseMapper<PartyPerson> {

    int insert(PartyPerson record);
}