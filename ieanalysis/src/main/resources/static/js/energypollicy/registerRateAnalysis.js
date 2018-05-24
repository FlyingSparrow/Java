$(function() {
	selectContrastYear=new Date().getFullYear()-1;
 	var contrastYearHtml="";
	for(var i=0;i<10;i++){
		if(selectContrastYear-i>=2014){
			contrastYearHtml+="<option value='"+(selectContrastYear-i)+"'>"+(selectContrastYear-i)+"年</option>";
			
		}
	}
	$("#queryContrastYearSelect").find("option").remove();
	$("#queryContrastYearSelect").append(contrastYearHtml);
	
	$(document).on('change', '#queryContrastYearSelect', function() {
		selectContrastYear=$("#queryContrastYearSelect").val();
		loadThree();
	});
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
	data.contrastYear=selectContrastYear;
	return data;
}
function loadfirst(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#79d2f7','#a8b8dc','#f19ec2','#f6d29b'];
	echartdata.url="/energyPolicy/searchNewEnterpriseRegistrationTimeDistribution.json";
	echartdata.nodeId="rightBody-first-one";
	echartdata.radius="";
	echartdata.center="";
	echartdata.legend="";
	echartdata.legendMark=true;
	loadPieAjax(echartdata);
}

function loadsecond(){
	var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#ade7d5','#79d2f7','#a8b8dc','#f19ec2','#f6d29b'];
	echartdata.url="/energyPolicy/searchNewEnterpriseRegistrationTimeAndSatisfactionStatistics.json";
	echartdata.nodeId="rightBody-first-two";
	echartdata.xName="";
	echartdata.yName="";
	echartdata.legendMark=true;
	echartdata.legend="";
	echartdata.top="10%";
	echartdata.formatter="{b0}: {c0}";
	loadBarAjax(echartdata);
}

function loadThree(){
	/*var echartdata={};
	echartdata.data=postData();
	echartdata.color=['#79d2f7','#9fe3ff'];
	echartdata.url="/energyPolicy/searchNewEnterpriseRegistrationThanSamePeriod.json";
	echartdata.nodeId="rightBody-second-one";
	echartdata.xName="";
	echartdata.yName="";
	echartdata.legendMark=true;
	echartdata.legend="";
	echartdata.top="20%";
	loadBarAjax(echartdata);*/
	$(".rightBody-second-one").find(".no-data").remove();
	showloading(".rightBody-second-one");
	var data=postData();
	$.ajax({
        url: "/energyPolicy/searchNewEnterpriseRegistrationThanSamePeriod.json",
        dataType: "json",
        type: 'post',
        data: data,
        success: function(result) {
        	if(result.data!=null){
        		loadFirstEchart(result);
        	}else{
        		setNoData(".rightBody-second-one");
        	}
        },
        error: function(result) {
            return;
        }
    });
}

function loadFirstEchart(result){
	closeloading('.rightBody-second-one');
	var myechart= initECharts(document.getElementsByClassName('rightBody-second-one')[0]);
	 var colors = ['#5793f3', '#d14a61'];

	 var   option = {
	     color: colors, 
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
		    },
	     xAxis: [
	         {
	             type: 'category',
	             axisTick: {
	                 alignWithLabel: true
	             },
	             data: ["1月", "2月", "3月", "4月",
	    				"5月", "6月", "7月", "8月", "9月",
	    				"10月", "11月", "12月" ]
	         }
	     ],
	     yAxis: [
	         {
	             type: 'value',
	             name: '新企业注册量',
	             min: 0,
	             max: 300000,
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
	             name: '众创空间量',
	             min: 0,
	             max: 3000,
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

