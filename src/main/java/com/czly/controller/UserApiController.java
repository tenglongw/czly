package com.czly.controller;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czly.common.util.HttpUtil;
import com.czly.common.util.Root;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/Api")
public class UserApiController extends BaseController {

	static Logger logger = LogManager.getLogger(UserApiController.class);
	static String POSTURL = "http://tangyidbweb.chinacloudsites.cn/API/API.asmx/Login";
	
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
	@RequestMapping(value = "/checkUser", method = RequestMethod.GET)
	public Object login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			HttpServletRequest request) throws Exception {
		logger.info("----------checkUser------------parameters{username:"
				+ username + ",password:" + password + "}");
		Document document = null;
		Map<String,Object> result = new HashMap<String,Object>();
		JSONObject jObj = new JSONObject();
		try {
			Map<String,String> params = new HashMap<String,String>();
			params.put("LoginName", username);
			params.put("LoginPassword", password);
			String response = HttpUtil.post(POSTURL, params);
			
			//创建SAXReader对象  
			SAXReader reader = new SAXReader();
			document = reader.read(new ByteArrayInputStream(response.getBytes("UTF-8")));
			Map<String,String> map = listNodes(document.getRootElement());
			if(!map.get("string").contains("登陆失败")){
				jObj = JSONObject.fromObject(map.get("string"));
				HttpSession session = request.getSession();
				Map<String,Object> userMap = new HashMap<String,Object>();
				userMap.put("username", username);
				userMap.put("userInfo", jObj.toString());
				session.setAttribute("userInfo", userMap);
				result.put("state", "0");
				result.put("userInfo", jObj.toString());
			}else{
				result.put("state", "1");
			}
		} catch (Exception e) {
			logger.error("checkUser error",e);
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "/checkLogin", method = RequestMethod.GET)
	public Object login(HttpServletRequest request) throws Exception {
		logger.info("----------checkLogin------------");
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			HttpSession session = request.getSession();
			Map<String,Object> userMap = (Map<String, Object>) session.getAttribute("userInfo");
			if(null != userMap){
				result.put("status", "login");
				result.put("user", userMap);
			}else{
				result.put("status", "logout");
			}
		} catch (Exception e) {
			logger.error("checkUser error",e);
		}
		return result;
	}
	 //遍历当前节点下的所有节点  
    public Map<String,String> listNodes(Element node){ 
    	Map<String,String> result = new HashMap<String,String>();
        //如果当前节点内容不为空，则输出  
        if(!(node.getTextTrim().equals(""))){  
            // System.out.println( node.getName() + "：" + node.getText());  
             result.put(node.getName(), node.getText());
        }  
        /*//同时迭代当前节点下面的所有子节点  
        //使用递归  
        Iterator<Element> iterator = node.elementIterator();  
        while(iterator.hasNext()){  
            Element e = iterator.next();  
            listNodes(e);  
        } */ 
        return result;
    }  
}