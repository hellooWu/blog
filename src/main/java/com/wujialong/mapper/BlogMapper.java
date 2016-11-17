package com.wujialong.mapper;

import java.util.HashMap;
import java.util.List;

import com.wujialong.pojo.Blog;

public interface BlogMapper {
	
	public List<Blog> getArticleInformation(int formerBlogID);
	public int getMaxRows();
	public List<Blog> getLatestBlog();
	public List<Blog> getTopClickedBlog();
	public void insertBlog(Blog blog);
	public void insertBlogType(Blog blog);
	public void deleteBlogType(int id);
	public List<Blog> getManagerInformation(int formerBlogID);
	public Blog getBlogByID(int id);
	public void deleteBlogByID(int id);
	public int getMaxID();
	public int ifExistsID(int id);
	public void updateClickHitByID(int id);
	public List<Blog> getManageInformationByType(HashMap<?, ?> map);//map中需要传入int formerBlogID 类型 String type
	public int getNumOfManageInformationByType(String type);//查询分类的管理博客总数
	

}
