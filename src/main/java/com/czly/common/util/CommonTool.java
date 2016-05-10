package com.czly.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonTool {
	private static Logger logger = LogManager.getLogger(CommonTool.class);
	public static String uuid(){
		return UUID.randomUUID().toString();
	}
	
	public static boolean isEndWithUploadFiles(String fileName){
		if (fileName.endsWith(".pdf")){
			//pdf
			return true;
		}
		
		if (fileName.endsWith(".doc") || fileName.endsWith(".docx")){
			//word
			return true;
		}
		
		return false;
	}
	
	//获取MAC地址的方法
	public static String getMACAddress(){
    	InetAddress ia;
		try {
			 ia = InetAddress.getLocalHost();
			 if (ia != null){
				//获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
				 byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
				 //下面代码是把mac地址拼装成String
			     StringBuffer sb = new StringBuffer();
			        
			     if (mac != null){
			    	 for(int i=0;i<mac.length;i++){
			    		 if(i!=0){
			    			 sb.append("-");
				         }
				         //mac[i] & 0xFF 是为了把byte转化为正整数
				         String s = Integer.toHexString(mac[i] & 0xFF);
				         sb.append(s.length()==1?0+s:s);
					 }
			    	 //把字符串所有小写字母改为大写成为正规的mac地址并返回
			    	 return sb.toString().toUpperCase();  
			     }else{
			    	return "00-00-00-00-00-00";
			     }
			 }
			 //其他情况
			 logger.warn("无法获取到电脑的MAC地址");
			 return "00-00-00-00-00-00";
		} catch (UnknownHostException e) {
			 //其他情况
			 logger.warn("无法获取到电脑的MAC地址");
			 return "00-00-00-00-00-00";
		} catch (SocketException e) {
			 //其他情况
			 logger.warn("无法获取到电脑的MAC地址");
			 return "00-00-00-00-00-00";
		}
    }
	
	public static void main(String[] args) {
		System.out.println(CommonTool.getMACAddress());
	}
}