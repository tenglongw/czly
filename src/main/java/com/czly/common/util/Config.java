package com.czly.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Config implements ServletContextListener {
	

	private static  Properties props1 = new Properties();
	
	public static void init(){
		
		InputStream db = null;
		
		try {
			
			db = Config.class.getClassLoader().getResourceAsStream("common.properties");
			
			props1.load(db);
			
		} catch ( FileNotFoundException e) {
			
			e.printStackTrace();
			
		}catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}
	
	public static String getkey(String key){
		
		String value = null;
		
		value = props1.getProperty(key);
		
		return value;
	}
	
	public static Properties getProperties(){
		return props1;
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		init();
	}
}
