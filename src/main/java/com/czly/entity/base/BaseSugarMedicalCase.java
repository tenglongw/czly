package com.czly.entity.base;

import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;

public class BaseSugarMedicalCase {

	private Integer id;
	private String caseType;
	private String icon;
	private String icon1;
	private String title;
	private String description;
	private String brand;
	private int readNum;
	private int displayFlag;
	private String url;
	private String keyword;
	private int isIndex;
	private String createdby;
	private String updatedby;
	private Date creationtime;
	private Date updatetime;
	private String creationDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getReadNum() {
		return readNum;
	}
	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}
	public int getDisplayFlag() {
		return displayFlag;
	}
	public void setDisplayFlag(int displayFlag) {
		this.displayFlag = displayFlag;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getIsIndex() {
		return isIndex;
	}
	public void setIsIndex(int isIndex) {
		this.isIndex = isIndex;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public Date getCreationtime() {
		return creationtime;
	}
	public void setCreationtime(Date creationtime) {
		this.creationtime = creationtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getCreationDate() {
		return DateUtil.formatDate(creationtime, "yyyy-MM-dd");
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getIcon1() {
		return icon1;
	}
	public void setIcon1(String icon1) {
		this.icon1 = icon1;
	}
	
}
