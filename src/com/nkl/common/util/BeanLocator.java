/*
 * 文件名：BeanLocator.java  1.0.0  2012-11-1
 * 描　述：&功能说明
 * 作　者：聂昆仑
 * 版　权：创智信科
 */
package com.nkl.common.util;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLocator {
	private static final Logger logger = Logger.getLogger(BeanLocator.class);
	private static BeanLocator instance = null;
	private static final String DEFAULT_CONFIGURATION_FILENAME = "applicationContext.xml";
	private static String configurationFileName = null;
	private static ApplicationContext applicationContext = null;

	private BeanLocator() {
		if ((configurationFileName == null)
				|| (configurationFileName.length() == 0)) {
			configurationFileName = DEFAULT_CONFIGURATION_FILENAME;
		}
		applicationContext = new ClassPathXmlApplicationContext(
				new String[] { configurationFileName });
	}

	public static BeanLocator getInstance() {
		if (instance == null) {
			synchronized (logger) {
				if (instance == null) {
					instance = new BeanLocator();
				}
			}
		}
		return instance;
	}

	public static void setConfigurationFileName(String fileName) {
		configurationFileName = fileName;
	}

	public Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
}
