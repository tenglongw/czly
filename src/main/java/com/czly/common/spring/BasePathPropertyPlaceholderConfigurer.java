package com.czly.common.spring;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.czly.common.util.lang.Files;
import com.czly.common.util.lang.Streams;

public class BasePathPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	
	private static Logger logger = LogManager.getLogger(BasePathPropertyPlaceholderConfigurer.class);
	
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
		throws BeansException {
		try {
			props.setProperty("issconfigpath", Files.getConfigPath());
			
			Properties p = new Properties();
			InputStream in = null;
			try {
				in = Streams.fileIn(Files.getConfigPath()+"/jdbc.properties");
				p.load(in);
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("loading rabbitMQ properties error!");
			}finally{
				if (in != null){
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			String username = p.getProperty("jdbc.username");
			props.setProperty("jdbc.username", username);
			
			String password = p.getProperty("jdbc.password");
			props.setProperty("jdbc.password", password);
			
			String driverClassName = p.getProperty("jdbc.driverClassName");
			props.setProperty("jdbc.driverClassName", driverClassName);
			
			String url = p.getProperty("jdbc.url");
			props.setProperty("jdbc.url", url);
			
//			String key = props.getProperty("jdbc.key");
//			if (username != null) {
//				props.setProperty("jdbc.username", DES.decrypt(username,key));
//			}
			
			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanInitializationException(e.getMessage());
		}
	}
}