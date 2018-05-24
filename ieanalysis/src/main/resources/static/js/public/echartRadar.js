function loadRadarAjax(echartdata){
	$("."+echartdata.nodeId).find(".no-data").remove();
	showloading("."+echartdata.nodeId);
	$.ajax({
        url: echartdata.url,
        dataType: "json",
        type: 'post',
        data: echartdata.data,
        success: function(result) {
        	if(result.data!=null){
        		loadRadarEchart(result,document.getElementsByClassName(echartdata.nodeId)[0],echartdata);
        	}else{
        		setNoData("."+echartdata.nodeId);
        	}
        },
        error: function(result) {
            return;
        }
    });
}
function loadRadarEchart(result,dom,echartdata){
	closeloading(dom);
	var myechart= initECharts(dom);
	var option = {
		    title: {
		        text: '区域投资方向分布',
		        show:false
		    },
		    color: echartdata.color,//['#91c6f5'],
		    tooltip : {
		        trigger: 'axis',
		        show:true
		    },
		    legend: {
		        x: 'center',
		        data:result.data.name,//['教育','母婴','电商','互联网金融','娱乐影视']
		    },
		    radar: [
		        {
 		            indicator:result.data.indicator,
//		        	[
//		                {text: '教育', max: 100},
//		                {text: '母婴', max: 100},
//		                {text: '电商', max: 100},
//		                {text: '互联网金融', max: 100},
//		                {text: '娱乐影视', max: 100},
//		            ],
		            center: ['50%','50%'],
		            radius: 90
		        }, 
		    ],
		    series: [
		        {
		            type: 'radar',
		             tooltip: {
		                trigger: 'item'
		            },
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		            data: [
		                {
		                    value: result.data.value,//[60,73,85,40,30],
		                    name: echartdata.name
		                }
		            ]
		        }, 
		    ]
		};
	myechart.setOption(option);
}
