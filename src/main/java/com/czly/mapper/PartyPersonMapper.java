package com.czly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.czly.entity.PartyPerson;
import com.czly.mapper.base.BasePartyPersonMapper;

public interface PartyPersonMapper extends BasePartyPersonMapper {

	public List<PartyPerson> queryPartyPersonList(@Param("status") Integer status,
			@Param("partyOrganizationId") Integer partyOrganizationId);

}