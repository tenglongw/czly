package com.czly.service;

import java.util.List;

import com.czly.entity.PartyPerson;
import com.czly.service.base.BaseService;

public interface PartyPersonService extends BaseService<PartyPerson> {
	

	public PartyPerson getById(Integer id);
	
	public Boolean deleteById(Integer id);
	
	public void addPartyPerson(PartyPerson partyPerson);
	
	public List<PartyPerson> queryPartyPersonList(Integer status,Integer partyOrganizationId);
}
