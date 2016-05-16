package com.czly;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.czly.entity.SugarMedicalCase;

public class Log4j2Test {
	
	Logger logger = LogManager.getLogger(Log4j2Test.class);

	@Test
	public void test() {
		
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String value="2015-04-05";
			df.parse(value);
			SugarMedicalCase smc = new SugarMedicalCase();
			smc.setCreationtime(df.parse(value));
			System.out.println(smc.getCreationtime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
