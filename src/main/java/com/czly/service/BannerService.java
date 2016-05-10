package com.czly.service;

import java.util.List;

import com.czly.common.util.page.PageQuery;
import com.czly.common.util.page.PageResult;
import com.czly.entity.Banner;
import com.czly.service.base.BaseService;

public interface BannerService extends BaseService<Banner> {
	
	public Banner getById(Integer id);
	
	public Boolean deleteById(Integer id);
	
	public void addBanner(Banner banner);
	
	public List<Banner> queryBannerList(String type);
	
	public PageResult<Banner> getAllBannerList(String title, PageQuery pageQuery);
}
