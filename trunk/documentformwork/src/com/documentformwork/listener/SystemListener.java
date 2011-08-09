package com.documentformwork.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.documentformwork.system.SystemConfig;

public class SystemListener implements ServletContextListener {
	static final Log logger = LogFactory.getLog(SystemListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {

	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		logger.info("start systemListener class");
		File file = new File(context.getServletContext().getRealPath(
				"/WEB-INF/config/system-config.properties"));
		try {
			InputStream inputStream = new FileInputStream(file);
			Properties p = new Properties();
			p.load(inputStream);
			SystemConfig.initApplicationConfig(p);
			inputStream.close();
		} catch (FileNotFoundException e) {
			logger.error("file system-config.properties not found");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("load file system-config.properties fail");
			e.printStackTrace();
		}
		logger.info("finish systemListener class");

	}
}
