package com.czly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.czly.common.util.page.PageQuery;
import com.czly.entity.Banner;
import com.czly.mapper.base.BaseBannerMapper;

public interface BannerMapper extends BaseBannerMapper {
	
	public List<Banner> queryBannerList(@Param("type") String type);
	
	public List<Banner> getBannerListByConditions(
			@Param("title") String title,
			@Param("pageQuery") PageQuery pageQuery);
	public int getCountByConditions(@Param("title") String title);
}