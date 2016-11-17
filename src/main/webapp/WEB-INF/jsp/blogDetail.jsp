<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="utf-8">
<title>阅读</title>
<meta name="keywords" content="个人博客" />
<meta name="description" content="基于SSM框架的个人博客设计" />
<link href="css/base.css" rel="stylesheet">
<link href="css/blogDetail.css" rel="stylesheet">
<link Rel="SHORTCUT ICON" href="images/turtle.jpg">
<script src="js/jquery-3.1.0.js"></script>
</head>

<body>
<header>
  <div id="logo"><a href="/"></a></div>
  <nav class="topnav" id="topnav"><a href="index.html"><span>首页</span><span class="en">Protal</span></a>
  <a href="about.html"><span>关于我</span><span class="en">About</span></a>
  <a href="newlist.html"><span>生活剪影</span><span class="en">Life</span></a>
  <a href="knowledge.html"><span>学无止境</span><span class="en">Learn</span></a>
  <a href="book.html"><span>留言版</span><span class="en">Gustbook</span></a></nav>
</header>


<article>
  <h2 class="title_tj">
    <p>文章<span>阅读</span></p>
  </h2>
 	<div class="left" id="blogDetail">
    <div id="entry-hd">
    <h2 class="blog-title" itemprop="headline">
         ${blog.title}</h2>
    <div class="blog-meta">
    <p><span class="byline">分类:[${blog.type }]</span>
     <span class="posted-on"> 阅读量:${blog.clickHit }</span>
     <span id="releaseDate"><fmt:formatDate value="${blog.releaseDate}" pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></span>
     </p>
     </div></div>
     
	<div id="blog_content">${blog.content }</div>
</div>
  <aside class="right">
    <div class="weather"><iframe width="250" scrolling="no" height="60" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=1"></iframe></div>
    <div class="news"> 
    <h3>
      <p>最新<span>文章</span></p>
    </h3>
    <ul class="rank">   
    	<c:if test="${lastedBlogs!=null}">
    		<c:forEach items="${lastedBlogs}" var="blog">
    			<li><a href="detail.html?id=${blog.id}" title="${blog.title}" target="_blank">${blog.title}</a></li>
    		</c:forEach>
    	</c:if>
    </ul>
    <h3 class="ph">
      <p>点击<span>排行</span></p>
    </h3>
    <ul class="paih">
    
    	<c:if test="${topClickedBlog!=null}">
    		<c:forEach items="${topClickedBlog}" var="blog">
    			<li><a href="detail.html?id=${blog.id}" title="${blog.title}" target="_blank">${blog.title}</a></li>
    		</c:forEach>
    	</c:if>
    </ul>
    <h3 class="links">
      <p>友情<span>链接</span></p>
    </h3>
    <ul class="website">
    	<li></li>
      	<li></li>
     	<li>什么都没有..囧</li>
     	<li></li>
     	<li></li>
    </ul> 
    </div>  
    <!-- Baidu Button BEGIN -->
    <div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare"><a class="bds_tsina"></a><a class="bds_qzone"></a><a class="bds_tqq"></a><a class="bds_renren"></a><span class="bds_more"></span><a class="shareCount"></a></div>
    <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=6574585" ></script> 
    <script type="text/javascript" id="bdshell_js"></script> 
    <script type="text/javascript">
document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
</script> 
    <!-- Baidu Button END -->   
    <a href="/" class="weixin"> </a>
</aside>
</article>






<footer>
  <p>Design by Turtle && DanceSmile  <!-- <a href="/">网站统计</a> --></p>
</footer>
<script src="js/silder.js"></script>
</body>
</html>

<script type="text/javascript">

</script>

