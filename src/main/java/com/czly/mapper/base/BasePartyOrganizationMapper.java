package com.czly.mapper.base;

import com.czly.entity.PartyOrganization;

public interface BasePartyOrganizationMapper extends BaseMapper<PartyOrganization> {

    int insert(PartyOrganization record);
}