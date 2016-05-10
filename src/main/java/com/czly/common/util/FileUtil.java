package com.czly.common.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	public static String uploadIcon(MultipartFile myfiles,String path,String newpicnam) throws IOException{
            if(myfiles.isEmpty()){  
                System.out.println("文件未上传");  
            }else{  
                System.out.println("文件长度: " + myfiles.getSize());  
                System.out.println("文件类型: " + myfiles.getContentType());  
                System.out.println("文件名称: " + myfiles.getName());  
                System.out.println("文件原名: " + myfiles.getOriginalFilename());  
                System.out.println("========================================");  
                //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中  
                String realPath = path;  
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
                FileUtils.copyInputStreamToFile(myfiles.getInputStream(), new File(realPath, newpicnam));  
            }  
		return newpicnam; 
	}
	
	public static boolean deleteIcon(String path,String newpicnam){
		return FileUtils.deleteQuietly(new File(path, newpicnam));
	}
}
