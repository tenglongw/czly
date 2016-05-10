package com.czly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.czly.common.util.DES;
import com.czly.common.util.Root;
import com.czly.common.util.lang.Strings;
import com.czly.common.util.page.PageQuery;
import com.czly.common.util.page.PageResult;
import com.czly.entity.User;
import com.czly.service.UserService;

@Controller
public class UserController extends BaseController {

	static Logger logger = LogManager.getLogger();
	@Resource
	private UserService userService;

	@RequestMapping("/login")
	public ModelAndView loginHtml(){
		return new ModelAndView("login");
	}
	/**
	 * 登录
	 * 
	 * @param username
	 * @param pwd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST)
	public String login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			HttpServletRequest request) throws Exception {
		Root root = null;
		User user = userService.getUserByName(username,
				DES.encrypt(password, DES.PASSWORD_CRYPT_KEY));
		if (null == user) {
			root = Root.getRootFail("用户名或密码错误，请重新输入！");
			return root.toJsonString();
		}
		//if (CommonConfig.ADMIN_NO == user.getType()) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			BaseController.user = user;
			root = Root.getRootOKAndSimpleMsg();
			root.setData(user);
	  //	}
        /*else {
			root = Root.getRootFail("该用户没有权限登录系统，请联系管理员分配权限！");
		}*/
		return root.toJsonString();
	}

	@ResponseBody
	@RequestMapping(value = "/getLoginUser", method = RequestMethod.GET)
	protected Object getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Root root = new Root();
		if (user != null) {
			root.setStatus(Root.STATUS_OK);
			root.setData(user);
		} else {
			root.setStatus(Root.STATUS_FAIL);
			root.setMsg("获取登录用户信息失败！");
		}
		return root;
	}

	@RequestMapping(value = "/consultation", method = RequestMethod.POST)
	public ModelAndView consultation(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("consultation");
		User user = userService.getUserByName(username,
				DES.encrypt(password, DES.PASSWORD_CRYPT_KEY));
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		return mv;
	}

	/**
	 * GET
	 */
	@ResponseBody
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public Object getUser(@PathVariable Long id) {
		logger.debug("user get invoked");
		User user = userService.getById(id.toString());
		Root root = new Root();
		if (user != null) {
			root.setStatus(Root.STATUS_OK);
			root.setData(user);
		} else {
			root.setStatus(Root.STATUS_FAIL);
			root.setMsg("查无此人");
		}
		return root;
	}

	/**
	 * PUT
	 */
	@ResponseBody
	@RequestMapping(value = "/user/{id}/{name}/{age}", method = RequestMethod.PUT)
	public Object putUser(@PathVariable("id") Long id,
			@PathVariable("name") String name, @PathVariable("age") Integer age) {
		Root root = new Root();
		return root;
	}

	/**
	 * DELETE
	 */
	@ResponseBody
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public Object deleteUser(@PathVariable Integer id) {
		Root root = new Root();
		if (userService.deleteById(id) ){
			root.setStatus(Root.STATUS_OK);
			root.setMsg("删除成功");
		} else {
			root.setStatus(Root.STATUS_FAIL);
			root.setMsg("删除失败");
		}
		return root;
	}

	/**
	 * POST
	 */
	@ResponseBody
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public Object postUser(@RequestParam(value = "name") String name,
			@RequestParam(value = "age") Integer age,
			@RequestParam(value = "birthday") String birthday) {
		Root root = new Root();

		return root;
	}
	
	/**
	 * 用户注销系统
	 */
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	public void logout(HttpServletRequest request){
		try{
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");
			if(user!=null){
				session.removeAttribute("user");
				session.invalidate();
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(""+e.getMessage());
			throw new RuntimeException("注销系统异常");
		}
	}
	
	
	/**
	 * 查询所有的用户
	 * 
	 * @param searchUserName
	 * @param pageIndex
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/list", method = RequestMethod.POST)
	public String getAllUserOrderById(
			@RequestParam(value = "searchUserName", required = false) String searchUserName,
			@RequestParam(value = "pageIndex") Integer pageIndex) {
		String userName = Strings.trimStringSearch(searchUserName);
		PageQuery pageQuery = new PageQuery();
		if (pageIndex != null) {
			pageQuery.setPageIndex(pageIndex);
		}
		PageResult<User> users = userService
				.getAllUserList(userName, pageQuery);
		Root root = Root.getRootOKAndSimpleMsg();
		root.setData(users);
		return root.toJsonString();
	}
	
	/**
	 * 校验登录用户是否被使用
	 * 
	 * @param searchUserName
	 * @param pageIndex
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkLoginName", method = RequestMethod.POST)
	public String checkLoginName(
			@RequestParam(value = "loginName", required = false) String loginName,
			@RequestParam(value = "userId") Integer userId) {
		loginName = Strings.trimStringSearch(loginName);
		boolean resultBoolean = userService.checkLoginName(loginName, userId);
		Root root = null;
		if (resultBoolean) {
			root = Root.getRootFailAndSimpleMsg();
		} else {
			root = Root.getRootOKAndSimpleMsg();
		}
		return root.toJsonString();
	}

	/**
	 * 获取用户
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserById", method = RequestMethod.POST)
	public String getUserById(@RequestParam("id") Integer id) throws Exception {
		User user = userService.getById(id);
		user.setPasswd(DES.decrypt(user.getPasswd(), DES.PASSWORD_CRYPT_KEY));
		Root root = Root.getRootOKAndSimpleMsg();
		root.setData(user);
		return root.toJsonString();
	}

	
	/**
	 * 添加、修改用户
	 * 
	 * @param userPwd
	 * @param userName
	 * @param loginName
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@RequestParam("userPwd") String userPwd,
			@RequestParam("userName") String userName,
			@RequestParam("loginName") String loginName,
			@RequestParam("userId") Integer userId,
			@RequestParam("type") Byte type,
			@RequestParam("hospitalId") Integer hospitalId,
			HttpServletRequest request)
			throws Exception {
		User user = userService.getById(userId);
		if (null == user) {
			user = new User();
		}
		HttpSession session = request.getSession();
		User crUser = (User) session.getAttribute("user");
		Root root = new Root();
		if(crUser==null){
			root.setStatus(Root.STATUS_FAIL);
			root.setMsg("登录超时,请重新登录!");
			return root.toJsonString();
		}
		boolean resultBoolean = userService.addUser(user, loginName, userName,
				DES.encrypt(userPwd, DES.PASSWORD_CRYPT_KEY),type,hospitalId,
				crUser.getUserName());
		if (resultBoolean) {
			root = Root.getRootOKAndSimpleMsg();
		} else {
			root = Root.getRootFail("添加失败");
		}
		return root.toJsonString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteUserAndUserHospital", method = RequestMethod.POST)
	public Object deleteUserAndUserHospital(@RequestParam("id") Integer id,
			@RequestParam("hospitalId") Integer hospitalId) {
		Root root = new Root();
		if (userService.deleteById(id,hospitalId) ){
			root.setStatus(Root.STATUS_OK);
			root.setMsg("删除成功");
		} else {
			root.setStatus(Root.STATUS_FAIL);
			root.setMsg("删除失败");
		}
		return root;
	}
}