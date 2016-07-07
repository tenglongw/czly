package com.czly.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czly.common.util.Root;
import com.czly.common.util.lang.Strings;
import com.czly.common.util.page.PageQuery;
import com.czly.common.util.page.PageResult;
import com.czly.entity.PartyOrganization;
import com.czly.entity.PartyPerson;
import com.czly.entity.User;
import com.czly.service.PartyOrganizationService;
import com.czly.service.PartyPersonService;

/**
 * @author wtl
 *
 */
@Controller
@RequestMapping("/party")
public class PartyBuildingController extends BaseController {

	static Logger logger = LogManager.getLogger(PartyBuildingController.class);
	@Resource
	private PartyOrganizationService partyOrganizationService;
	@Resource
	private PartyPersonService partyPersonService;

	/**
	 * 查询列表
	 * 
	 * @param caseType
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getPartyOrganizationList", method = RequestMethod.GET)
	protected Object getPartyOrganizationList() {
		logger.info("----------getPartyOrganizationList------------parameters{}");
		Root root = new Root();
		try {
			root.setStatus(Root.STATUS_OK);
			List<PartyOrganization> smcList = partyOrganizationService.queryPartyOrganizationList();
			root.setData(smcList);
		} catch (Exception e) {
			logger.error("getPartyOrganizationList error !!!!", e);
		}
		return root;
	}

	/**
	 * 删除组织
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deletePartyOrganizationById", method = RequestMethod.POST)
	protected Object deletePartyOrganizationById(@RequestParam(value = "id", required = false) Integer id) {
		Root root = new Root();
		logger.info("----------deletePartyOrganizationById------------parameters{id:" + id + "}");
		try {
			if (partyOrganizationService.deleteById(id)) {
				root.setStatus(Root.STATUS_OK);
				root.setMsg("删除成功");
			} else {
				root.setStatus(Root.STATUS_FAIL);
				root.setMsg("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除失败！！！", e);
		}
		return root;
	}

	/**
	 * 分页查询所有组织信息
	 * 
	 * @param searcTitle
	 * @param pageIndex
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPartyOrganizationList", method = RequestMethod.POST)
	protected Object queryPartyOrganizationList(@RequestParam(value = "searcTitle", required = false) String searcTitle,
			@RequestParam(value = "pageIndex") Integer pageIndex) {
		logger.info("----------queryPartyOrganizationList------------parameters{searcTitle:" + searcTitle
				+ ",pageIndex:" + pageIndex + "}");
		String userName = Strings.trimStringSearch(searcTitle);
		PageQuery pageQuery = new PageQuery();
		if (pageIndex != null) {
			pageQuery.setPageIndex(pageIndex);
		}
		PageResult<PartyOrganization> cases = partyOrganizationService.getAllPartyOrganizationList(userName, pageQuery);
		Root root = Root.getRootOKAndSimpleMsg();
		root.setData(cases);
		return root.toJsonString();
	}

	/**
	 * 获得组织信息
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getPartyOrganizationById", method = RequestMethod.POST)
	protected Object getPartyOrganizationById(@RequestParam(value = "id") Integer id) {
		logger.info("----------getSugarMedicalCaseById------------parameters{id:" + id + "}");
		Map<String,Object> result = new HashMap<String,Object>();
		//党组织信息
		PartyOrganization smcase = partyOrganizationService.getById(id);
		//待审批人员
		List<PartyPerson> smcList = partyPersonService.queryPartyPersonList(0, id);
		result.put("partyOrganization", smcase);
		result.put("partyPersonList", smcList);
		Root root = Root.getRootOKAndSimpleMsg();
		root.setData(result);
		return root;
	}

	/**
	 * 添加组织信息
	 * 
	 * @param id
	 * @param partyBranchName
	 * @param communityPartyCommittee
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addPartyOrganization", method = RequestMethod.POST)
	protected Object addPartyOrganization(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "partyBranchName") String partyBranchName,
			@RequestParam(value = "remark", required = false) String remark,
			@RequestParam(value = "communityPartyCommittee") String communityPartyCommittee,
			@RequestParam(value = "isNonPublicPartyBranch") Integer isNonPublicPartyBranch,
			@RequestParam(value = "branchSecretary") String branchSecretary, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User crUser = (User) session.getAttribute("user");
		Root root = new Root();
		if (crUser == null) {
			root.setStatus(Root.STATUS_FAIL);
			root.setMsg("登录超时,请重新登录!");
			return root.toJsonString();
		}
		PartyOrganization partyOrganization = new PartyOrganization();
		partyOrganization.setId(id);
		partyOrganization.setPartyBranchName(partyBranchName);
		partyOrganization.setCommunityPartyCommittee(communityPartyCommittee);
		partyOrganization.setCreatedby(crUser.getLoginName());
		partyOrganization.setIsNonPublicPartyBranch(isNonPublicPartyBranch);
		partyOrganization.setPartyMemberNum(0);
		partyOrganization.setRemark(remark);
		partyOrganization.setBranchSecretary(branchSecretary);
		partyOrganizationService.addPartyOrganization(partyOrganization);
		root = Root.getRootOKAndSimpleMsg();
		return root.toJsonString();
	}
	
	/**审批人员是否允许加入党组织
	 * @param id
	 * @param partyBranchName
	 * @param partyMemberNum
	 * @param communityPartyCommittee
	 * @param branchSecretary
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/approvalPartyPerson", method = RequestMethod.POST)
	protected Object approvalPartyPerson(@RequestParam(value = "partyPersonId") Integer id,
			@RequestParam(value = "status") Integer status) {
		logger.info("----------approvalPartyPerson------------parameters{partyPersonId:" + id
				+ ",status:" + status + "}");
		Root root = new Root();
		PartyPerson pp = partyPersonService.getById(id);
		PartyOrganization po = partyOrganizationService.getByName(pp.getCommunityBranch());
		//批准
		if(status == 1){
			po.setPartyMemberNum(po.getPartyMemberNum()+1);
			po.setUpdatetime(new Date());
			partyOrganizationService.addPartyOrganization(po);
		}
		pp.setStatus(status);
		partyPersonService.addPartyPerson(pp);
		root = Root.getRootOKAndSimpleMsg();
		return root.toJsonString();
	}

	/**校验党支部名字是否存在
	 * @param partyBranchName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkPartyBranchName", method = RequestMethod.POST)
	public Object checkPartyBranchName(@RequestParam(value = "partyBranchName") String partyBranchName) {
		PartyOrganization po = partyOrganizationService.getByName(partyBranchName.trim());
		logger.info("----------checkPartyBranchName------------parameters{partyBranchName:" + partyBranchName + "}");
		Map<String, String> result = new HashMap<String, String>();
		if (null != po) {
			result.put("isRepeat", "true");
		} else {
			result.put("isRepeat", "false");
		}
		return result;
	}

	/**
	 * 查询待审批人员列表
	 * 
	 * @param caseType
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getPartyPersonList", method = RequestMethod.GET)
	protected Object getPartyPersonList(@RequestParam(value = "status") Integer status,
			@RequestParam(value = "partyOrganizationId") Integer partyOrganizationId) {
		logger.info("----------getPartyPersonList------------parameters{status:" + status + "}");
		Root root = new Root();
		try {
			root.setStatus(Root.STATUS_OK);
			List<PartyPerson> smcList = partyPersonService.queryPartyPersonList(status, partyOrganizationId);
			root.setData(smcList);
		} catch (Exception e) {
			logger.error("getPartyPersonList error !!!!", e);
		}
		return root;
	}

	/**
	 * 审批
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addPartyPerson", method = RequestMethod.POST)
	protected Object addPartyPerson(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "sex") Integer sex,
			@RequestParam(value = "residentialAddress") String residentialAddress,
			@RequestParam(value = "workAddress") String workAddress,
			@RequestParam(value = "partyCommittee") String partyCommittee,
			@RequestParam(value = "communityBranch") String communityBranch,
			@RequestParam(value = "partyPosition") String partyPosition,
			@RequestParam(value = "identityCard") String identityCard,
			@RequestParam(value = "phoneNumber") String phoneNumber) {
		logger.info("----------addPartyPerson------------parameters" + "{id:" + id + ",name:" + name + ",sex:" + sex
				+ ",residentialAddress" + residentialAddress + ",workAddress" + workAddress + ",partyCommittee"
				+ partyCommittee + ",communityBranch" + communityBranch + ",partyPosition" + partyPosition
				+ ",identityCard" + identityCard + ",phoneNumber" + phoneNumber+ "}");
		Root root = new Root();
		try {
			PartyPerson partyPerson = new PartyPerson();
			PartyOrganization po = partyOrganizationService.getByName(communityBranch);
			if(null != po){
				partyPerson.setCommunityBranchId(po.getId());
			}
			partyPerson.setId(id);
			partyPerson.setName(name);
			partyPerson.setSex(sex);
			partyPerson.setResidentialAddress(residentialAddress);
			partyPerson.setWorkAddress(workAddress);
			partyPerson.setPartyCommittee(partyCommittee);
			partyPerson.setCommunityBranch(communityBranch);
			partyPerson.setPartyPosition(partyPosition);
			partyPerson.setIdentityCard(identityCard);
			partyPerson.setPhoneNumber(phoneNumber);
			partyPerson.setStatus(0);
			partyPersonService.addPartyPerson(partyPerson);
			root = Root.getRootOKAndSimpleMsg();
		} catch (Exception e) {
			root = Root.getRootFailAndSimpleMsg();
			logger.error("addPartyPerson error !!!!", e);
		}
		return root;
	}
}