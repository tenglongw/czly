package com.czly.controller;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;

import com.czly.common.util.Root;
import com.czly.common.util.lang.Strings;
import com.czly.common.util.page.PageQuery;
import com.czly.common.util.page.PageResult;
import com.czly.entity.Banner;
import com.czly.entity.User;
import com.czly.service.BannerService;

/**
 * @author wtl
 *
 */
@Controller
@RequestMapping("/banner")
public class BannerController extends BaseController {

	static Logger logger = LogManager.getLogger(BannerController.class);
	@Resource
	private BannerService bannerService;

	/**查询列表
	 * @param caseType
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getBannerList", method = RequestMethod.GET)
	protected Object getUserCaseList(@RequestParam(value = "type",required = false) String type) {
		Root root = new Root();
		try {
			root.setStatus(Root.STATUS_OK);
			root.setData(bannerService.queryBannerList(type));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		logger.info("----------uploadFile----------parameters{fileName:"+file.getOriginalFilename()+",formData:"+formData+"}");
		String fileName = String.valueOf(System.currentTimeMillis())+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
		try {
			String path = request.getSession().getServletContext().getRealPath("upload/banner");
			File targetFile = new File(path, fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	        //保存  
            file.transferTo(targetFile);  
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("文件上传失败", e);
		}
		root.setStatus(Root.STATUS_OK);
		root.setData("upload/banner/"+fileName);
		return root;

	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteBannerByid", method = RequestMethod.POST)
	protected Object deleteUserCaseByid(@RequestParam(value = "id",required = false) Integer id) {
		Root root = new Root();
		try {
			if(bannerService.deleteById(id)){
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
	@RequestMapping(value = "/queryBannerList", method = RequestMethod.POST)
	protected Object queryUserCaseList(
			@RequestParam(value = "searcTitle", required = false) String searcTitle,
			@RequestParam(value = "pageIndex") Integer pageIndex) {
		String userName = Strings.trimStringSearch(searcTitle);
		PageQuery pageQuery = new PageQuery();
		if (pageIndex != null) {
			pageQuery.setPageIndex(pageIndex);
		}
		PageResult<Banner> cases = bannerService
				.getAllBannerList(userName, pageQuery);
		Root root = Root.getRootOKAndSimpleMsg();
		root.setData(cases);
		return root.toJsonString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/getBannerById", method = RequestMethod.POST)
	protected Object getUserCaseById(
			@RequestParam(value = "id") Integer id) {
		Banner banner = bannerService.getById(id);
		Root root = Root.getRootOKAndSimpleMsg();
		root.setData(banner);
		return root.toJsonString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/addBanner", method = RequestMethod.POST)
	protected Object addUserCase(@RequestParam(value = "id",required=false) Integer id,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "displayFlag") Integer displayFlag,
			@RequestParam(value = "type") String type,
			@RequestParam(value = "path") String path,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User crUser = (User) session.getAttribute("user");
		Root root = new Root();
		if(crUser==null){
			root.setStatus(Root.STATUS_FAIL);
			root.setMsg("登录超时,请重新登录!");
			return root.toJsonString();
		}
		Banner banner = new Banner();
		banner.setId(id);
		banner.setTitle(title);
		banner.setDescription(description);
		banner.setDisplayFlag(displayFlag);
		banner.setType(type);
		banner.setPath(path);
		banner.setCreatedby(crUser.getLoginName());
		banner.setUpdatedby(crUser.getLoginName());
		if(null == id){
			banner.setCreationtime(new Date());
		}
		banner.setUpdatetime(new Date());
		bannerService.addBanner(banner);
		root = Root.getRootOKAndSimpleMsg();
		return root.toJsonString();
	}
}