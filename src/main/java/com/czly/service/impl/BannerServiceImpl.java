package com.czly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.czly.common.util.page.PageQuery;
import com.czly.common.util.page.PageResult;
import com.czly.entity.Banner;
import com.czly.mapper.BannerMapper;
import com.czly.mapper.base.BaseMapper;
import com.czly.service.BannerService;
import com.czly.service.base.impl.BaseServiceImpl;

@Service
public class BannerServiceImpl extends BaseServiceImpl<Banner> implements
		BannerService {
	// 注入到父类中
	@Resource(name = "bannerMapper")
	private BannerMapper bannerMapper;
	@Override
	public BaseMapper<Banner> getBaseMapper() {
		return bannerMapper;
	}
	@Override
	public void addBanner(Banner banner) {
		if(null != banner.getId()){
			bannerMapper.updateByPrimaryKeySelective(banner);	
		}else{
			bannerMapper.insert(banner);
		}		
	}
	@Override
	public List<Banner> queryBannerList(String type) {
		return bannerMapper.queryBannerList(type);
	}
	@Override
	public PageResult<Banner> getAllBannerList(String title,
			PageQuery pageQuery) {
		PageResult<Banner> result = new PageResult<Banner>();
		List<Banner> bannerList = bannerMapper.getBannerListByConditions(title,
				pageQuery);
		int totalCount = bannerMapper.getCountByConditions(title);
		result.setItems(bannerList);
		result.setPageQuery(pageQuery);
		result.setCount(totalCount);
		return result;
	}

}
