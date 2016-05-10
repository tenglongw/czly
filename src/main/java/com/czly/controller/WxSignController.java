package com.czly.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czly.common.util.HttpUtil;
import com.czly.common.util.Root;
import com.czly.common.util.Sign;

@Controller
@RequestMapping("/wx")
public class WxSignController extends BaseController {

	static Logger logger = LogManager.getLogger(WxSignController.class);
	
	@ResponseBody
	@RequestMapping(value = "/getAccessToken", method = RequestMethod.GET)
	protected Object getUserCase(@RequestParam(value = "appId") String appId,
			@RequestParam(value = "appSecret") String appSecret,
			@RequestParam(value = "t") String t) {
		logger.info("----------getAccessToken----------parameters{appid:"+appId+",secret:"+appSecret);
		JSONObject jsonObject = new JSONObject();
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret;
			jsonObject = JSONObject.fromObject(HttpUtil.get(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	/**获取签名信息
	 * @param appid
	 * @param secret
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSingInfo", method = RequestMethod.GET)
	protected Object getSingInfo(@RequestParam(value = "url") String url,HttpServletRequest request) {
		Root root = new Root();
		logger.info("----------getSingInfo--------- parameters={url:"+url+"}");
		try {
			//获取accesstoken
			String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+Sign.APPID+"&secret="+Sign.SECRET;
			JSONObject tokenMap = JSONObject.fromObject(HttpUtil.get(tokenUrl));  
			//获取apiticket
			String ticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+tokenMap.get("access_token")+"&type=jsapi";
			JSONObject tiketMap = JSONObject.fromObject(HttpUtil.get(ticketUrl)); 
			Map<String,String> signMap = Sign.sign((String) tiketMap.get("ticket"),url);
			logger.info("-----------return--getSingInfo------"+signMap.toString());
			root.setStatus(Root.STATUS_OK);
			root.setData(signMap);
		} catch (Exception e) {
			logger.error("getSingInfo error !!!",e);
		}
		return root;
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkSignature", method = RequestMethod.GET)
	protected Object checkSignature(@RequestParam(value = "signature") String signature,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "nonce") String nonce,
			@RequestParam(value = "echostr") String echostr,HttpServletRequest request) {
		Root root = new Root();
		try {
			if(Sign.checkSignature(signature,Sign.TOKEN, timestamp, nonce)){
				return echostr;
			}else{
				root.setStatus(Root.STATUS_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return root;
	}
	
}