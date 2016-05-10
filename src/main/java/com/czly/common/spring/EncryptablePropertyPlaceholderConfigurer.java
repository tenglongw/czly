package com.czly.common.spring;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.czly.common.util.DES;

public class EncryptablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
		throws BeansException {
		try {
			String key = props.getProperty("jdbc.key");
			String username = props.getProperty("jdbc.username");
			if (username != null) {
				props.setProperty("jdbc.username", DES.decrypt(username,key));
			}
			
			String password = props.getProperty("jdbc.password");
			if (password != null) {
				props.setProperty("jdbc.password", DES.decrypt(password,key));
			}
			
			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanInitializationException(e.getMessage());
		}
	}
}