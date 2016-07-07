package com.czly.entity.base;

import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;

public class BasePartyPerson {

	private Integer id;
	private String name; //名称
	private Integer sex; //性别 0：男，1：女
	private String residentialAddress; //居住地址
	private String workAddress; //工作地址
	private String partyCommittee; //社区党委
	private String communityBranch; //所属支部
	private Integer communityBranchId; //所属支部
	private String partyPosition; //党内职务
	private String identityCard; //身份证
	private String phoneNumber; //联系电话
	private Integer status; //申请状态 0：申请中，1：通过，2：拒绝
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getResidentialAddress() {
		return residentialAddress;
	}
	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}
	public String getWorkAddress() {
		return workAddress;
	}
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	public String getPartyCommittee() {
		return partyCommittee;
	}
	public void setPartyCommittee(String partyCommittee) {
		this.partyCommittee = partyCommittee;
	}
	public String getCommunityBranch() {
		return communityBranch;
	}
	public void setCommunityBranch(String communityBranch) {
		this.communityBranch = communityBranch;
	}
	public String getPartyPosition() {
		return partyPosition;
	}
	public void setPartyPosition(String partyPosition) {
		this.partyPosition = partyPosition;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getCommunityBranchId() {
		return communityBranchId;
	}
	public void setCommunityBranchId(Integer communityBranchId) {
		this.communityBranchId = communityBranchId;
	}
}
