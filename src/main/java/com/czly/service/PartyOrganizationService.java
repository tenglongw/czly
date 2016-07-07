package com.czly.service;

import java.util.List;

import com.czly.common.util.page.PageQuery;
import com.czly.common.util.page.PageResult;
import com.czly.entity.PartyOrganization;
import com.czly.service.base.BaseService;

public interface PartyOrganizationService extends BaseService<PartyOrganization> {
	

	public PartyOrganization getById(Integer id);
	
	public Boolean deleteById(Integer id);
	
	public void addPartyOrganization(PartyOrganization partyOrganization);
	
	public List<PartyOrganization> queryPartyOrganizationList();
	
	public PartyOrganization getByName(String name);
	
	public PageResult<PartyOrganization> getAllPartyOrganizationList(String title,PageQuery pageQuery);
}
