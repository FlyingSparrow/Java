$(function() {
	selectArea="北京";
	var commonArea=new Array();
	var areaStr="北京,上海,广东";
	commonArea=areaStr.split(",");
	var areaHtml="";
	for(var i=0;i<commonArea.length;i++){
		if(i==0){
			areaHtml+="<li><a href='javascript:;' class='changeColor'>"+commonArea[i]+"</a></li>";
		}else{
			areaHtml+="<li><a href='javascript:;' >"+commonArea[i]+"</a></li>";
		}
	}
	//queryAreaSelect
	$(".queryAreaSelect").append(areaHtml);
});