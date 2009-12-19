package com.xxx.common.core;

/**
 * 分页对象
 * 
 * @author xiaohe
 * 
 */
public class Page {
	/**
	 * 当前页 默认为1
	 */
	private Integer currentPage = 1;
	/**
	 * 开始行
	 */
	private Integer startRow = 1;
	/**
	 * 结束行 默认取10
	 */
	private Integer endRow = 10;
	/**
	 * 页大小 默认为10
	 */
	private Integer pageSize = 10;
	/**
	 * 总记录数
	 */
	private Integer count;
	/**
	 * 总页数
	 */
	private Integer countPage;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		/**
		 * 计算开始行 =((当前页-1)*页大小)+1
		 */
		this.startRow = ((this.currentPage - 1) * 10) + 1;
	}

	public Integer getEndRow() {
		return endRow;
	}

	public void setEndRow(Integer endRow) {
		/**
		 * 计算结束行= (当前行 *页大小)
		 */
		this.endRow = this.currentPage * this.pageSize;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getCountPage() {
		return countPage;
	}

	public void setCountPage(Integer countPage) {
		/**
		 * 取余 如果 为0 则不需要去加一页 否则 则需要加1 计算总页数 =(总记录数%页大小==0)?总记录数/页大小:(总记录数/页大小)+1
		 */
		this.countPage = this.count % this.pageSize == 0 ? this.count
				/ this.pageSize : (this.count / this.pageSize) + 1;
	}

}
