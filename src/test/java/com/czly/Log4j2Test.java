package com.czly;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.czly.common.util.HttpUtil;

public class Log4j2Test {
	
	Logger logger = LogManager.getLogger(Log4j2Test.class);

	@Test
	public void test() {
		
		try {
			String url = "http://10.102.1.61/cms//cms/website/NWMH/nbmh/index.jsp?siteId=10";
			System.out.println(HttpUtil.get(url));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
