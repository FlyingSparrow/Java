$(function() {
	load();
});
function load(){
	loadfirst();
	loadsecond();
	loadThird();
}
function  postData(){
	var data={};
	data.year=selectYear;
	data.province=selectProvince;
	return data;
}
function loadfirst(){
	var data={};
	data.year=selectYear;
	var echartdata={};
	echartdata.data=data;
	echartdata.color="";
	echartdata.url="/policyhot/searchPolicyHotAreaFocusMapAnalysis.json";
	echartdata.nodeId="rightBody-first-one";
	echartdata.legendMark=true;
	echartdata.heightcolor="";
	loadthermodynamicMapAjax(echartdata);
}

function loadsecond(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5'];
	echartdata.url="/policyhot/searchPolicyHotAreaFocusRankAnalysis.json";
	echartdata.nodeId="rightBody-second-one";
	echartdata.xName="";
	echartdata.yName="(条)";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	echartdata.leftx="10%";
	echartdata.lefty="10%";
	echartdata.rightx="10%";
	echartdata.righty="10%";
	loadBarAjax(echartdata);
}
function loadThird(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#79d2f7','#ade7d5', '#79d2f7', '#a5b8dc', '#f19ec2', '#f6d29b', '#9fe3ff'];
	echartdata.url="/policyhot/searchPolicyHotAreaFocusSocialSubjectAnalysis.json";
	echartdata.nodeId="rightBody-second-two";
	echartdata.xName="";
	echartdata.yName="(条)";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	echartdata.leftx="10%";
	echartdata.lefty="10%";
	echartdata.rightx="10%";
	echartdata.righty="10%";
	loadBarAjax(echartdata);
}
function mapBack(params){
	selectProvince=params.name;
    loadsecond();
    loadThird()
}