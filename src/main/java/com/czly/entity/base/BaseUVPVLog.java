package com.czly.entity.base;

import java.util.Date;

public class BaseUVPVLog {

	private Integer uv;
	private Integer pv;
	private String module;
	private Date creationtime;
	public Integer getUv() {
		return uv;
	}
	public void setUv(Integer uv) {
		this.uv = uv;
	}
	public Integer getPv() {
		return pv;
	}
	public void setPv(Integer pv) {
		this.pv = pv;
	}
	public Date getCreationtime() {
		return creationtime;
	}
	public void setCreationtime(Date creationtime) {
		this.creationtime = creationtime;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
}
