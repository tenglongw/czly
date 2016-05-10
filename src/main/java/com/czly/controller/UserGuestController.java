package com.czly.controller;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czly.common.util.Root;
import com.czly.entity.UserGuest;
import com.czly.service.UserGuestService;

@Controller
public class UserGuestController extends BaseController {

	static Logger logger = LogManager.getLogger();
	@Resource
	private UserGuestService userGuestService;

	
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
	@RequestMapping(value = "/addUserGuest", method = RequestMethod.POST)
	public String addUser(@RequestParam("username") String username,
			@RequestParam(value="phoneNumber",required = false) String phoneNumber,
			@RequestParam(value="email",required=false) String email,
			@RequestParam(value="message",required=false) String message)
			throws Exception {
		Root root = new Root();
		UserGuest userGuest = new UserGuest();
		userGuest.setUsername(username);
		userGuest.setPhoneNumber(phoneNumber);
		userGuest.setEmail(email);
		userGuest.setMessage(message);
		userGuestService.addUser(userGuest);
		root = Root.getRootOKAndSimpleMsg();
		return root.toJsonString();
	}
	
}