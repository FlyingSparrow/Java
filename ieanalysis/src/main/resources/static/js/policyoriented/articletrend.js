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
	return data; 
}
function loadfirst(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#79d2f7'];
	echartdata.url="/policyoriented/searchPolicyMediaCommentTotalRanking.json";
	echartdata.nodeId="rightBody-first-one";
	echartdata.xName="";
	echartdata.yName="条";
	echartdata.legendMark=true;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAjax(echartdata);
}

function loadsecond(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#a5b8dc','#f19ec2','#f6d29b','#ade7d5','#79d2f7', '#a5b8dc', '#9fe3ff',"#FFF68F","#8B2323","#76EE00"];
	echartdata.url="/policyoriented/searchPolicyMediaArticleProportion.json";
	echartdata.nodeId="rightBody-second-one";
	echartdata.radius="";
	echartdata.center="";
	echartdata.legend="";
	echartdata.legendMark=true;
	loadPieAjax(echartdata);
}
function loadThird(){
		var echartdata={};
		echartdata.data=postData();
		echartdata.color=['#a5b8dc','#f19ec2','#f6d29b','#ade7d5','#79d2f7', '#a5b8dc', '#9fe3ff',"#FFF68F","#8B2323","#76EE00"];
		echartdata.url="/policyoriented/searchPolicyMediaArticleTrend.json";
		echartdata.nodeId="rightBody-second-two";
		echartdata.xName="";
		echartdata.yName="条";
		echartdata.legendMark=true;
		echartdata.legend="";
		echartdata.top="20%";
		loadBarAjax(echartdata);
}
