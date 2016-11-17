
function jumpPage(page){
	//跳转页面时,将跳转至的页面信息写入URL栈顶(注意是写即将跳转的页面信息,因为后退将回退到上一记录中而不是此次写入的)
	//例外是从首页到第二页时是ajax的第一步，也就是说第二页才开始ajax记录，
	//当从第二页回退回第一页时，url记录回到首页但页面未改变，因为出ajax范围，popstate已经不监听了
	//所以当首页进入第二页时，需要把首页的state也写入url栈中(记得state不能赋为空,否则popstate不监听)
	if(location.search==""){
		window.history.pushState({historyPage:1}, document.title, null);
	}
	var stateObj={historyPage:page};
//	alert("写入URL栈"+history.state.historyPage+"当前历史深度"+history.length);
	window.history.pushState(stateObj, document.title, "index.html?page="+page);
	ajaxPage(page);
}

window.addEventListener('popstate', function(e){
	//这时候的跳转不要再写历史了//history.state和e.state是一样的
	 var state = e.state;
		if (history.state){
			ajaxPage(state.historyPage);	
			 }	 
	}, false);

function ajaxPage(page){
	$.ajax({
		url:"index.html?requestPage="+page,
		dataType:"html",		
		success:function(data){
		$("#blogPage").html(data);
		$('body').animate({scrollTop: '350px'}, 200);	
		},
		error:function(){
		alert("后退error");
		}		
	});	
}
