package com.wujialong.pojo;

import java.util.List;

//jsp中使用的分页数据
public class PageBean {
	private int current;
	private int maxPage;
	private int maxRow;
	//返回5行blog数据
	private List<Blog> blogs;
	static final int RowsPerPage=5;

	public int getRowsperpage() {
		return RowsPerPage;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getMaxRow() {
		return maxRow;
	}
	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}
	public List<Blog> getBlogs() {
		return blogs;
	}
	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	
	
	
}
