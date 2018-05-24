var selectProvince=""; 
var selectArea="北京";
var selectYear=new Date().getFullYear();
var selectMonth="";
var selectContrastYear="";
$(function() {
	var commonArea=new Array();
	var areaStr="北京,上海,广州,深圳";
	commonArea=areaStr.split(",");
	var areaHtml="";
	for(var i=0;i<commonArea.length;i++){
		if(i==0){
			areaHtml+="<li><a href='javascript:;' class='changeColor'>"+commonArea[i]+"</a></li>";
		}else{
			areaHtml+="<li><a href='javascript:;' >"+commonArea[i]+"</a></li>";
		}
	}
	//queryProvinceSelect
	$(".queryAreaSelect").append(areaHtml);
	var yearHtml="";
	var curYear=new Date().getFullYear();
	for(var i=0;i<5;i++){
		if(curYear-i>=2014){
			if(i==0){
				yearHtml+="<li><a href='javascript:;' class='changeColor'>"+(curYear-i)+"</a></li>";
			}else{
				yearHtml+="<li><a href='javascript:;'>"+(curYear-i)+"</a></li>";
			}
		}
	}
	if(curYear-5>2014){
		yearHtml+="<li><a href='javascript:;'>更多</a></li>";
	}
	$(".queryYearSelect").append(yearHtml);
	
	toSetMonthSelect(curYear);
	
	//queryAreaSelect
	$(document).on('click', '.queryAreaSelect>li', function() {
		$(this).parent().find(".changeColor").removeClass("changeColor");
		$(this).find("a").addClass("changeColor");
		if($(this).find("a").text()=="不限"){
			selectArea="";
			load();
		}else{
			selectArea=$(this).find("a").text();
			load();
		}
	});
	//queryYearSelect
	$(document).on('click', '.queryYearSelect>li', function() {
		if($(this).find("a").text()=="更多"){
			var prevNum=$(this).prev().find("a").text();
			if(prevNum>2014){
				var html="";
				for(var x=1;x<6;x++){
					html+="<li><a href='javascript:;'>"+(prevNum-x)+"</a></li>";
				}
				html+="<li><a href='javascript:;'>更多</a></li>";
				$(this).remove();
				$(".queryYearSelect").append(html);
			}else{
				$(this).hide();
			}
			
		}else if($(this).find("a").text()=="不限"){
			$(this).parent().find(".changeColor").removeClass("changeColor");
			$(this).find("a").addClass("changeColor");
			selectYear="";
			toSetMonthSelect(new Date().getFullYear());
			load();
		}else{
			$(this).parent().find(".changeColor").removeClass("changeColor");
			$(this).find("a").addClass("changeColor");
			selectYear=$(this).find("a").text();
			toSetMonthSelect(selectYear);
			load();
		}
	});
});

function  toSetMonthSelect(curYear){
	var monthHtml="";
	selectMonth="";
	var cury=new Date().getFullYear();
	monthHtml+="<option selected='selected'  value=''>全部</option>";
	if(selectYear>2014&&selectYear<cury){
		for(var i=1;i<13;i++){
			monthHtml+="<option value='"+i+"'>"+curYear+"年"+i+"月</option>";
		}
	}
	if(selectYear==2014){
		for(var i=9;i<13;i++){
			monthHtml+="<option value='"+i+"'>"+curYear+"年"+i+"月</option>";
		}
	}
	if(selectYear==cury){
		var curm=new Date().getMonth();
		for(var i=1;i<=curm;i++){
			monthHtml+="<option value='"+i+"'>"+curYear+"年"+i+"月</option>";
		}
	}
	$("#queryMonthSelect").find("option").remove();
	$("#queryMonthSelect").append(monthHtml);
}
