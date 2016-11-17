<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑文章</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script type="text/javascript" charset="utf-8"
	src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="ueditor/ueditor.all.min.js">
	
</script>
<script type="text/javascript" charset="utf-8"
	src="ueditor/lang/zh-cn/zh-cn.js"></script>
<link rel=stylesheet href="ueditor/themes/default/css/ueditor.css">
	<link Rel="SHORTCUT ICON" href="images/turtle.jpg">
		<link type="text/css" rel="Stylesheet" href="css/blogmain.css" />
</head>

<body>
	<div id="wrap">

		<div class="head">
			<div class="user_info">
				<dl>
					<dt>
						<img src="images/5794.png" alt="magic_turtle" />
					</dt>
					<dd>
						<ul>
							<li class="user_name">magic_turtle</li>
							<li class="feed_link"><a href="index.html">个人主页</a>|<a
								href="manageBlog.html">博客管理</a></li>
						</ul>
					</dd>
				</dl>
			</div>
		</div>
		<form action="form.html" method="post" name="post">
			<p class="subtit">文章标题</p>
			<input type="text" id="title" style="width: 99.4%; height: 20px;"
				maxlength="100" name="txtTitle" value="${blog.title}" />
			<p class="subtit">文章内容</p>
			<div class="section">
				<script id="editor" name="txtContent" type="text/plain"
					style="width:100%;height:500px;">${blog.content}</script>
				<script type="text/javascript">
					var ue = UE.getEditor('editor');
				</script>
			</div>
			<div id="moreDiv">
				<p class="subtit">
					个人分类 <select id="selCat" onchange='selectType(this.value)'>
						<option value="">选择已有分类</option>
						<c:forEach items="${allTypes}" var="type">
							<option value="${type}">${type}&nbsp;&nbsp;&nbsp;&nbsp;</option>
						</c:forEach>
					</select>
				</p>
				<!-- 在此设置一个多选框 选择已有的类别-->
				<input type="text" id="txtTag" style="width: 60%; height: 20px;"
					maxlength="100" name="txtType" value="${blog.type}" />（多个分类之间用“,”分隔）
				<p id="p_desc" class="subtit">
					摘要：<span class="gray">（可自行编辑,或默认提取文章的前100字作为文章摘要）</span>
				</p>
				<div id="d_desc">
					<textarea rows="6" style="width: 99.4%;" name="txtSummary">${blog.summary}</textarea>
				</div>
			</div>
		</form>
		<div class="btn_area_1">
			<input id="btnPublish" type="button" class="input_btn_1" value="发表"
				title="保存并跳转" onclick="onSubmit()" /> <input id="btnDraft"
				type="button" class="input_btn_1" value="清空" title="清空页面所有内容"
				onclick="onClear()" />
		</div>

	</div>
</body>
</html>
<script type="text/javascript">
	function onSubmit() {
		var ue = UE.getEditor('editor');
		if (!ue.hasContents()) {
			alert("请输入内容");
		} else
			document.post.submit();

	}
	function onClear() {
		var ue = UE.getEditor('editor');
		ue.execCommand('cleardoc');
	}

	function selectType(value) {
		document.getElementById("txtTag").value = value;
		//$("#selCat").val(value); 这个咋无效类
	}
</script>

