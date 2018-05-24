$(function() {
 	selectContrastYear=new Date().getFullYear()-1;
 	var contrastYearHtml="";
	for(var i=0;i<10;i++){
		if(selectContrastYear-i>=2014){
			contrastYearHtml+="<option value='"+(selectContrastYear-i)+"'>"+(selectContrastYear-i)+"年</option>";
		}
	}
	$("#queryContrastYearSelect").find("option").remove();
	$("#queryContrastYearSelect").append(contrastYearHtml);
	
	$(document).on('change', '#queryContrastYearSelect', function() {
		selectContrastYear=$("#queryContrastYearSelect").val();
		load();
	});
	load();
});
 
function load(){
	loadfirst();
	loadsecond();
	$(document).on('change', '#queryMonthSelect', function() {
		selectMonth=$("#queryMonthSelect").val();
		loadThree();
	});
}

function  postData(){
	var data={};
	data.province=selectProvince;
	data.year=selectYear;
	data.contrastYear=selectContrastYear;
	return data;
}

function loadfirst(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#79d2f7'];
	echartdata.url="/energyPolicy/searchRegistrationsContrast.json";
	echartdata.nodeId="rightBody-first-one";
	echartdata.xName="";
	echartdata.yName="";
	echartdata.legendMark=true;
	echartdata.legend="";
	echartdata.top="10%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAjax(echartdata);
}

function loadsecond(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#79d2f7'];
	echartdata.url="/energyPolicy/searchRegistrationsRateContrast.json";
	echartdata.nodeId="rightBody-first-two";
	echartdata.xName="";
	echartdata.yName="增长比(%)";
	echartdata.legendMark=true;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	loadLineAjax(echartdata);
}