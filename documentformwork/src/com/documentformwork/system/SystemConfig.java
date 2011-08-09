package com.documentformwork.system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.documentformwork.model.Module;
import com.documentformwork.model.UserRoot;

/**
 * 读取系统配置信息
 * 
 * @author style
 * 
 */
public class SystemConfig {
	private static Map<String, String> mapConfig = null;

	private static UserRoot userRoot;

	public static Map<String, String> getMapConfig() {
		return mapConfig;
	}

	public static void setMapConfig(Map<String, String> mapConfig) {
		SystemConfig.mapConfig = mapConfig;
	}

	public static UserRoot getUserRoot() {
		return userRoot;
	}

	public static void setUserRoot(UserRoot userRoot) {
		SystemConfig.userRoot = userRoot;
	}

	/**
	 * 初始化 基础模块配置项
	 * 
	 * @param prop
	 */
	public static synchronized void initApplicationConfig(Properties prop) {
		
		mapConfig = new HashMap<String, String>();
		Map<String, Module> moduleMap = new HashMap<String, Module>();
		userRoot = new UserRoot();
		String modulePrefix = "system.root.module.";
		for (Object key : prop.keySet()) {
			String keyStr = (String) key;
			if (keyStr.indexOf(modulePrefix) == 0
					&& keyStr.split("\\.").length == 5) {
				String moduleKey = keyStr.substring(modulePrefix.length(),
						keyStr.lastIndexOf("."));
				String moduleField = keyStr.substring(modulePrefix.length()
						+ (moduleKey + ".").length());
				Module module = moduleMap.get(moduleKey);
				if (module == null) {
					module = new Module();
					moduleMap.put(moduleKey, module);
				}
				if ("name".equals(moduleField)) {
					module.setName(prop.getProperty(keyStr));
				} else if ("url".equals(moduleField)) {
					module.setUrl(prop.getProperty(keyStr));
				} else if ("icon".equals(moduleField)) {
					module.setIcon(prop.getProperty(keyStr));
				}

			}

		}
		for (int i = 0; i < 999; ++i) {
			Module module = moduleMap.get("" + i);
			if (module != null) {
				userRoot.addModule(module);
			} else {
				break;
			}
		}

	}
}
