$(function() {
 load();
});
function load(){
	loadfirst();
	loadsecond();
	loadThree();
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
	echartdata.url="/policyindex/searchEnterpriseCoreCompetitivenessAnalysis.json";
	echartdata.nodeId="rightBody-first-one";
	//['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
	echartdata.xName="";
	echartdata.yName="产品数(万个)";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	loadLineAjax(echartdata);
} 

function loadsecond(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#79d2f7'];
	echartdata.url="/policyindex/searchEnterpriseMarketForceAnalysis.json";
	echartdata.nodeId="rightBody-second-one";
	echartdata.xName="";
	echartdata.yName="(亿元)";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAjax(echartdata);
}

function loadThree(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#79d2f7'];
	echartdata.url="/policyindex/searchEnterpriseInnovationAndCreativityAnalysis.json";
	echartdata.nodeId="rightBody-three-one";
	echartdata.xName="";
	echartdata.yName="(亿元)";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAjax(echartdata);
}