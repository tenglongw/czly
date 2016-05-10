package com.czly.mapper.base;

import com.czly.entity.SugarMedicalCase;

public interface BaseSugarMedicalCaseMapper extends BaseMapper<SugarMedicalCase> {

    int insert(SugarMedicalCase record);
}