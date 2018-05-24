$(function() {
	load();
});
function load(){
	loadfirst();
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
	echartdata.color=['#a5b8dc','#f19ec2','#f6d29b','#ade7d5','#79d2f7', '#a5b8dc', '#9fe3ff',"#FFF68F","#8B2323","#76EE00"];
	echartdata.url="/policyhot/searchPolicyHotChannelSiteAnalysis.json";
	echartdata.nodeId="rightBody-first-one";
	echartdata.radius="";
	echartdata.center="";
	echartdata.legend="";
	echartdata.legendMark=true;
	loadPieAjax(echartdata);
	loadsecond();
}

function loadsecond(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#79d2f7','#ade7d5', '#79d2f7', '#a5b8dc', '#f19ec2', '#f6d29b', '#9fe3ff'];
	echartdata.url="/policyhot/searchPolicyHotChannelSocialSubjectAnalysis.json";
	echartdata.nodeId="rightBody-second-one";
	echartdata.xName="";
	echartdata.yName="(条)";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAjax(echartdata);
	loadBarAjax(echartdata);
}
 
 