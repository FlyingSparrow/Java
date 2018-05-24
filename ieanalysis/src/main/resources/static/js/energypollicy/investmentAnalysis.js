$(function() {
	load();
});
 
function load(){
	loadfirst();
	loadsecond();
	loadThree();
	$(document).on('change', '#queryMonthSelect', function() {
		selectMonth=$("#queryMonthSelect").val();
		loadThree();
	});
}
 
function  postData(){
	var data={};
	data.province=selectProvince;
	data.year=selectYear; 
	data.month=selectMonth;
	return data;
}

function loadfirst(){
	$(".rightBody-first-one").find(".no-data").remove();
	showloading(".rightBody-first-one");
	$("#queryMonthSelect").show();
	var data=postData();
	$.ajax({
        url: "/energyPolicy/searchInvestmentAmountAndGrowthRate.json",
        dataType: "json",
        type: 'post',
        data: data,
        success: function(result) {
        	if(result.data!=null){
        		loadFirstEchart(result);
        	}else{
        		if(data.month==""){
        			$("#queryMonthSelect").hide();
        		}
        		setNoData(".rightBody-first-one");
        	}
        },
        error: function(result) {
            return;
        }
    });
}

function loadsecond(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#91c6f5'];
	echartdata.url="/energyPolicy/searchHotInvestmentIndustryTop.json";
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
	echartdata.color=['#91c6f5'];
	echartdata.url="/energyPolicy/searchInvestmentIndustryDistribute.json";
	echartdata.nodeId="rightBody-second-two";
	echartdata.color=['#91c6f5'];
	echartdata.name="行业分布";
	loadRadarAjax(echartdata);
}

function loadFirstEchart(result){
	closeloading('.rightBody-first-one');
	var maxleft=100;
	var maxright=100;
	if(result.data.max>1000){
		maxleft=result.data.max;
		maxright=result.data.max/10;
	}else if(result.data.max>1000){
		maxright=result.data.max;
	}
	var myechart= initECharts(document.getElementsByClassName('rightBody-first-one')[0]);
	var option = {
		    title: {
		        text: '区域投资总额及投资增长率分布',
		        show:false
		    },
		    tooltip : {
		        trigger: 'axis',
		        show:true
		    },
		    legend: {
		        data:['投资总额','投资增长率']
		    },
		    toolbox: {
		    	showTitle:false,
		        feature: {
		            saveAsImage: {show:false}
		        }
		    },
		    color:['#91c6f5','#ffbc80'],
		    grid: {
		        left: '5%',
		        right: '5%',
		        bottom: '10%',
		        containLabel: false
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
		        },
		        {
		            type : 'category',
		            data : []//['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec']
		        }
		    ],
		    yAxis : [
		         {
		            name: '投资总额(亿元)',
		            min:0,
		            max:maxleft,
		            type: 'value',
		            splitLine:{show: true}
		        },
		        {
		            name: '投资增长率(%)',
		            type: 'value',
		            min:0,
		            max:maxleft,
		            nameLocation:'end',
		            splitLine:{show: true},
		        	/*inverse: true, */
		        }
		    ],
		    series : [
		        {
		            name:'投资总额',
		            type:'line',
		            areaStyle: {normal: {}},
		            data: result.data.amount
		             /*data:[120, 132, 101, 134, 90, 230, 210, 101, 134, 90, 230, 210]*/
		            /*data:[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]*/
		        },
		        {
		            name:'投资增长率',
		            type:'line',
		            yAxisIndex:1,
		            areaStyle: {normal: {}},
		            data:result.data.rate
		           /* data:[220, 182, 191, 234, 290, 330, 310, 191, 234, 290, 330, 310]*/
		            /*data:[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]*/
		        },
		    ]
		};
	myechart.setOption(option);
}
