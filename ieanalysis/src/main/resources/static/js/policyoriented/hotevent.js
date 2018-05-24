var curSelectProvince="";
$(function() {
 load();
});
function load(){
	loadfirst();
	loadsecond();
}
function  postData(){
	var data={};
	data.province=curSelectProvince;
	return data;
}
function loadfirst(type){
    var data=postData();
	var echartdata={};
	echartdata.data=data;
	echartdata.color=['#79d2f7','#9fe3ff'];
	echartdata.url="/policyoriented/searchPolicyHotEventPlaceDistrbute.json";
	echartdata.nodeId="rightBody-first-one";
	echartdata.legendMark=false;
	echartdata.heightcolor="";
	loadthermodynamicMapAjax(echartdata);
}

function loadsecond(){
    var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#a5b8dc'];
	echartdata.url="/policyoriented/searchPolicyHotEventAmountDistrbute.json";
	echartdata.nodeId="rightBody-second-one";
	echartdata.xName="";
	echartdata.yName="数量(条)";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.leftx="5%";
	echartdata.lefty="12%";
	echartdata.rightx="5%";
	echartdata.righty="10%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAjax(echartdata);
} 
function mapBack(params){
	curSelectProvince=params.name;
    loadsecond();
}