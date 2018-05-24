$(function() {
 load();
});
function load(){
	loadfirst();
	loadsecond();
}
function  postData(){
	var data={};
	data.province=selectProvince;
	data.year=selectYear;
	return data;
}
function loadfirst(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#79d2f7'];
	echartdata.url="/policyindex/searchRegisterAndSurviveNumAnalysis.json";
	echartdata.nodeId="rightBody-first-one";
	echartdata.xName="";
	echartdata.yName="(%)";
	echartdata.legendMark=true;
	echartdata.legend="";
	echartdata.top="20%";
	loadBarAjax(echartdata);
}

function loadsecond(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#a5b8dc'];
	echartdata.url="/policyindex/searchRegisterAndSurviveAmountAnalysis.json";
	echartdata.nodeId="rightBody-second-one";
	echartdata.xName="";
	echartdata.yName="(亿元)";
	echartdata.legendMark=true;
	echartdata.legend="";
	echartdata.top="20%";
	loadBarAjax(echartdata);
}