package com.czly.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.multipart.MultipartFile;

import com.czly.common.util.HttpUtil;
import com.czly.common.util.Root;
import com.czly.common.util.lang.Strings;
import com.czly.common.util.page.PageQuery;
import com.czly.common.util.page.PageResult;
import com.czly.entity.SugarMedicalCase;
import com.czly.entity.User;
import com.czly.service.SugarMedicalCaseService;

import net.sf.json.JSONObject;

/**
 * @author wtl
 *
 */
@Controller
@RequestMapping("/sugarMedical")
public class SugarMedicalCaseController extends BaseController {

	static Logger logger = LogManager.getLogger(SugarMedicalCaseController.class);
	@Resource
	private SugarMedicalCaseService sugarMedicalCaseService;
	private static final String BASE_URL = "http://query.qoofan.com";
	private static final String SINGLE_URL = "/single";
	private static final String ARTICLE_CONTENT = "/article/content";
	private static final String TOKEN = "ede7f79bc7ca9149e0a0a4bb160a6080";

	@ResponseBody
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	protected Object queryCaseIndex() {
		Root root = new Root();
		try {
			root.setStatus(Root.STATUS_OK);
			root.setData(sugarMedicalCaseService.querySugarMedicalCaseIndex());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return root;
	}
	/**查询列表
	 * @param caseType
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSugarMedicalCaseList", method = RequestMethod.POST)
	protected Object getUserCaseList(@RequestParam(value = "caseType",required = false) String caseType,
			@RequestParam(value = "searchName",required = false) String searchName,
			HttpServletRequest request) {
		logger.info("----------getSugarMedicalCaseList------------parameters{caseType:"
				+ caseType + ",searchName:" + searchName + "}");
		Root root = new Root();
		try {
			root.setStatus(Root.STATUS_OK);
			List<SugarMedicalCase> smcList = sugarMedicalCaseService.querySugarMedicalCaseList(caseType,Strings.trimStringSearch(searchName));
			root.setData(smcList);
		} catch (Exception e) {
			logger.error("getSugarMedicalCaseList error !!!!",e);
		}
		return root;
	}
	
	/**
	 * 保存信息
	 * 
	 * @param multipartFile
	 * @param path
	 * @param filename
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "formData", required = false) String formData,
			HttpServletRequest request) {
		Root root = new Root();
		logger.info("----------/sugarMedical/uploadFile----------parameters{fileName:"+file.getOriginalFilename()+",formData:"+formData+"}");
		String fileName = String.valueOf(System.currentTimeMillis())+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		try {
			String path = request.getSession().getServletContext().getRealPath("upload/case");
			File targetFile = new File(path, fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	        //保存  
            file.transferTo(targetFile);  
		} catch (Exception e) {
			logger.error("文件上传失败", e);
		}
		root.setStatus(Root.STATUS_OK);
		root.setData("upload/case/"+fileName);
		return root;

	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteSugarMedicalCaseByid", method = RequestMethod.POST)
	protected Object deleteUserCaseByid(@RequestParam(value = "id",required = false) Integer id) {
		Root root = new Root();
		logger.info("----------deleteSugarMedicalCaseByid------------parameters{id:"
				+ id + "}");
		try {
			if(sugarMedicalCaseService.deleteById(id)){
				root.setStatus(Root.STATUS_OK);
				root.setMsg("删除成功");
			}else{
				root.setStatus(Root.STATUS_FAIL);
				root.setMsg("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除失败！！！",e);
		}
		return root;
	}
	
	@ResponseBody
	@RequestMapping(value = "/querySugarMedicalCasList", method = RequestMethod.POST)
	protected Object queryUserCaseList(
			@RequestParam(value = "searcTitle", required = false) String searcTitle,
			@RequestParam(value = "pageIndex") Integer pageIndex) {
		logger.info("----------querySugarMedicalCasList------------parameters{searcTitle:"
				+ searcTitle + ",pageIndex:"+pageIndex+"}");
		String userName = Strings.trimStringSearch(searcTitle);
		PageQuery pageQuery = new PageQuery();
		if (pageIndex != null) {
			pageQuery.setPageIndex(pageIndex);
		}
		PageResult<SugarMedicalCase> cases = sugarMedicalCaseService
				.getAllSugarMedicalCaseList(userName, pageQuery);
		Root root = Root.getRootOKAndSimpleMsg();
		root.setData(cases);
		return root.toJsonString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSugarMedicalCaseById", method = RequestMethod.POST)
	protected Object getUserCaseById(
			@RequestParam(value = "id") Integer id) {
		logger.info("----------getSugarMedicalCaseById------------parameters{id:"
				+ id + "}");
		SugarMedicalCase smcase = sugarMedicalCaseService.getById(id);
		Root root = Root.getRootOKAndSimpleMsg();
		root.setData(smcase);
		return root.toJsonString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/addSugarMedicalCase", method = RequestMethod.POST)
	protected Object addUserCase(@RequestParam(value = "id",required=false) Integer id,
			@RequestParam(value = "caseType") String caseType,
			@RequestParam(value = "icon") String icon,
			@RequestParam(value = "icon1") String icon1,@RequestParam(value = "title") String title,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "displayFlag") Integer displayFlag,
			@RequestParam(value = "url") String url,
			@RequestParam(value = "keyword") String keyword,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User crUser = (User) session.getAttribute("user");
		Root root = new Root();
		if(crUser==null){
			root.setStatus(Root.STATUS_FAIL);
			root.setMsg("登录超时,请重新登录!");
			return root.toJsonString();
		}
		SugarMedicalCase sugarMedicalCase = new SugarMedicalCase();
		sugarMedicalCase.setId(id);
		sugarMedicalCase.setCaseType(caseType);
		sugarMedicalCase.setIcon(icon);
		sugarMedicalCase.setIcon1(icon1);
		sugarMedicalCase.setTitle(title);
		sugarMedicalCase.setDescription(description);
		sugarMedicalCase.setReadNum(0);
		sugarMedicalCase.setDisplayFlag(displayFlag);
		sugarMedicalCase.setUrl(url);
		//sugarMedicalCase.setIsIndex(isIndex);
		sugarMedicalCase.setCreatedby(crUser.getLoginName());
		sugarMedicalCase.setUpdatedby(crUser.getLoginName());
		sugarMedicalCase.setKeyword(keyword);
		if(null == id){
			sugarMedicalCase.setCreationtime(new Date());
		}
		sugarMedicalCase.setUpdatetime(new Date());
		Map<String,String> param = new HashMap<String,String>();
		param.put("token", TOKEN);
		param.put("url", sugarMedicalCase.getUrl());
		//获取文章阅读量
		JSONObject singleObject = new JSONObject();
		singleObject = JSONObject.fromObject(HttpUtil.post(BASE_URL+SINGLE_URL, param));
		if(singleObject.get("msg").equals("success")){
			Map<String,Integer> dataMap = (Map<String, Integer>) singleObject.get("data");
			if(null != dataMap){
				Integer readNum = dataMap.get("read_num");
				sugarMedicalCase.setReadNum(readNum);
			}
		}
		//获取文章创建日期
		JSONObject contentObject = new JSONObject();
		contentObject = JSONObject.fromObject(HttpUtil.post(BASE_URL+ARTICLE_CONTENT, param));
		try {
			if(contentObject.get("msg").equals("success")){
				Map<String,String> dataMap = (Map<String, String>) contentObject.get("data");
				if(null != dataMap){
					String createTime = dataMap.get("create_time");
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					sugarMedicalCase.setCreationtime(df.parse(createTime));
				}
			}
		} catch (ParseException e) {
			logger.error("add sugarMedicalCase error",e);
		}
		sugarMedicalCaseService.addSugarMedicalCase(sugarMedicalCase);
		root = Root.getRootOKAndSimpleMsg();
		return root.toJsonString();
	}
}