package com.wujialong.controller;




import java.text.SimpleDateFormat;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wujialong.pojo.Blog;
import com.wujialong.pojo.PageBean;
import com.wujialong.service.BlogService;
import com.wujialong.service.BlogTypeService;
//import com.wujialong.service.BloggerService;



//在视图解析器中设置了前缀和后缀
//@RequestMapping(value="")

@Controller
public class LoginController {
	
//	@Autowired
//	private BloggerService bloggerService;
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private BlogTypeService blogTypeService;
	
	
//	@RequestMapping(value="/login.html")
//	public String loginPage(){
////		System.out.println("进来了login");
//		return "login";
//	}
	
	
//	暂时取消登录功能
//	@RequestMapping(value="/login.html")
//	public ModelAndView loginCheck(String user_name,String user_password) throws Exception{
//		boolean valid=bloggerService.ifMatchUserName(user_name, user_password);
//		List<Blog> blogs=blogService.getArticleInformation(1);
//		System.out.println(user_name+"  "+user_password);
//		if(valid){
//			System.out.println("返回主界面");
//			ModelAndView mv=new ModelAndView();
//			mv.setViewName("index");
//			mv.addObject("blogs", blogs);
//			return mv;
//		}
//		else{
//			System.out.println("密码错误");
//			return new ModelAndView("login", "error", "用户名或密码错误");
//		}
//	}
	@RequestMapping(value="/login.html")
	public ModelAndView loginCheck(@RequestParam(value="error" ,required=false)String error) throws Exception{
		//正常登陆返回登录界面,登录错误则返回error错误.
		if(error==null){
			return new ModelAndView("login");
		}
		else{
			System.out.println("密码错误");
			return new ModelAndView("login", "error", "用户名或密码错误");
		}
	}
	
	
	//处理首页请求，包括页面跳转请求，若无page说明第一次请求page设为1，若为其他则封装pageBean返回
//	@RequestMapping(value="/index.html")
//	public ModelAndView loginCheck(Integer requestPage) throws Exception{
//		ModelAndView mv=new ModelAndView();
//		List<String> lastedBlogs=blogService.getLatestBlog();
//		List<String> topClickedBlog=blogService.getTopClickedBlog();
//		int requestpage;
//		PageBean pageBean;
//		if(requestPage==null){
//			requestpage=1;
//		}
//		else{
//			requestpage=requestPage;
//		}
//		pageBean=blogService.getPageBean(requestpage);
//		mv.addObject("topClickedBlog", topClickedBlog);
//		mv.addObject("lastedBlogs", lastedBlogs);
//		mv.addObject("pageBean", pageBean);
//		mv.setViewName("index");		
//		return mv;
//	}
//	
	
	//响应删除请求
	@RequestMapping(value="/delete.html")
	public String delete(Integer id) {
		if(id!=null&&blogService.ifExistsID(id)){
			blogService.deleteBlogType(id);
			if(!blogService.deleteByID(id))
				return "fail";
			
		}
		return "forward:/manageBlog.html";
	}
	
	
	
	//进入博客内容页面
	@RequestMapping(value="/detail.html")
	public ModelAndView detail(Integer id) {
		ModelAndView mv=new ModelAndView("blogDetail");
		if(id!=null&&blogService.ifExistsID(id)){
			blogService.updateClickHitByID(id);
			Blog blog=blogService.getBlogByID(id);
			mv.addObject("blog", blog);
			
		}
		List<Blog> lastedBlogs=blogService.getLatestBlog();
		List<Blog> topClickedBlog=blogService.getTopClickedBlog();
		mv.addObject("topClickedBlog", topClickedBlog);
		mv.addObject("lastedBlogs", lastedBlogs);
		return mv;
	}
	
	
	
	//进入编辑博客页面
	@RequestMapping(value="/editor.html")
	public ModelAndView editor(Integer id) {
		ModelAndView mv=new ModelAndView("editor");
		List<String> allTypes=blogTypeService.getAllTypes();
		if(id!=null&&blogService.ifExistsID(id)){
			Blog blog=blogService.getBlogByID(id);
			mv.addObject("blog", blog);
		}
		mv.addObject("allTypes", allTypes);
		return mv;
	}
	
	//提交博客至数据库
	@RequestMapping(value="/form.html")
	public ModelAndView editor(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv=new ModelAndView();
		try{
			System.out.println(request.getParameter("txtTitle")+" "+request.getParameter("txtContent")+" "+request.getParameter("txtSummary")+" "+request.getParameter("txtType"));
			blogService.insertBlog(request);
			mv.addObject("id", request.getAttribute("id"));
			mv.setViewName("success");
			return mv;
		}
		catch(Exception e){
			e.printStackTrace();
			mv.setViewName("fail");
			return mv;
		}
	}
	
	
	//对博客管理界面请求
	@RequestMapping(value="/manageBlog.html")
	public ModelAndView manageBlog(Integer requestPage,String requestType){
		ModelAndView mv=new ModelAndView();
		PageBean pageBean;
		List<String> allTypes=blogTypeService.getAllTypes();
		int requestpage;
		if(requestPage==null){
			requestpage=1;
		}
		else{
			requestpage=requestPage;
		}
		if(requestType==null||requestType.equals("all")){
			pageBean=blogService.getManagerBean(requestpage);
		}
		else{
			pageBean=blogService.getManagerBeanByType(requestpage, requestType);
		}
		mv.addObject("currentType", requestType);
		mv.addObject("allTypes", allTypes);
		mv.addObject("pageBean", pageBean);
		mv.setViewName("manageBlog");
		return mv;
	}
	
	
	
