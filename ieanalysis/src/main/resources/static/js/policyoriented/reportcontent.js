$(function() { 
   load();
});
function load(){
	loadfirst();
	loadsecond();
	loadThird();
	loadFour();
}
function  postData(){
	var data={};
	data.year=new Date().getFullYear();
	return data; 
}
function loadfirst(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#79d2f7'];
	echartdata.url="/policyoriented/searchPolicyMediaTranspondAmount.json";
	echartdata.nodeId="rightBody-first-one";
	echartdata.xName=['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	echartdata.yName="条";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="10%";
	loadLineAjax(echartdata);
}

function loadsecond(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#79d2f7'];
	echartdata.url="/policyoriented/searchPolicySocialTranspondAmount.json";
	echartdata.nodeId="rightBody-first-two";
	echartdata.xName=['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	echartdata.yName="条";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="10%";
	loadLineAjax(echartdata);
}

function loadThird(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#79d2f7'];
	echartdata.url="/policyoriented/searchPolicyUserCommentAmount.json";
	echartdata.nodeId="rightBody-second-one";
	echartdata.xName=['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	echartdata.yName="条";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="10%";
	loadLineAjax(echartdata);
}
function loadFour(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#79d2f7', '#ff9271','#9fe3ff'];
	echartdata.url="/policyoriented/searchPolicyEmotionAnalysis.json";
	echartdata.nodeId="rightBody-second-two";
	echartdata.radius="";
	echartdata.center="";
	echartdata.legend="";
	echartdata.legendMark=false;
	loadPieAjax(echartdata);
}
