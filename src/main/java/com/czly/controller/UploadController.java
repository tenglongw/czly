package com.czly.controller;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.czly.common.util.Root;
import com.czly.entity.WebLog;
import com.czly.service.WebLogService;

@Controller
public class UploadController extends BaseController {

	static Logger logger = LogManager.getLogger(UploadController.class);
	@Resource
	private WebLogService webLogService;
	/**
	 * 上传文件
	 * 
	 * @param multipartFile
	 * @param path
	 * @param filename
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "formData", required = false) String formData,
			HttpServletRequest request) {
		logger.info("----------uploadFile----------parameters{fileName:"+file.getOriginalFilename()+",formData:"+formData+"}");
		String fileName = String.valueOf(System.currentTimeMillis())+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
		try {
			String path = request.getSession().getServletContext().getRealPath("upload");
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
		return "http://101.200.161.146/czly/upload/"+fileName;

	}

	@ResponseBody
	@RequestMapping(value = "/insertLog", method = RequestMethod.GET)
	protected Object insertLog(@RequestParam(value = "type",required = false) String type,HttpServletRequest request) {
		Root root = new Root();
		try {
			WebLog wl = new WebLog();
			wl.setType(type);
			wl.setCreatedby("wx");
			wl.setUpdatedby("wx");
			wl.setUpdatetime(new Date());
			wl.setCreationtime(new Date());
			webLogService.add(wl);
			root.setStatus(Root.STATUS_OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return root;
	} 
}