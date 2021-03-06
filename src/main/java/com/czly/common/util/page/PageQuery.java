package com.czly.common.util.page;

import com.czly.common.util.CommonConfig;


public class PageQuery {
	public PageQuery(){
		this.pageIndex = 1;
	}
	protected Integer pageIndex;
	protected int limit = CommonConfig.DEFAULT_PAGE_SIZE;
	public int getPageOffset() {
		return (this.pageIndex-1)<0?0:(this.pageIndex-1)*this.limit;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
}
