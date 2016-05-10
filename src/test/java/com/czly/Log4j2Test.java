package com.czly;

import static org.junit.Assert.*;

import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Test {
	
	Logger logger = LogManager.getLogger(Log4j2Test.class);

	@Test
	public void test() {
		logger.debug("test");
	}

}
