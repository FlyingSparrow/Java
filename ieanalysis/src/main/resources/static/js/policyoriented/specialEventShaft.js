$(function() {
 var height=1270;
	textList();
	$(window).scroll(function(){
		　　var scrollTop = $(this).scrollTop();
		　　var scrollHeight = $(document).height();
		　　var windowHeight = $(this).height();
		　　if(scrollTop + windowHeight == scrollHeight){
			
			if($("#event_time_line").outerHeight()-$("#time_line").outerHeight()<100){
				height+=1170;
			
				}else{
					$("#loadMore").html("加载完成")
				
					};
			
			$("#event_time_line").css({"height":height})
		
		}
		});
});
var eventTimeLine="#event_time_line";
function textList() {
	var curYear=new Date().getFullYear();
	var data={};
	data.pageNumber=1;
	data.pageSize=100;
	var url="/policyoriented/searchPolicySpecialEventShaft.json";
    $.ajax({
        url: url,
        dataType: "json",
        type: 'post',
        data: data,
        success: function(result) {
             var len = result.data.page.totalElements;
         
             if (len > 0) {
                 setTableContent(result,data,curYear);
             }else{
            	 setTableContentNull(curYear);
             }
             
        },
        error: function(result) {
            return;
        }
    });
}
function setTableContent(result,data,curYear){
	var html='<div class="event-time-line" id="event_time_line" >'
	+'<div class="box">'
	+curYear
	+'</div>'
	+'<div class="time-line" id="time_line">';
	 
	var len=result.data.page.content.length;
	for(var x=0;x<len;x++){
		var temp=result.data.page.content[x];
		html+='<div class="time-line-item">'
				+'<div class="circle-box">'
	            	+'<div class="circle"></div>'
		        +'</div>'
		        +'<div class="time-line-content">'
		            +'<h5 class="title"><a href='+temp.policyUrl+'  target="_blank" >'+temp.policyTitle+'</a></h5>'
		            +'<p class="intro">'
		                +'<label>时间：</label><span>'+temp.time.substring(0,10)+'</span>'
		            +'</p>'+'<p class="point"></p>'
		        +'</div>'
		     +'</div>';
	 
	}
	 html+='</div><div class="box">'
	      +(curYear-1)
	       +'</div>'
	       +'</div>';
	 $(eventTimeLine).append(html);
}
function  setTableContentNull(curYear){
	var html=' <div class="event-time-line" id="event_time_line" >'
				+'<div class="box">'
				+curYear
				+'</div>'
			    +'<div class="time-line" id="time_line">'
			        +'<div class="time-line-item">'
			         +'</div>'
			    +'</div>'
			    +'<div class="box">'
			    +(curYear-1)				
				+'</div>'
			 +'</div>';
	 $(eventTimeLine).append(html);
}
