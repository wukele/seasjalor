package com.xxx.common.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListBase<T extends Base> {
	/**
	 * 结果集 -1或者0
	 */
	private Integer resultRecord;
	/**
	 * 存储数据 List
	 */
	public List<T> dataList = new ArrayList<T>();
	/**
	 * 分页对象
	 */
	public Page page = new Page();
	/**
	 * 存储数据 Map
	 * 
	 * @SuppressWarnings("unchecked")
	 */
	@SuppressWarnings("unchecked")
	private Map map = new HashMap();

	@SuppressWarnings("unchecked")
	public Map getMap() {
		return map;
	}

	@SuppressWarnings("unchecked")
	public void setMap(Map map) {
		this.map = map;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getResultRecord() {
		return resultRecord;
	}

	public void setResultRecord(Integer resultRecord) {
		this.resultRecord = resultRecord;
	}

}
