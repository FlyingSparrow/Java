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
	var url="/policyoriented/searchPolicyTextInfo.json";
    $.ajax({
        url: url,
        dataType: "json",
        type: 'post',
        data: data,
        success: function(result) {
            $(pageDiv).hide();
            var len = result.data.page.totalElements;
            if (len >0) { 
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
            	 closeloading(tableBody);
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
     '<th width="5%" >序号</th>'+
     '<th width="45%">标题</th>'+
     '<th width="10%" style="vertical-align:middle; text-align:left;">文件类型</th>'+
     '<th width="20%" style="vertical-align:middle; text-align:left;">发文字号</th>'+
     '<th width="10%" style="vertical-align:middle; text-align:left;">发布机构</th>'+
     '<th width="10%" >发布时间</th>'+
     '</tr>';
	var len=result.data.page.content.length;
	for(var x=0;x<len;x++){
		var temp=result.data.page.content[x];
		var tempTitle=temp.policyTitle;
		if(tempTitle.length>30){
			tempTitle=tempTitle.substring(0,30)+"...";
		}
		html+="<tr>"
	 	 +"<td >"+((data.pageNumber-1)*data.pageSize+x+1)+"</td>"
	     +"<td style='vertical-align:middle; text-align:left;' title="+temp.policyTitle.replace(" ","")+"><a target='_blank' href="+temp.policyUrl+">"+tempTitle+"</a></td>"
	     +"<td style='vertical-align:middle; text-align:left;'>"+temp.policyReleaseMechanism+"</td>"
	     +"<td style='vertical-align:middle; text-align:left;'>"+temp.policyPostShopName+"</td>"
	     +"<td style='vertical-align:middle; text-align:left;'>"+temp.policyPublishAuthor+"</td>"
	     +"<td>"+temp.time.substring(0,10)+"</td>"
	     +"</tr>";
	}
	 $(tableBody).append(html);
}
