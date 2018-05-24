var selectDepartment="";
$(function() {
	$(document).on('change', '#queryDepartmentSelect', function() {
		selectDepartment=$("#queryDepartmentSelect").val();
		loadsecond();
	});
 load();
});
function load(){
	loadfirst();
	loadsecond();
	loadThird();
	loadFour();
	loadFive();
}
function  postData(){
	var data={};
	data.province=selectProvince;
	data.year=selectYear;
	return data;
}
var xName=['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
function loadfirst(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#91c6f5'];
	echartdata.url="/policyindex/searchManagementEnvironmentAnalysis.json";
	echartdata.nodeId="rightBody-first-one";
	echartdata.color=['#91c6f5'];
	echartdata.name="经营环境";
	loadRadarAjax(echartdata);
}

function loadsecond(){
	var data=postData();
	data.department=selectDepartment;
	var echartdata={};
	echartdata.data=data;
	echartdata.color=['#ade7d5','#79d2f7'];
	echartdata.url="/policyindex/searchCentralPolicyPublishAnalysis.json";
	echartdata.nodeId="rightBody-second-one";
	echartdata.xName=['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	echartdata.yName="(条)";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAjax(echartdata);
}

function loadThird(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#79d2f7'];
	echartdata.url="/policyindex/searchLocalPolicyPublishAnalysis.json";
	echartdata.nodeId="rightBody-second-two";
	echartdata.xName=['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	echartdata.yName="(条)";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAjax(echartdata);
}
function loadFour(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#79d2f7'];
	echartdata.url="/policyindex/searchProtectionIntellectualLitigationAnalysis.json";
	echartdata.nodeId="rightBody-third-one";
	echartdata.xName=['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	echartdata.yName="(条)";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAjax(echartdata);
}
function loadFive(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#79d2f7'];
	echartdata.url="/policyindex/searchPolicyReportAndFocusAnalysis.json";
	echartdata.nodeId="rightBody-third-two";
	echartdata.xName=['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	echartdata.yName="(条)";
	echartdata.legendMark=true;
	echartdata.legend="";
	echartdata.top="20%";
	loadBarAjax(echartdata);
}