package com.documentformwork.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.SystemUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.documentformwork.dao.DocumentDao;
import com.documentformwork.entity.Document;
import com.documentformwork.util.FormworkUtil;

@SuppressWarnings("unchecked")
public class DocumentServiceImpl extends BaseDaoServiceImpl<Document, Integer>
		implements DocumentDao {
	// @Override
	// public List<Document> getAllList() {
	// String sql = "select document_ID,document_name,type,size from document";
	// return (List<Document>) this.findBySQL(sql);
	// }
	//
	public String getGridJson2(String query) {
		List list = this.findList(query, null, 0, 100);
		JSONArray array = new JSONArray();
		try {
			if (list != null && list.size() > 0) {
				Class cls = list.get(0).getClass();
				// 获取所有的字段
				Field fields[] = cls.getDeclaredFields();
				System.out.println("字段：" + fields.length);
				for (Iterator it = list.iterator(); it.hasNext();) {
					Object obj = it.next();
					JSONObject json = new JSONObject();
					for (Field f : fields) {
						Method method = cls.getDeclaredMethod(FormworkUtil
								.getMethodByFieldName(f.getName()), null);
						Object value = method.invoke(obj, null);
						// 判断是否为日期格式
						if (FormworkUtil.isDateType(f.getType().getName())) {
							DateFormat df = null;
							df = new SimpleDateFormat("yyyy-MM-dd");
							value = df.format(value);
						}
						json.put(f.getName(), value);
					}
					array.add(json);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getGridJson error detail :" + e.getMessage());
		}
		JSONObject obj = new JSONObject();
		obj.put("root", array);
		return obj.toString();

	}

}
