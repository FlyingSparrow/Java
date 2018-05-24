$(function() {
 load();
});
function load(){
/*	loadfirst();
	loadsecond();
	loadThird();
	loadFour();*/
	loadFive();
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
	echartdata.color=['#ade7d5'];
	echartdata.url="/policybenefit/searchPolicyBenefitEveryMonth.json";
	echartdata.nodeId="rightBody-first-one";
	echartdata.xName="";
	echartdata.yName="亿元";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAjax(echartdata);
}

function loadsecond(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#79d2f7'];
	echartdata.url="/policybenefit/searchPolicyBenefItenterpriseInvestmentTopFive.json";
	echartdata.nodeId="rightBody-first-two";
	echartdata.xName="";
	echartdata.yName="亿元";
	echartdata.legendMark=true;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAliasAjax(echartdata);
}
function loadThird(){
	var data={};
	data.year=selectYear;
	var echartdata={};
	echartdata.data=data;
	echartdata.color=['#a5b8dc'];
	echartdata.url="/policybenefit/searchPolicyBenefIndustryArea.json";
	echartdata.nodeId="rightBody-second-one";
	echartdata.xName="";
	echartdata.yName="亿元";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAjax(echartdata);
}
function loadFour(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#f6d29b'];
	echartdata.url="/policybenefit/searchPolicyBenefItenterpriseIndustryTopFive.json";
	echartdata.nodeId="rightBody-second-two";
	echartdata.xName="";
	echartdata.yName="亿元";
	echartdata.legendMark=false;
	echartdata.legend="";
	echartdata.top="15%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAjax(echartdata);
}
function loadFive(){
	$(".rightBody-third-one").find(".no-data").remove();
	showloading(".rightBody-third-one");
	var data=postData();
	$.ajax({
        url: "/policybenefit/searchPolicyBenefPublicServiceEfficiencyAnalysis.json",
        dataType: "json",
        type: 'post',
        data: data,
        success: function(result) {
        	if(result.data!=null){
        		loadFirstEchart(result);
        	}else{
        		setNoData(".rightBody-third-one");
        	}
        },
        error: function(result) {
            return;
        }
    });
}

function loadFirstEchart(result){
	closeloading('.rightBody-third-one');
	var myechart= initECharts(document.getElementsByClassName('rightBody-third-one')[0]);
	 var colors = ['#5793f3', '#d14a61'];

	 var   option = {
	     color: colors, 
	     grid: {
	        // right: '20%'
	     },
	     tooltip: {
	         trigger: 'axis',
	         axisPointer: {
	             type: 'cross'
	         }
	     },
	     legend: {
	         data:result.data.name//['新企业注册量','新增就业人数']
	     },
	     grid: {
		        left: '5%',
		        right: '5%',
		        bottom:'10%'
		    },
	     xAxis: [
	         {
	             type: 'category',
	             axisTick: {
	                 alignWithLabel: true
	             },
	             data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
	         }
	     ],
	     yAxis: [
	         {
	             type: 'value',
	             name: '政策发布量(个)',
	             min:0,
	             max:100000,
	             position: 'left',
	             axisLine: {
	                 lineStyle: {
	                     color: colors[0]
	                 }
	             },
	             /*axisLabel: {
	                 formatter: '{value} ml'
	             }*/
	         },
	          
	         {
	             type: 'value',
	             name: '政策关注度(万人)',
	             min:0,
	             max:10000,
	             position: 'right',
	             axisLine: {
	                 lineStyle: {
	                     color: colors[1]
	                 }
	             },
	            /* axisLabel: {
	                 formatter: '{value} °C'
	             }*/
	         }
	     ],
	     series: [
	         {
	             name:result.data.name[0],//'新企业注册量',
	             type:'bar',
	             data:result.data.value[0]//[200, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
	         },
	        
	         {
	             name:result.data.name[1],//'新增就业人数',
	             type:'line',
	             yAxisIndex: 1,
	             data:result.data.value[1]//[110, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
	         }
	     ]
	 };


	myechart.setOption(option);
}
 