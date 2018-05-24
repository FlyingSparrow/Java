$(function() {
	textList();
});
var tableId="#publicTable";
var tableBody="#tableBody";
var pageDiv=".PageDiv";
function textList() {
	$(tableBody).find(".no-data").remove();
	showloading(tableBody);
	var data={};
	data.pageNumber=1;
	data.pageSize=15;
	var url="/policyoriented/searchPolicyVideoInfo.json";
    $.ajax({
        url: url,
        dataType: "json",
        type: 'post',
        data: data,
        success: function(result) {
            $(pageDiv).hide();
            var len = result.data.page.totalElements;
            if (len > 0) { 
            	 $(tableBody).find("tr").remove();
                $(tableBody).find(".no-data").remove();
                $.jqPaginator(
                    '#paging', {
                        totalPages: result.data.page.totalPages,
                        visiblePages: data.pageSize,
                        currentPage: data.pageNumber,
                        prev: '<li class="prev"><a href="javascript:void(0);"><sapn class="glyphicon glyphicon-chevron-left"></sapn><\/a><\/li>',
                        next: '<li class="next"><a href="javascript:void(0);"><span class="glyphicon glyphicon-chevron-right"></span><\/a><\/li>',
                        page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
                        onPageChange: function(num, type) {
                        	if(num>=1000){
                        		num=1;
                        	}
                        	if(num>=1){ 
                        		changeContent(url,data, num);
                        	}
                        },
                    }); 
            } else {
            	closeloading(tableBody);
            	setNoData(tableBody);
            }
        },
        error: function(result) {
            return;
        }
    });
}

function changeContent(url,data, num){
	data.pageNumber=num;
    $.ajax({
        url: url,
        dataType: "json",
        type: 'post',
        data: data,
        success: function(result) {
        	 $(pageDiv).hide();
             var len = result.data.page.totalElements;
             if (len > 0) {
            	 $(tableBody).find("tr").remove();
                 $(tableBody).find(".no-data").remove();
                 setTableContent(result,data);
             } else { 
            	 closeloading(tableBody);s
             	setNoData(tableBody);
             }
        },
        error: function(result) {
            return;
        }
    });
}
function setTableContent(result,data){
	closeloading(tableBody);
	 $(pageDiv).show();
	var html="";
	html+='<tr>'+
     '<th width="5%">序号</th>'+
     '<th width="20%">网站</th>'+
     '<th width="60%">标题</th>'+
     '<th width="15%">发布时间</th>'+
     '</tr>';
	var len=result.data.page.content.length;
	for(var x=0;x<len;x++){
		var temp=result.data.page.content[x];
		var tempTitle=temp.policyTitle;
		if(tempTitle.length>40){
			tempTitle=tempTitle.substring(0,40)+"...";
		}
		html+="<tr>"
	 	 +"<td>"+((data.pageNumber-1)*data.pageSize+x+1)+"</td>"
	 	 +"<td style='vertical-align:middle; text-align:left;'>"+temp.site+"</td>"
	     +"<td style='vertical-align:middle; text-align:left;' title="+temp.policyTitle.replace(" ","")+"><a target='_blank' href="+temp.policyUrl+">"+tempTitle+"</a></td>"
	     +"<td>"+temp.time.substring(0,10)+"</td>"
	     +"</tr>";
	}
	 $(tableBody).append(html);
}
