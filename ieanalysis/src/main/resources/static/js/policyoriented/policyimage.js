$(function() {
	textList();
});
var imageDiv="#imageDiv";
var pageDiv=".PageDiv";
function textList() {
	$(imageDiv).find(".no-data").remove();
	showloading(imageDiv);
	$(".rightBody").removeClass("rightBody-div");
	var data={}; 
	data.pageNumber=1;
	data.pageSize=12;
	var url="/policyoriented/searchPolicyImageInfo.json";
    $.ajax({
        url:url ,
        dataType: "json",
        type: 'post',
        data: data,
        success: function(result) {
        	console.log(result);
            $(pageDiv).hide();
            var len = result.data.page.totalElements;
            if (len > 0) { 
            	 $(imageDiv).find("div").remove();
                $(imageDiv).find(".no-data").remove();
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
            	closeloading(imageDiv);
            	setNoData(imageDiv);
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
            	 $(imageDiv).find("div").remove();
                 $(imageDiv).find(".no-data").remove();
                 setTableContent(result,data);
             } else { 
            	 closeloading(imageDiv);
             	setNoData(imageDiv);
             }
        },
        error: function(result) {
            return;
        }
    });
}
function setTableContent(result,data){
	 closeloading(imageDiv);
	if(!$(".rightBody").hasClass("rightBody-div")){
		 $(".rightBody").addClass("rightBody-div");
	}
	 $(pageDiv).show();
	var html="";
	var len=result.data.page.content.length;
	for(var x=0;x<len;x++){
		var temp=result.data.page.content[x];
		html+='<div class="image-page">'
		+'<a target="_blank" href="'+temp.policyUrl+'"><img src="'+temp.policyImageUrl+'"/><br>'
			+'<span>'+temp.policyTitle+'</span><br>'
			+'<span></span>'
		+'</a>'
	+'</div>';
	}
	 $(imageDiv).append(html);
}
