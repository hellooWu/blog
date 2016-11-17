<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="utf-8">
<title>乌龟的个人博客</title>
<meta name="keywords" content="个人博客" />
<meta name="description" content="基于SSM框架的个人博客设计" />
<link href="css/base.css" rel="stylesheet">
<link href="css/index.css" rel="stylesheet">
<link Rel="SHORTCUT ICON" href="images/turtle.jpg">
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
<script src="js/jquery-3.1.0.js"></script>
</head>
<body>
<header>
  <div id="logo"><a href="/"></a></div>
  <nav class="topnav" id="topnav"><a href="index.html"><span>首页</span><span class="en">Protal</span></a>
  <a href="album.html"><span>相册</span><span class="en">Album</span></a>
  <a href="dairy.html"><span>日记</span><span class="en">Diary</span></a>
  <a href="about.html"><span>关于我</span><span class="en">About</span></a>
  <a href="book.html"><span>留言版</span><span class="en">Gustbook</span></a></nav>
</header>
<div class="banner">
  <section class="box">
    <ul class="texts">
<!--       <p>打了死结的青春，捆死一颗苍白绝望的灵魂。</p>
      <p>为自己掘一个坟墓来葬心，红尘一梦，不再追寻。</p>
      <p>加了锁的青春，不会再因谁而推开心门。</p> -->
      <p>玩具是你的</p>
      <p>&nbsp;&nbsp;零食是你的</p>
      <p>你是我的</p>
    </ul>
    <div class="avatar"><a href="#"><span>乌龟</span></a> </div>
  </section>
</div>

<article>
  <h2 class="title_tj">
    <p>文章<span>概览</span></p>
  </h2>
  <div class="bloglist left" id="blogPage">
  <!-- 遍历blogs对象数组取值 -->
  <!-- ,每页五行最好看 -->
  	<c:forEach items="${pageBean.blogs}" var="blog">
    <h3>${blog.title}</h3>
    <figure><img src="images/001.png"></figure>
    <ul>
      <p>${blog.summary}...</p>
      <a  href="detail.html?id=${blog.id}" target="_blank" class="readmore">阅读全文>></a>
    </ul>
    <p class="dateview"><span><fmt:formatDate value="${blog.releaseDate}" pattern="yyyy-MM-dd"></fmt:formatDate></span><span>作者：${blog.author}</span><span>分类：[${blog.type}]</span></p>   
  </c:forEach>


<!-- 页面跳转控件 -->
 <div id="pagging" >
         <div class="left" style="font-size:10px">共${pageBean.maxRow}条记录</div>
         <div class="right">
         <c:if test="${pageBean.current == 1}">
             <span class="disabled" ><< 前一页</span>        
         </c:if>
         <c:if test="${pageBean.current >1}">
         	<c:if test="${pageBean.current >6}">
         		<a onclick="jumpPage(1)" class="n">首页</a>
         	</c:if>
            <a   onclick="jumpPage(${pageBean.current-1})"  class="n">< 前一页</a>
         </c:if>
       
         <!-- 如果总页数大于5,则能显示至少5个跳转按钮 -->
         <c:if test="${pageBean.maxPage>=5}">
         	<!-- 当前页面大于3时,把当前页面居中显示 -->
         	<c:if test="${pageBean.current >=3}">
         		<c:set var="begin" value="${pageBean.current-2}"/>
         		<c:set var="end" value="${pageBean.current+2}"/>
         			<!-- 若最后一个按钮值小于总页数了,把最后一个按钮改为总页数 -->
         			<c:if test="${pageBean.maxPage<end}">
         				<c:set var="end" value="${pageBean.maxPage}"/>
         			</c:if>
         	</c:if>
         	<!-- 当前页面小于3时,直接显示1到5-->
         	<c:if test="${pageBean.current <3}">
         		<c:set var="begin" value="1"/>
         		<c:set var="end" value="5"/>
         	</c:if>
         </c:if>
         <!-- 如果总页数小于5,则从1显示到总页数个按钮-->
         <c:if test="${pageBean.maxPage<5}">
         	<c:set var="begin" value="1"/>
         	<c:set var="end" value="${pageBean.maxPage}"/>
         </c:if>
         
         <c:forEach begin="${begin}" end="${end}" varStatus="vs" >
        	 <c:if test="${pageBean.current==vs.index}">
            	 <strong>
            		<span class="pc">${vs.index}</span>  
            	 </strong>     
        	 </c:if>
        	 <c:if test="${pageBean.current != vs.index}">
             	 <a    onclick="jumpPage(${vs.index})" ><span class="pc">${vs.index}</span></a>
         	</c:if>
         </c:forEach>
                  
         <!-- 当前页面大于等于最大页数时,后一页按钮失效 -->
         <c:if test="${pageBean.current >= pageBean.maxPage}">
             <span class="disabled">后一页 >></span>        
         </c:if>
         <!-- 当前页面小于最大页数时,后一页按钮生效-->  
         <c:if test="${pageBean.current < pageBean.maxPage}">
         	<a onclick="jumpPage(${pageBean.current+1})"  class="n">后一页 ></a>
         	<c:if test="${pageBean.current <pageBean.maxPage-6}">
         		<a  onclick="jumpPage(${pageBean.maxPage})" class="n">尾页</a>
         	</c:if>
         </c:if>
         
         </div>
 	</div> 

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
<script src="js/silder.js" type="text/javascript"></script>
<script type="text/javascript" src="js/pageListener.js"></script>
</body>
</html>



