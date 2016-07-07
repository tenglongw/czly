package com.czly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.czly.common.util.page.PageQuery;
import com.czly.common.util.page.PageResult;
import com.czly.entity.PartyOrganization;
import com.czly.mapper.PartyOrganizationMapper;
import com.czly.mapper.base.BaseMapper;
import com.czly.service.PartyOrganizationService;
import com.czly.service.base.impl.BaseServiceImpl;

@Service
public class PartyOrganizationServiceImpl extends BaseServiceImpl<PartyOrganization> implements
	PartyOrganizationService {
	// 注入到父类中
	@Resource(name = "partyOrganizationMapper")
	private PartyOrganizationMapper partyOrganizationMapper;
	@Override
	public BaseMapper<PartyOrganization> getBaseMapper() {
		return partyOrganizationMapper;
	}
	@Override
	public void addPartyOrganization(PartyOrganization partyOrganization) {
		if(null != partyOrganization.getId()){
			partyOrganizationMapper.updateByPrimaryKeySelective(partyOrganization);	
		}else{
			partyOrganizationMapper.insert(partyOrganization);
		}
	}
	@Override
	public List<PartyOrganization> queryPartyOrganizationList() {
		// TODO Auto-generated method stub
		return partyOrganizationMapper.queryPartyOrganizationList();
	}
	@Override
	public PageResult<PartyOrganization> getAllPartyOrganizationList(String title,PageQuery pageQuery) {
		PageResult<PartyOrganization> result = new PageResult<PartyOrganization>();
		List<PartyOrganization> userCaseList = partyOrganizationMapper.getPartyOrganizationListByConditions(title,pageQuery);
		int totalCount = partyOrganizationMapper.getCountByConditions(title);
		result.setItems(userCaseList);
		result.setPageQuery(pageQuery);
		result.setCount(totalCount);
		return result;
	}
	@Override
	public PartyOrganization getByName(String name) {
		return partyOrganizationMapper.queryByName(name);
	}
}