	//包含ajax的首页请求
	@RequestMapping(value="/index.html")
	public void  jumpPage(String requestPage,String page,HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("这是一个神奇的地址"+request.getSession().getServletContext().getRealPath("images"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		List<Blog> lastedBlogs=blogService.getLatestBlog();
		List<Blog> topClickedBlog=blogService.getTopClickedBlog();
		PageBean pageBean;
//		System.out.println(requestPage);
		if(requestPage==null){
			int futurePage;
			if(page==null){
				futurePage=1;
			}
			else{
				futurePage=Integer.parseInt(page);
				if(futurePage<=0){
					futurePage=1;
				}
			}
			pageBean=blogService.getPageBean(futurePage);			
			request.setAttribute("topClickedBlog", topClickedBlog);
			request.setAttribute("lastedBlogs", lastedBlogs);
			request.setAttribute("pageBean", pageBean);
			//如果把jsp文件放在web-inf下,这里直接写"index"会报找不到指定文件,应该是默认的URL视图解析器并木有工作
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}
		
		//requestPage不为空代表ajax请求
		else{
			response.setContentType("text/text;charset=GBK"); 
			StringBuffer html=new StringBuffer();
			pageBean=blogService.getPageBean(Integer.parseInt(requestPage));
			List<Blog> blogs=pageBean.getBlogs();
			//ajax请求处理,太长分发给下一个
			//不分发了,特么读不到对象,是字符不会转
			for(int i=0;i<blogs.size();i++){
				html.append("<h3>"+blogs.get(i).getTitle()+"</h3>");
				html.append("<figure><img src=\"images/001.png\"></figure>");
				html.append("<ul><p>"+blogs.get(i).getSummary()+"...</p>");
				html.append("<a  href=\"detail.html?id="+blogs.get(i).getId()+"\" target=\"_blank\" class=\"readmore\">阅读全文>></a></ul>");
				html.append("<p class=\"dateview\"><span>");
				html.append(formatter.format(blogs.get(i).getReleaseDate())+"</span><span>作者：");
				html.append(blogs.get(i).getAuthor()+"</span><span>分类：[");
				html.append(blogs.get(i).getType()+"]</span></p>");										
			}
			
			html.append(" <div id=\"pagging\" ><div class=\"right\">");
			if(pageBean.getCurrent()==1){
				html.append("<span class=\"disabled\" ><< 前一页</span>  ");
			}
			else if(pageBean.getCurrent()>1){
				if(pageBean.getCurrent()>7){
					html.append("<a onclick=\"jumpPage(1)\" class=\"n\">首页</a>");
				}
				html.append("<a onclick=\"jumpPage(");
				html.append((pageBean.getCurrent()-1)+")\" class=\"n\">< 前一页</a>");
			}
			int begin;
			int end;
			if(pageBean.getMaxPage()>=5){
				if(pageBean.getCurrent()>=3){
					begin=pageBean.getCurrent()-2;
					end=pageBean.getCurrent()+2;
					if(pageBean.getMaxPage()<end){
						end=pageBean.getMaxPage();
					}
				}
				else{
					begin=1;
					end=5;
				}
				
			}
			else{
				begin=1;
				end=pageBean.getMaxPage();
			}
			for(int i=begin;i<=end;i++){
				if(pageBean.getCurrent()==i){
					html.append("<strong><span class=\"pc\">"+i+"</span></strong>");
				}
				else{
					html.append("<a onclick=\"jumpPage("+i+")\"><span class=\"pc\">");
					html.append(i+"</span></a>");
				}
			}
			if(pageBean.getCurrent()>=pageBean.getMaxPage()){
				html.append("<span class=\"disabled\">后一页 >></span>");
			}
			else{
				html.append("<a onclick=\"jumpPage("+(pageBean.getCurrent()+1)+")\" class=\"n\">后一页 ></a>");
				if(pageBean.getCurrent()<pageBean.getMaxPage()-7){
					html.append("<a onclick=\"jumpPage(");
					html.append(pageBean.getMaxPage()+")\" class=\"n\">尾页</a>");
				}
			
			}
			html.append("</div></div>");
//			System.out.println(html.toString());
			response.getWriter().write(html.toString());
		}
		
		
		
		
		
	}
	
	
	
}
