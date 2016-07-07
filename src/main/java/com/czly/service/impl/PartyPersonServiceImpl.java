package com.czly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.czly.entity.PartyPerson;
import com.czly.mapper.PartyPersonMapper;
import com.czly.mapper.base.BaseMapper;
import com.czly.service.PartyPersonService;
import com.czly.service.base.impl.BaseServiceImpl;

@Service
public class PartyPersonServiceImpl extends BaseServiceImpl<PartyPerson> implements
	PartyPersonService {
	// 注入到父类中
	@Resource(name = "partyPersonMapper")
	private PartyPersonMapper partyPersonMapper;
	@Override
	public BaseMapper<PartyPerson> getBaseMapper() {
		return partyPersonMapper;
	}
	@Override
	public void addPartyPerson(PartyPerson partyPerson) {
		if(null != partyPerson.getId()){
			partyPersonMapper.updateByPrimaryKeySelective(partyPerson);	
		}else{
			partyPersonMapper.insert(partyPerson);
		}
	}
	@Override
	public List<PartyPerson> queryPartyPersonList(Integer status,Integer partyOrganizationId) {
		// TODO Auto-generated method stub
		return partyPersonMapper.queryPartyPersonList(status,partyOrganizationId);
	}
}
