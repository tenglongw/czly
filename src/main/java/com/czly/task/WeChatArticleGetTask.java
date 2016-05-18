package com.czly.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.czly.common.util.HttpUtil;
import com.czly.entity.SugarMedicalCase;
import com.czly.service.SugarMedicalCaseService;

import net.sf.json.JSONObject;

/**
 * 每日定时获取微信文章阅读量
 */

@Controller
public class WeChatArticleGetTask {
	@Resource
	private SugarMedicalCaseService sugarMedicalCaseService;
	
	private static final String BASE_URL = "http://query.qoofan.com";
	private static final String SINGLE_URL = "/single";
	private static final String ARTICLE_CONTENT = "/article/content";
	private static final String TOKEN = "ede7f79bc7ca9149e0a0a4bb160a6080";
	public void run() {
		List<SugarMedicalCase> smcList = sugarMedicalCaseService.querySugarMedicalCaseList(null,null);
		Map<String,String> param = new HashMap<String,String>();
		param.put("token", TOKEN);
		List <SugarMedicalCase> tempList = new ArrayList<SugarMedicalCase>();
		try {
			for(SugarMedicalCase smc : smcList){
				param.put("url", smc.getUrl());
				//获取文章阅读量
				JSONObject singleObject = new JSONObject();
				singleObject = JSONObject.fromObject(HttpUtil.post(BASE_URL+SINGLE_URL, param));
				if(singleObject.get("msg").equals("success")){
					Map<String,Integer> dataMap = (Map<String, Integer>) singleObject.get("data");
					if(null != dataMap){
						Integer readNum = dataMap.get("read_num");
						smc.setReadNum(readNum);
					}
				}
				//获取文章创建日期
				/*JSONObject contentObject = new JSONObject();
				contentObject = JSONObject.fromObject(HttpUtil.post(BASE_URL+ARTICLE_CONTENT, param));
				if(contentObject.get("msg").equals("success")){
					Map<String,String> dataMap = (Map<String, String>) contentObject.get("data");
					if(null != dataMap){
						String createTime = dataMap.get("create_time");
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						smc.setCreationtime(df.parse(createTime));
					}
				}*/
//				System.out.println("read_num"+smc.getReadNum()+"create_time"+smc.getCreationtime());
				sugarMedicalCaseService.update(smc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}