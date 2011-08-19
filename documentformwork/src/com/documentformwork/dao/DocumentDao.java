package com.documentformwork.dao;

import java.util.List;

import com.documentformwork.entity.Document;

public interface DocumentDao extends BaseDao<Document, Integer> {

//	public List<Document> getAllList();
//
	public String getGridJson2(String query);

}
