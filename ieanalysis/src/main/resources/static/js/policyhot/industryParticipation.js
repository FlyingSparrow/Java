$(function() {
 load();
});
function load(){
	loadfirst();
	loadsecond();
	loadthird();
}
function  postData(){
	var data={};
	data.province=selectProvince;
	data.year=selectYear;
	return data;
}
function loadfirst(type){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#79d2f7', '#ff9271','#9fe3ff'];
	echartdata.url="/policyhot/searchPolicyHotIndustryEmotionAnalysis.json";
	echartdata.nodeId="rightBody-first-one";
	echartdata.radius="";
	echartdata.center="";
	echartdata.legend="";
	echartdata.legendMark=false;
	loadPieAjax(echartdata);
}

function loadsecond(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#a5b8dc'];
	echartdata.url="/policyhot/searchPolicyHotIndustryRankAnalysis.json";
	echartdata.nodeId="rightBody-first-two";
	echartdata.xName="";
	echartdata.yName="阅读量(条)";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAjax(echartdata);
}
function loadthird(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#79d2f7', '#ff9271','#9fe3ff'];
	echartdata.url="/policyhot/searchPolicyHotIndustryDistributeAnalysis.json";
	echartdata.nodeId="rightBody-second-one";
	echartdata.xName="";
	echartdata.yName="比重(%)";
	echartdata.legendMark=true;
	echartdata.legend="";
	echartdata.top="20%";
	echartdata.yAxisMin=0;
 	echartdata.yAxisMax=100;
	loadBarAjax(echartdata);
}
 