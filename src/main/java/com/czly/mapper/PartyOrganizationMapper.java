package com.czly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.czly.common.util.page.PageQuery;
import com.czly.entity.PartyOrganization;
import com.czly.mapper.base.BasePartyOrganizationMapper;

public interface PartyOrganizationMapper extends BasePartyOrganizationMapper {

	public List<PartyOrganization> queryPartyOrganizationList();
	
	public List<PartyOrganization> getPartyOrganizationListByConditions(
			@Param("title") String title,@Param("pageQuery") PageQuery pageQuery);
	public int getCountByConditions(@Param("title") String title);
	
	public PartyOrganization queryByName(@Param("name") String name);
}