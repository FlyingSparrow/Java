(function($){ 
/*
 * 基于jquery文章列表分页控件
 * pageNumber:当前页
 * pageSize:页容量
 * params:请求参数
 * url:请求地址
 * dataContainer:数据容器
 * pageContainer:分页组件容器
 * 
 * */
$.fn.getPageList = function(pageNumber,pageSize,pageRange,url,dataContainer,pageContainer){
	var params = "";
	if($('#formParam').length > 0){
		params = $('#formParam').serialize();
	}
	$.getJSON(url, {'pageNumber':pageNumber,'pageSize':pageSize,'params':params}, function(json){
		if(json.status != 0){
			//alert(json.msg);
			$(pageContainer).html("");
			$(dataContainer).html("<div class='no-data'><span class='glyphicon glyphicon-info-sign'><font>&nbsp;暂无数据</font></span></div>");
			return;
		}
		var data = json.data;
		var dataHtml = "";
		if(data.rows.length == 0){
			$(pageContainer).html("");
			$(dataContainer).html("<div class='no-data'><span class='glyphicon glyphicon-info-sign'><font>&nbsp;暂无数据</font></span></div>");
			return;
		}
		for(var i = 0 ;i<data.rows.length;i++){
			var item = data.rows[i];
			var articleHtml = "<div class='articleBody-section'>";
			if(item.title != null && "" != $.trim(item.title)){
				articleHtml += "<h3 id="+item.ossLink1+" class='articleTitle'>"+item.title+"</h3>";
				articleHtml += "<p>"+item.content+"</p>";
            }else{
            	var sumContent = item.content.length > 30 ? item.content.substring(0,30) : item.content;
            	articleHtml += "<h3 id="+item.ossLink1+" class='articleTitle'>"+sumContent+"</h3>";
            	articleHtml += "<p>"+item.content+"</p>";
            }
            articleHtml += "<p><span>站点 : <a href='javascript:;'>"+item.source+"</a></span>";
            articleHtml += "<span>作者 : <a href='javascript:;'>"+item.author+"</a></span>";
            articleHtml += "<span>支持数 : <a href='javascript:;'>"+item.supportCount+"</a></span>";
            articleHtml += "<span>评论数 : <a href='javascript:;'>"+item.replyCount+"</a></span></p></div>";
            dataHtml += articleHtml;
		}
		$(dataContainer).html(dataHtml);
		var pageParam = pageSize + ','+ pageRange+',"'+url+'",'+'"'+dataContainer+'"'+',"'+pageContainer+'"';
//		console.log(decodeURIComponent(pageParam));
		var pageHtml = "<div class='pageControl'>";
		//显示上一页
		if(pageNumber <= 1){
			pageHtml += "<input class='jbtn' type='button' value='<' disabled='disabled'/>"
		}else{
			pageHtml += "<input class='jbtn' onclick='$(this).getPageList("+(pageNumber-1)+","+pageParam+");' type='button' value='<' />"
		}
		
		//如果总页数小于等于10，直接全部显示
		if(data.pageCount <= 9){
			for(var i=1;i<=data.pageCount;i++){
				if(i == pageNumber){
					pageHtml += "<input class='jbtn nowPage' onclick='$(this).getPageList("+pageNumber+","+pageParam+");' type='button' value='"+pageNumber+"' />"
				}else{
					pageHtml += "<input class='jbtn' onclick='$(this).getPageList("+i+","+pageParam+");' type='button' value='"+i+"' />"
				}
			}
		}
		//如果当前页 +10 >= 总页数
		else if( pageNumber + 5 >= data.pageCount){
			for(var i=data.pageCount - 9;i<=data.pageCount;i++){
				if(i == pageNumber){
					pageHtml += "<input class='jbtn nowPage' onclick='$(this).getPageList("+pageNumber+","+pageParam+");' type='button' value='"+pageNumber+"' />"
				}else{
					pageHtml += "<input class='jbtn' onclick='$(this).getPageList("+i+","+pageParam+");' type='button' value='"+i+"' />"
				}
			}
		
		}else{
			
			if(pageNumber <= 5){
				for(var i=1;i<=9;i++){
					if(i == pageNumber){
						pageHtml += "<input class='jbtn nowPage' onclick='$(this).getPageList("+pageNumber+","+pageParam+");' type='button' value='"+pageNumber+"' />"
					}else{
						pageHtml += "<input class='jbtn' onclick='$(this).getPageList("+i+","+pageParam+");' type='button' value='"+i+"' />"
					}
				}
			}else{
				for(var i=pageNumber-4;i<pageNumber;i++){
					pageHtml += "<input class='jbtn' onclick='$(this).getPageList("+i+","+pageParam+");' type='button' value='"+i+"' />"
				}
				pageHtml += "<input class='jbtn nowPage' onclick='$(this).getPageList("+pageNumber+","+pageParam+");' type='button' value='"+pageNumber+"' />"
				for(var i= pageNumber+1;i < pageNumber+5;i++){
					pageHtml += "<input class='jbtn' onclick='$(this).getPageList("+i+","+pageParam+");' type='button' value='"+i+"' />"
				}
			}
			
		}
		
		//显示下一页
		if(pageNumber >= data.pageCount){
			pageHtml += "<input class='jbtn' type='button' value='>'  disabled='disabled'/>"
		}else{
			pageHtml += "<input class='jbtn' onclick='$(this).getPageList("+(pageNumber+1)+","+pageParam+");' type='button' value='>' />"
		}
		pageHtml += "&nbsp;共<strong style='text-align:center;'>"+data.pageCount+"</strong>页 ,第<strong>"+pageNumber+"</strong>页</div>";
		//pageHtml += "&nbsp;共<strong style='text-align:center;'>"+data.pageCount+"</strong>页 ,第<strong>"+pageNumber+"</strong>页&nbsp;-向&nbsp;<input type='text' style='border-radius: 5px; width: 30px;height:30px;border:1px solid black;' id='jumpTo' /> &nbsp;页 <input type='button' class='jbtn' value='go' onclick='$(this).jumpToPage("+data.pageCount+","+pageParam+")' /></div>";			
		$(pageContainer).html(pageHtml);
	});
}

/*
 * 基于jquery文章列表分页控件
 * pageNumber:当前页
 * pageSize:页容量
 * callBack:回调函数
 * url:请求地址
 * dataContainer:数据容器
 * pageContainer:分页组件容器
 * 
 * */
$.fn.getPageList2 = function(pageNumber,pageSize,pageRange,url,dataContainer,pageContainer){
	var params = "";
	if($('#formParam').length > 0){
		params = $('#formParam').serialize();
	}else if(getOpenerDom('#formParam')){
		params = getOpenerDom('#formParam').serialize();
	}
	$.getJSON(url, {'pageNumber':pageNumber,'pageSize':pageSize,'params':params}, function(json){
		if(json.status != 0){
			//alert(json.msg);
			return;
		}
		var data = json.data;
		var dataHtml = "";
		if(data.rows.length == 0){
			$(pageContainer).html("");
			$(dataContainer).html("<div class='no-data'><span class='glyphicon glyphicon-info-sign'><font>&nbsp;暂无数据</font></span></div>");
			return;
		}
		for(var i = 0 ;i<data.rows.length;i++){
			var item = data.rows[i];
			var articleHtml = "<div class='articleBody-section'>";
			if(item.title != null && "" != $.trim(item.title)){
				articleHtml += "<h3 id="+item.ossLink+" class='articleTitle'>"+item.title+"</h3>";
            }else{
            	articleHtml += "<p id="+item.ossLink+" class='articleTitle'>"+item.content+"</p>";
            }
            articleHtml += "<p><span>情感 : <a href='javascript:;'>"+item.emotion+"</a></span>";
            articleHtml += "<span>站点 : <a href='javascript:;'>"+item.source+"</a></span>";
            articleHtml += "<span>作者 : <a href='javascript:;'>"+item.author+"</a></span>";
            articleHtml += "<span>发布时间 : <a href='javascript:;'>"+item.publishDateTime+"</a></span>";
            articleHtml += "<span>热度 : <a href='javascript:;'>"+item.heat+"</a></span></p></div>";
            dataHtml += articleHtml;
		}
		$(dataContainer).html(dataHtml);
		var pageParam = pageSize + ','+ pageRange+',"'+url+'",'+'"'+dataContainer+'"'+',"'+pageContainer+'"';
//		console.log(decodeURIComponent(pageParam));
		var pageHtml = "<div class='pageControl'>";
		//显示上一页
		if(pageNumber <= 1){
			pageHtml += "<input class='jbtn' type='button' value='<' disabled='disabled'/>"
		}else{
			pageHtml += "<input class='jbtn' onclick='$(this).getPageList("+(pageNumber-1)+","+pageParam+");' type='button' value='<' />"
		}
		
		//如果总页数小于等于10，直接全部显示
		if(data.pageCount <= 9){
			for(var i=1;i<=data.pageCount;i++){
				if(i == pageNumber){
					pageHtml += "<input class='jbtn nowPage' onclick='$(this).getPageList("+pageNumber+","+pageParam+");' type='button' value='"+pageNumber+"' />"
				}else{
					pageHtml += "<input class='jbtn' onclick='$(this).getPageList("+i+","+pageParam+");' type='button' value='"+i+"' />"
				}
			}
		}
		//如果当前页 +10 >= 总页数
		else if( pageNumber + 5 >= data.pageCount){
			for(var i=data.pageCount - 9;i<=data.pageCount;i++){
				if(i == pageNumber){
					pageHtml += "<input class='jbtn nowPage' onclick='$(this).getPageList("+pageNumber+","+pageParam+");' type='button' value='"+pageNumber+"' />"
				}else{
					pageHtml += "<input class='jbtn' onclick='$(this).getPageList("+i+","+pageParam+");' type='button' value='"+i+"' />"
				}
			}
		
		}else{
			
			if(pageNumber <= 5){
				for(var i=1;i<=9;i++){
					if(i == pageNumber){
						pageHtml += "<input class='jbtn nowPage' onclick='$(this).getPageList("+pageNumber+","+pageParam+");' type='button' value='"+pageNumber+"' />"
					}else{
						pageHtml += "<input class='jbtn' onclick='$(this).getPageList("+i+","+pageParam+");' type='button' value='"+i+"' />"
					}
				}
			}else{
				for(var i=pageNumber-4;i<pageNumber;i++){
					pageHtml += "<input class='jbtn' onclick='$(this).getPageList("+i+","+pageParam+");' type='button' value='"+i+"' />"
				}
				pageHtml += "<input class='jbtn nowPage' onclick='$(this).getPageList("+pageNumber+","+pageParam+");' type='button' value='"+pageNumber+"' />"
				for(var i= pageNumber+1;i < pageNumber+5;i++){
					pageHtml += "<input class='jbtn' onclick='$(this).getPageList("+i+","+pageParam+");' type='button' value='"+i+"' />"
				}
			}
			
		}
		
		//显示下一页
		if(pageNumber >= data.pageCount){
			pageHtml += "<input class='jbtn' type='button' value='>'  disabled='disabled'/>"
		}else{
			pageHtml += "<input class='jbtn' onclick='$(this).getPageList("+(pageNumber+1)+","+pageParam+");' type='button' value='>' />"
		}
		pageHtml += "&nbsp;共<strong style='text-align:center;'>"+data.pageCount+"</strong>页 ,第<strong>"+pageNumber+"</strong>页&nbsp;-向&nbsp;<input type='text' style='border-radius: 5px; width: 30px;height:30px;border:1px solid black;' id='jumpTo' /> &nbsp;页 <input type='button' class='jbtn' value='go' onclick='$(this).jumpToPage("+data.pageCount+","+pageParam+")' /></div>";			
		$(pageContainer).html(pageHtml);
	});
}


})(jQuery);
function getFilePost(url, data) {
	var tempForm = document.createElement("form");
	tempForm.method = "post";
	tempForm.action = url;
	var hideInput = document.createElement("input");
	hideInput.type = "hidden";
	hideInput.name = "content";
	hideInput.value = data;
	tempForm.appendChild(hideInput);
	document.body.appendChild(tempForm);
	tempForm.submit();
	document.body.removeChild(tempForm);
}
(function($) {
	$.fn.exportTab = function() {
		var table = $("#mainTable").html();
		getFilePost("export.jsp", table);
	}
})(jQuery);
(function($){
	$.fn.jumpToPage = function(totalPages,pageSize,pageRange,url,dataContainer,pageContainer){
			var page = Math.floor($("#jumpTo").val());
			if(!isNaN(page)&&page>0){
				if(page >= totalPages){
					page = totalPages;
				}
				$(this).getPageList(page,pageSize,pageRange,url,dataContainer,pageContainer);
			}
			return;
	}
})(jQuery);
