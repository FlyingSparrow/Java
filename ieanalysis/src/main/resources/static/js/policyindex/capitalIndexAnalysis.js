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
	echartdata.url="/policyindex/searchFinancingAmountAnalysis.json";
	echartdata.nodeId="rightBody-first-one";
	echartdata.xName=['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	echartdata.yName="金额(亿元)";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	loadLineAjax(echartdata);
} 

function loadsecond(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#79d2f7'];
	echartdata.url="/policyindex/searchMergersAmountAnalysis.json";
	echartdata.nodeId="rightBody-second-one";
	echartdata.xName=['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	echartdata.yName="金额(亿元)";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	loadLineAjax(echartdata);
}

function loadThree(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#79d2f7'];
	echartdata.url="/policyindex/searchQuitAmountAnalysis.json";
	echartdata.nodeId="rightBody-third-one";
	echartdata.xName=['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	echartdata.yName="金额(亿元)";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	loadLineAjax(echartdata);
}