package com.czly.entity.base;

import java.util.Date;

public class BasePartyOrganization {

	private Integer id;
	private String partyBranchName; //党组织名称
	private String communityPartyCommittee; //所属社区党委
	private Integer isNonPublicPartyBranch; //是否非公企业党支部，0：是，1：否
	private Integer partyMemberNum; //党组织人数
	private String branchSecretary; //支部书记
	private String createdby;
	private String updatedby;
	private Date creationtime;
	private Date updatetime;
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPartyBranchName() {
		return partyBranchName;
	}
	public void setPartyBranchName(String partyBranchName) {
		this.partyBranchName = partyBranchName;
	}
	public String getCommunityPartyCommittee() {
		return communityPartyCommittee;
	}
	public void setCommunityPartyCommittee(String communityPartyCommittee) {
		this.communityPartyCommittee = communityPartyCommittee;
	}
	public Integer getPartyMemberNum() {
		return partyMemberNum;
	}
	public void setPartyMemberNum(Integer partyMemberNum) {
		this.partyMemberNum = partyMemberNum;
	}
	public String getBranchSecretary() {
		return branchSecretary;
	}
	public void setBranchSecretary(String branchSecretary) {
		this.branchSecretary = branchSecretary;
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
	public Integer getIsNonPublicPartyBranch() {
		return isNonPublicPartyBranch;
	}
	public void setIsNonPublicPartyBranch(Integer isNonPublicPartyBranch) {
		this.isNonPublicPartyBranch = isNonPublicPartyBranch;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
