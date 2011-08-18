package com.documentformwork.dao.impl;

import java.util.List;
import com.documentformwork.dao.DocumentDao;
import com.documentformwork.entity.Document;
@SuppressWarnings("unchecked")
public class DocumentServiceImpl extends BaseDaoServiceImpl<Document, Integer>
		implements DocumentDao {
	//听说太理想的恋爱总不可接触
	//听说太理想的一切总不可接触
	@Override
	public List<Document> getAllList() {
		String sql = "select document_ID,document_name,type,size from document";
		return (List<Document>) this.findBySQL(sql);
	}

}
