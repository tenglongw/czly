package com.czly.controller;

import net.sf.json.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czly.common.util.HttpUtil;

@Controller
@RequestMapping("/Menu")
public class MenuController extends BaseController {

	static Logger logger = LogManager.getLogger(MenuController.class);
	
	/**查询菜单
	 * @param token
	 * @param t
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getMenu", method = RequestMethod.GET)
	protected Object getMenuList(@RequestParam(value = "token") String token,
			@RequestParam(value = "t") String t) {
		logger.info("----------getAccessToken----------parameters{token:"+token);
		JSONObject jsonObject = new JSONObject();
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+token;
			jsonObject = JSONObject.fromObject(HttpUtil.get(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	/**删除菜单
	 * @param token
	 * @param t
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteMenu", method = RequestMethod.GET)
	protected Object deleteMenu(@RequestParam(value = "token") String token,
			@RequestParam(value = "t") String t) {
		logger.info("----------deleteMenu----------parameters{token:"+token);
		JSONObject jsonObject = new JSONObject();
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+token;
			jsonObject = JSONObject.fromObject(HttpUtil.get(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/createMenu", method = RequestMethod.GET)
	protected Object createMenu(@RequestParam(value = "token") String token,
			@RequestParam(value = "t") String t) {
		logger.info("----------deleteMenu----------parameters{token:"+token);
		JSONObject jsonObject = new JSONObject();
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+token;
			jsonObject = JSONObject.fromObject(HttpUtil.get(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}