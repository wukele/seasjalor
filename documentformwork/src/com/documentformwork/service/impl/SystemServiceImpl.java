package com.documentformwork.service.impl;

import net.sf.json.JSONArray;

import com.documentformwork.service.SystemService;
import com.documentformwork.system.SystemConfig;

public class SystemServiceImpl implements SystemService {
	public String getModuleByRoot() {
		// return SystemConfig.getUserRoot().getModules();
		return JSONArray.fromObject(SystemConfig.getUserRoot().getModules())
				.toString();
	}

}
