package com.documentformwork.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.documentformwork.dao.BaseDao;
import com.documentformwork.dao.impl.BaseDaoServiceImpl;
import com.documentformwork.entity.Document;

/**
 * 工具类
 * 
 * @author style
 * 
 */
public class FormworkUtil {

	private static Map<String, String> typeMap = new HashMap<String, String>();
	static {
		typeMap.put("java.util.Date", "java.util.Date");
	}

	public static String getMethodByFieldName(String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);

	}

	public static void main(String[] args) {
		Class cl = Integer.class;

		Field[] f = cl.getFields();
		for (Field ff : f) {

			System.out.println(ff.getName());
		}

	}

	public static boolean isDateType(String type) {
		return typeMap.containsKey(type);
	}

}
