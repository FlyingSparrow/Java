$(function() {
	load();
});
 
function load(){
	loadfirst();
	loadsecond();
}

function  postData(){
	var data={};
	data.year=new Date().getFullYear();
	return data;
}


function loadfirst(){
	$(".rightBody-first-one").find(".no-data").remove();
	showloading(".rightBody-first-one");
	var data=postData();
	$.ajax({
        url: "/policyoriented/searchPolicyAffectInfo.json",
        dataType: "json",
        type: 'post',
        data: data,
        success: function(result) {
        	if(result.data!=null){
        		loadFirstEchart(result);
        	}else{
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
	echartdata.color=['#ade7d5','#79d2f7','#a8b8dc','#f19ec2','#f6d29b'];
	echartdata.url="/policyoriented/searchPolicyAffectIndustryTrent.json";
	echartdata.nodeId="rightBody-second-one";
	echartdata.radius=['40%', '80%'];
	echartdata.center="";
	echartdata.legend="";
	echartdata.legendMark=true;
	loadPieAjax(echartdata);
}
 
function loadFirstEchart(result){
	closeloading('.rightBody-first-one');
	var myechart= initECharts(document.getElementsByClassName('rightBody-first-one')[0]);
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
	             name: '注册数(个)',
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
	             name: '招聘量(万人)',
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


function loadsecondEchart(result){
	var myechart= echarts.init(document.getElementsByClassName('rightBody-second-one')[0]);

	var  option = {
	    tooltip: {
	        trigger: 'item',
	        formatter: "{a} <br/>{b}: {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        x: 'left',
	        data:result.data.name//['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
	    },
	    series: [
	        {
	            name:'',
	            type:'pie',
	            radius: ['40%', '80%'],
	            avoidLabelOverlap: false,
	            label: {
	                normal: {
	                    show: false,
	                    position: 'center'
	                },
	                emphasis: {
	                    show: false,
	                    textStyle: {
	                        fontSize: '30',
	                        fontWeight: 'bold'
	                    }
	                }
	            },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data:result.data.data//[
//	                {value:335, name:'直接访问'},
//	                {value:310, name:'邮件营销'},
//	                {value:234, name:'联盟广告'},
//	                {value:135, name:'视频广告'},
//	                {value:1548, name:'搜索引擎'}
//	            ]
	        }
	    ]
	};


	myechart.setOption(option);
}