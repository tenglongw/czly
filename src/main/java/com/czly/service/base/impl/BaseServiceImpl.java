package com.czly.service.base.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.czly.mapper.base.BaseMapper;
import com.czly.service.base.BaseService;

/**
 * @author zhourui
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private static Logger logger = LogManager.getLogger(BaseServiceImpl.class);
	
	public abstract BaseMapper<T> getBaseMapper();
	
	@Override
	public T getById(Long id) {
		return getBaseMapper().selectByPrimaryKey(id);
	}
	
	@Override
	public T getById(Integer id) {
		return getBaseMapper().selectByPrimaryKey(id);
	}

	@Override
	public T getById(String id) {
		return getBaseMapper().selectByPrimaryKey(id);
	}

	@Override
	public Boolean update(T record) {
		return getBaseMapper().updateByPrimaryKeySelective(record) > -1;
	}

	@Override
	public Boolean deleteById(Long id) {
		return getBaseMapper().deleteByPrimaryKey(id) > -1;
	}
	
	@Override
	public Boolean deleteById(Integer id) {
		return getBaseMapper().deleteByPrimaryKey(id) > -1;
	}

	@Override
	public Boolean deleteById(String id) {
		return getBaseMapper().deleteByPrimaryKey(id) > -1;
	}

	@Override
	public Boolean add(T record) {
		return getBaseMapper().insertSelective(record) > 0;
	}
	
}