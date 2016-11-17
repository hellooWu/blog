<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>博客管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link type="text/css" rel="Stylesheet" href="css/manageBlog.css" /> 
    <link Rel="SHORTCUT ICON" href="images/turtle.jpg"> 
    <script src="js/jquery-3.1.0.js"></script>   
</head>

<body>
<div id="wrap">

        <div class="head">
            <div class="user_info">
                <dl>
                    <dt><img src="images/5794.png" alt="magic_turtle" /></dt>
                    <dd>
                        <ul>
                            <li class="user_name">magic_turtle</li>
                            <li class="feed_link"><a href="index.html" target="_blank">个人主页</a>|<a href="manageBlog.html">博客管理</a></li>
                        </ul>
                    </dd>
                </dl>
            </div>
        </div>
        
<div id="type_post">
<div id="sel_div" class="h_status">
分类：
<select id="selCat" onchange='selectType(this.value)' >
<option value="all">全部&nbsp;&nbsp;&nbsp;&nbsp;</option>
<c:forEach items="${allTypes}" var="type">
<option value="${type}" ${type==currentType?'selected':''}>${type}&nbsp;&nbsp;&nbsp;&nbsp;</option>
</c:forEach>
</select>
</div>
<div class="tabs_header">
            <ul id="ul_tab" class="tabs"  style="width:100%">
                <li id="tab_postedit" style="display:none;"><a href="#"><span>发表文章</span></a></li>
                <li id="btn_postedit" class="write"><a href="editor.html" class="t_button" target="_blank">写新文章</a></li>
            </ul>
</div>
</div>

<table id="lstBox" cellspacing="0">
<tr><th class="tdleft">标题</th><th style="width:200px;">日期</th><th style="width:40px;">阅读量</th><th style="width:100px;">分类</th><th style="width:170px;">操作</th></tr>

<!-- 遍历blogs对象数组取值 -->
<c:forEach items="${pageBean.blogs}" var="blog">
<tr>
<td class="tdleft"><a href="detail.html?id=${blog.id}" target="_blank">${blog.title}</a></td>
<td><span class="gray"><fmt:formatDate value="${blog.releaseDate}" pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></span></td>
<td>${blog.clickHit}</td>
<td>${blog.type}</td>
<td><a href="editor.html?id=${blog.id}">编辑</a> | <a href="delete.html?id=${blog.id}" onclick="return confirmAct();" name="del">删除</a> </td>
</tr>
</c:forEach>
</table>
<!-- 分页控件 -->
 <div id="pagging" >
         <div class="right">
         <c:if test="${pageBean.current == 1}">
             <span class="disabled" ><< 前一页</span>        
         </c:if>
         <c:if test="${pageBean.current >1}">
         	<c:if test="${pageBean.current >7}">
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
         	<c:if test="${pageBean.current <pageBean.maxPage-7}">
 	      		<a  onclick="jumpPage(${pageBean.maxPage})" class="n">尾页</a>
         	</c:if>
         </c:if>
         
         </div>
 	</div> 


</div>
</body>
</html>

<script type="text/javascript">
function jumpPage(page){
	var select=$("#selCat").val();
	if(select=="all"){
		location.href="manageBlog.html?requestPage="+page;
	}
	else{
		location.href="manageBlog.html?requestPage="+page+"&requestType="+select;
	}
	
}


function confirmAct() 
{ 
    if(confirm('确定要删除这篇博客吗?')) 
    { 
        return true; 
    } 
    return false; 
}

function selectType(value)
{
	location.href="manageBlog.html?requestType="+value;
}









</script>