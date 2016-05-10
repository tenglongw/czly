package com.czly.controller;

import java.util.Date;

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
import com.czly.entity.User;
import com.czly.entity.UserCase;
import com.czly.service.UserCaseService;

@Controller
public class UserCaseController extends BaseController {

	static Logger logger = LogManager.getLogger(UserCaseController.class);
	@Resource
	private UserCaseService userCaseService;

	@ResponseBody
	@RequestMapping(value = "/getUserCaseList", method = RequestMethod.GET)
	protected Object getUserCaseList(@RequestParam(value = "caseType",required = false) String caseType,HttpServletRequest request) {
		Root root = new Root();
		try {
			
			root.setStatus(Root.STATUS_OK);
			root.setData(userCaseService.queryUserCaseList(caseType.equals("0")?null:caseType));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return root;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteUserCaseByid", method = RequestMethod.POST)
	protected Object deleteUserCaseByid(@RequestParam(value = "id",required = false) Integer id) {
		Root root = new Root();
		try {
			if(userCaseService.deleteById(id)){
				root.setStatus(Root.STATUS_OK);
				root.setMsg("删除成功");
			}else{
				root.setStatus(Root.STATUS_FAIL);
				root.setMsg("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return root;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryUserCaseList", method = RequestMethod.POST)
	protected Object queryUserCaseList(
			@RequestParam(value = "searcTitle", required = false) String searcTitle,
			@RequestParam(value = "pageIndex") Integer pageIndex) {
		String userName = Strings.trimStringSearch(searcTitle);
		PageQuery pageQuery = new PageQuery();
		if (pageIndex != null) {
			pageQuery.setPageIndex(pageIndex);
		}
		PageResult<UserCase> users = userCaseService
				.getAllUserCaseList(searcTitle, pageQuery);
		Root root = Root.getRootOKAndSimpleMsg();
		root.setData(users);
		return root.toJsonString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUserCaseById", method = RequestMethod.POST)
	protected Object getUserCaseById(
			@RequestParam(value = "id") Integer id) {
		UserCase usercase = userCaseService.getById(id);
		Root root = Root.getRootOKAndSimpleMsg();
		root.setData(usercase);
		return root.toJsonString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/addUserCase", method = RequestMethod.POST)
	protected Object addUserCase(@RequestParam(value = "id",required=false) Integer id,
			@RequestParam(value = "caseType") String caseType,
			@RequestParam(value = "icon") String icon,@RequestParam(value = "title") String title,
			@RequestParam(value = "description") String description,@RequestParam(value = "brand") String brand,
			@RequestParam(value = "orderNum") Integer orderNum,@RequestParam(value = "displayFlag") Integer displayFlag,
			@RequestParam(value = "url") String url,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User crUser = (User) session.getAttribute("user");
		Root root = new Root();
		if(crUser==null){
			root.setStatus(Root.STATUS_FAIL);
			root.setMsg("登录超时,请重新登录!");
			return root.toJsonString();
		}
		UserCase userCase = new UserCase();
		userCase.setId(id);
		userCase.setCaseType(caseType);
		userCase.setIcon(icon);
		userCase.setTitle(title);
		userCase.setDescription(description);
		userCase.setBrand(brand);
		userCase.setOrderNum(orderNum);
		userCase.setDisplayFlag(displayFlag);
		userCase.setUrl(url);
		userCase.setCreateby(crUser.getLoginName());
		userCase.setUpdatedby(crUser.getLoginName());
		userCase.setCreationtime(new Date());
		userCase.setUpdatetime(new Date());
		userCaseService.addUser(userCase);
		root = Root.getRootOKAndSimpleMsg();
		return root.toJsonString();
	}
}