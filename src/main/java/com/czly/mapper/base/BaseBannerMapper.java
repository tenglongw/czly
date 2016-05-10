package com.czly.mapper.base;

import com.czly.entity.Banner;

public interface BaseBannerMapper extends BaseMapper<Banner> {

    int insert(Banner banner);
}