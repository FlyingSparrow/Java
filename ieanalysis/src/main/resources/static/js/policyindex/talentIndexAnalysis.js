$(function() {
 load();
});
 
function load(){
	loadfirst();
	loadsecond();
	loadThree();
	loadFour();
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
	echartdata.url="/policyindex/searchJobsNumberAnalysis.json";
	echartdata.nodeId="rightBody-first-one";
	echartdata.xName=['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	echartdata.yName="数量(个)";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAjax(echartdata);
} 

function loadsecond(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#79d2f7'];
	echartdata.url="/policyindex/searchAvgRemunerationAnalysis.json";
	echartdata.nodeId="rightBody-second-one";
	echartdata.xName=['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	echartdata.yName="平均工资(元)";
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
	echartdata.url="/policyindex/searchCoverHeatAnalysis.json";
	echartdata.nodeId="rightBody-three-one";
	echartdata.xName="";
	echartdata.yName="求职比例";
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
	echartdata.url="/policyindex/searchCombinationTalentRatioAnalysis.json";
	echartdata.nodeId="rightBody-four-one";
	echartdata.xName=['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	echartdata.yName="比例";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAjax(echartdata);
}