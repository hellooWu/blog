package com.wujialong.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wujialong.mapper.BlogMapper;

import com.wujialong.pojo.Blog;
import com.wujialong.pojo.PageBean;

@Service
public class BlogService {
	
	@Autowired
	BlogMapper blogMapper;
	
	
	//更新点击量by id
	public void updateClickHitByID(Integer id){
		blogMapper.updateClickHitByID(id);
	}
	
	
	//查询是否存在id 存在返回true 不存在返回false
	public boolean ifExistsID(Integer id){
		if(blogMapper.ifExistsID(id)>0){
			return true;
		}
		else
			return false;
	}
	
	
	
	//获取最大ID
	public int getMaxID(){
		return blogMapper.getMaxID();
	}
	
	
	
	//以id删除博客
	public Boolean deleteByID(int id){
		try{
		blogMapper.deleteBlogByID(id);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public void deleteBlogType(int id){
		blogMapper.deleteBlogType(id);
	}
	
	
	//以id获取博客内容
	public Blog getBlogByID(int id){
		return blogMapper.getBlogByID(id);
	}
	
	
	//当前页的内容 limit 5
	public List<Blog> getArticleInformation(int formerBlogID){
		List<Blog> blogs=blogMapper.getArticleInformation(formerBlogID);
		return blogs;		
	}
	
	//返回管理页面的博客信息 limit 12(无分类)
	public List<Blog> getManagerInformation(int formerBlogID){
		List<Blog> blogs=blogMapper.getManagerInformation(formerBlogID);
		return blogs;		
	}
	
	//返回有分类的博客信息
	public List<Blog> getManagerInformationByType(HashMap<?, ?> map){
		return blogMapper.getManageInformationByType(map);
	}
	
	
	//返回管理博客页面信息(有分类)
	public PageBean getManagerBeanByType(Integer requestPage,String requestType){
		PageBean pageBean=new PageBean();
		List<Blog> blogs;
		int current=requestPage;
		int maxRow=blogMapper.getNumOfManageInformationByType(requestType);
		int maxPage;		
		int rowsPerPage=12;
		int formerBlogID;
		pageBean.setMaxRow(maxRow);
		if(maxRow%rowsPerPage==0){
			maxPage=maxRow/rowsPerPage;
			if(maxPage==0){
				maxPage=1;
			}
		}
		else{
			maxPage=maxRow/rowsPerPage+1;
		}		
		pageBean.setMaxPage(maxPage);
		if(requestPage>maxPage){
			current=maxPage;
		}
		pageBean.setCurrent(current);
		formerBlogID=(current-1)*rowsPerPage;//计算当前页的前一条记录的ID,方便从这条后查询(使用limit)
		HashMap<String, Comparable> map=new HashMap<String, Comparable>();
		map.put("formerBlogID", formerBlogID);
		map.put("type", requestType);
		blogs=blogMapper.getManageInformationByType(map);
		pageBean.setBlogs(blogs);		
		return pageBean;
	}
	
	
	//管理博客分页(全部无分类)
	public PageBean getManagerBean(int requestPage){
		PageBean pageBean=new PageBean();
		List<Blog> blogs;
		int current=requestPage;
		int maxRow=getMaxRows();
		int maxPage;		
		int rowsPerPage=12;
		int formerBlogID;
		pageBean.setMaxRow(maxRow);
		if(maxRow%rowsPerPage==0){
			maxPage=maxRow/rowsPerPage;
			if(maxPage==0){
				maxPage=1;
			}
		}
		else{
			maxPage=maxRow/rowsPerPage+1;
		}		
		pageBean.setMaxPage(maxPage);
		if(requestPage>maxPage){
			current=maxPage;
		}
		pageBean.setCurrent(current);
		formerBlogID=(current-1)*rowsPerPage;//计算当前页的前一条记录的ID,方便从这条后查询(使用limit)
		blogs=blogMapper.getManagerInformation(formerBlogID);
		pageBean.setBlogs(blogs);		
		return pageBean;		
	}
	
	
	//获得博客的总数目
	public int getMaxRows(){
		return blogMapper.getMaxRows();
	}
	
	//分页逻辑，输入请求的页数，返回PageBean,包括总页数，blog数据
	public PageBean getPageBean(int requestPage){
		PageBean pageBean=new PageBean();
		List<Blog> blogs;
		int current=requestPage;
		int maxRow=getMaxRows();
		int maxPage;		
		int rowsPerPage=pageBean.getRowsperpage();	
		int formerBlogID;
		pageBean.setMaxRow(maxRow);
		//计算页数
		if(maxRow%rowsPerPage==0){
			maxPage=maxRow/rowsPerPage;
			if(maxPage==0){
				maxPage=1;
			}
		}
		else{
			maxPage=maxRow/rowsPerPage+1;
		}		
		pageBean.setMaxPage(maxPage);
		
		if(requestPage>maxPage){
			current=maxPage;
		}
		
		pageBean.setCurrent(current);
		formerBlogID=(current-1)*rowsPerPage;//计算当前页的前一条记录的ID,方便从这条后查询(使用limit)
		blogs=blogMapper.getArticleInformation(formerBlogID);
		
		pageBean.setBlogs(blogs);
		
		return pageBean;
		
	}
	
	//返回最新的几篇文章
	public List<Blog> getLatestBlog(){
		List<Blog> list=blogMapper.getLatestBlog();
		return list;
	}
	
	//返回点击量最高的几篇文章
	public List<Blog> getTopClickedBlog(){
		return blogMapper.getTopClickedBlog();
	}
	
	//将表单传递来的博客内容插入数据库中
	public void insertBlog(HttpServletRequest request){
		Blog blog = new Blog();
		String txtTitle,txtContent,txtType,txtSummary;
		txtTitle=request.getParameter("txtTitle");
		txtContent=request.getParameter("txtContent");
		txtType=request.getParameter("txtType");
		txtSummary=request.getParameter("txtSummary");
		if(txtTitle==null){
			txtTitle="无标题";
		}
		if(txtContent==null){
			txtContent="";
		}
		if(txtType==null){
			txtType="";
		}
		if(txtSummary==null){
			if(txtContent.length()>100)
				txtSummary=txtContent.substring(0, 100);
			else
				txtSummary=txtContent.substring(0, txtContent.length());
		}
		blog.setTitle(txtTitle);
		blog.setSummary(txtSummary);
		blog.setContent(txtContent);
		blog.setType(txtType);
		blog.setReleaseDate(new Date());
		blogMapper.insertBlog(blog);
		blogMapper.insertBlogType(blog);
		request.setAttribute("id", blog.getId());
	}
	
	
}
