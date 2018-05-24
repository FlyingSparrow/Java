var curSelectProvince="";
var curType="1";
$(function() {
	 load();
	 $(document).on("click",".rightBody-big>h5>a",function(){
		 curSelectProvince="";
		 $(this).siblings("a").addClass("a-change-color");
		  $(this).removeClass("a-change-color");
		  if($(this).text().indexOf("媒体")>=0){
			  curType="1";
			  loadfirst("1");
		  } 
		  if($(this).text().indexOf("社交")>=0){
			  curType="2";
			  loadfirst("2");
		  } 
	 });
	});
function load(){
	loadfirst("1");
	loadsecond();
	loadThird();
}
function  postData(){
	var data={};
	data.province=curSelectProvince;
	return data; 
}
function loadfirst(type){
	var data=postData();
	data.reportType=type;
	var echartdata={};
	echartdata.data=data;
	echartdata.color="";
	echartdata.url="/policyoriented/searchPolicyMediaParMapAnaylysis.json";
	echartdata.nodeId="rightBody-first-one";
	echartdata.legendMark=true;
	echartdata.heightcolor="";
	loadthermodynamicMapAjax(echartdata);
	
}

function loadsecond(){
    var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ff9271','#9fe3ff', '#79d2f7'];
	echartdata.url="/policyoriented/searchPolicyMediaParAnaylysis.json";
	echartdata.nodeId="rightBody-second-one";
	echartdata.xName="";
	echartdata.yName="数量(条)";
	echartdata.legendMark=true;
	echartdata.legend="";
	echartdata.top="20%";
	echartdata.leftx="10%";
	echartdata.lefty="10%";
	echartdata.rightx="10%";
	echartdata.righty="10%";
	loadBarAliasAjax(echartdata);
}
function loadThird(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ff9271','#9fe3ff', '#79d2f7'];
	echartdata.url="/policyoriented/searchPolicySocialParAnaylysis.json";
	echartdata.nodeId="rightBody-second-two";
	echartdata.xName="";
	echartdata.yName="数量(条)";
	echartdata.legendMark=true;
	echartdata.legend="";
	echartdata.top="20%";
	echartdata.leftx="10%";
	echartdata.lefty="10%";
	echartdata.rightx="10%";
	echartdata.righty="10%";
	loadBarAjax(echartdata);
}

function mapBack(params){
	if(curType==1){
		curSelectProvince=params.name;
		loadsecond();
	}else if(curType==2){
		curSelectProvince=params.name;
		loadThird();
	}
}

function randomData() {
    return Math.round(Math.random()*1000);
}