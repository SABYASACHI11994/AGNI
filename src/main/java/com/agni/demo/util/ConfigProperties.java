package com.agni.demo.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Configuration 
@PropertySource("classpath:/application.properties")
@Component
public class ConfigProperties {
	private static Properties properties;
	private static Logger logger = LoggerFactory.getLogger(ConfigProperties.class);

	private static Environment environment;

	public ConfigProperties() {
		initalizeProperties();
	}

	private static void initalizeProperties() {
		if (properties == null) {
			properties = new Properties();

			// FileInputStream fis;
			try {
				// this.getClass().getResourceAsStream(

				InputStream fis = ConfigProperties.class.getResourceAsStream("/application.properties");
				properties.load(fis);
				// properties.
				// fis.close();
			} catch (IOException e) {
				logger.error("Loading of config file failed with error " + e.getLocalizedMessage(), e);
			}
		}
	}

	@Autowired
	@Required
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public static String getPropertyByName(String propertyName) {
		String result = null;
		try {
			if (properties == null) {
				initalizeProperties();
			}
			result = properties.getProperty(propertyName).trim();
		} catch (Exception e) {
			logger.error(propertyName + " retrival failed.", e);
		}
		return result;
	}

	private static Class<ConfigProperties> configProperties = ConfigProperties.class;
}
