function loadMapAjax(echartdata){
	$("."+echartdata.nodeId).find(".no-data").remove();
	showloading("."+echartdata.nodeId);
	$.ajax({
        url: echartdata.url,
        dataType: "json",
        type: 'post',
        data: echartdata.data,
        success: function(result) {
        	if(result.data!=null){
        		loadMapEchart(result,document.getElementsByClassName(echartdata.nodeId)[0],echartdata);
        	}else{
        		setNoData("."+echartdata.nodeId);
        	}
        },
        error: function(result) {
            return;
        }
    });
}
function loadMapEchart(result,dom,echartdata){
	closeloading(dom);
	var myechart= initECharts(dom);
	var option = {
			//color:echartdata.color,
		    title: {
		        text: '',
		        subtext: '',
		        left: 'center',
		        show:false
		        	
		    },
		    tooltip: {
		        trigger: 'item',
		    },
		    legend: {
		    	show:echartdata.legendMark,
		       orient: 'horizontal',
		        left: 'center',
		        data:result.data.legend//['iphone3','iphone4','iphone5']
		    },
		    visualMap: {
		    	min: 0,
			    max: result.data.max,
		        left: '3%',
		        top: 'bottom',
		        text: ['高','低'],           // 文本，默认为数值文本
		        calculable: true,
		        //color:echartdata.heightcolor,
		    },
		    toolbox: {
		        show: false,
		        orient: 'vertical',
		        left: 'right',
		        top: 'center',
		        feature: {
		            dataView: {readOnly: false},
		            restore: {},
		            saveAsImage: {}
		        }
		    },
		    series: result.data.series
		    //[
//		        {
//		            name: 'iphone3',
//		            type: 'map',
//		            mapType: 'china',
//		            roam: false,
//		            label: {
//		                normal: {
//		                    show: true
//		                },
//		                emphasis: {
//		                    show: true
//		                }
//		            },
//		            data:[
//		                {name: '北京',value: randomData() },
//		                {name: '天津',value: randomData() },
//		                {name: '上海',value: randomData() },
//		                {name: '重庆',value: randomData() },
//		                {name: '河北',value: randomData() },
//		                {name: '河南',value: randomData() },
//		                {name: '云南',value: randomData() },
//		                {name: '辽宁',value: randomData() },
//		                {name: '黑龙江',value: randomData() },
//		                {name: '湖南',value: randomData() },
//		                {name: '安徽',value: randomData() },
//		                {name: '山东',value: randomData() },
//		                {name: '新疆',value: randomData() },
//		                {name: '江苏',value: randomData() },
//		                {name: '浙江',value: randomData() },
//		                {name: '江西',value: randomData() },
//		                {name: '湖北',value: randomData() },
//		                {name: '广西',value: randomData() },
//		                {name: '甘肃',value: randomData() },
//		                {name: '山西',value: randomData() },
//		                {name: '内蒙古',value: randomData() },
//		                {name: '陕西',value: randomData() },
//		                {name: '吉林',value: randomData() },
//		                {name: '福建',value: randomData() },
//		                {name: '贵州',value: randomData() },
//		                {name: '广东',value: randomData() },
//		                {name: '青海',value: randomData() },
//		                {name: '西藏',value: randomData() },
//		                {name: '四川',value: randomData() },
//		                {name: '宁夏',value: randomData() },
//		                {name: '海南',value: randomData() },
//		                {name: '台湾',value: randomData() },
//		                {name: '香港',value: randomData() },
//		                {name: '澳门',value: randomData() }
//		            ]
//		        },
//		        {
//		            name: 'iphone4',
//		            type: 'map',
//		            mapType: 'china',
//		            label: {
//		                normal: {
//		                    show: true
//		                },
//		                emphasis: {
//		                    show: true
//		                }
//		            },
//		            data:[
//		                {name: '北京',value: randomData() },
//		                {name: '天津',value: randomData() },
//		                {name: '上海',value: randomData() },
//		                {name: '重庆',value: randomData() },
//		                {name: '河北',value: randomData() },
//		                {name: '安徽',value: randomData() },
//		                {name: '新疆',value: randomData() },
//		                {name: '浙江',value: randomData() },
//		                {name: '江西',value: randomData() },
//		                {name: '山西',value: randomData() },
//		                {name: '内蒙古',value: randomData() },
//		                {name: '吉林',value: randomData() },
//		                {name: '福建',value: randomData() },
//		                {name: '广东',value: randomData() },
//		                {name: '西藏',value: randomData() },
//		                {name: '四川',value: randomData() },
//		                {name: '宁夏',value: randomData() },
//		                {name: '香港',value: randomData() },
//		                {name: '澳门',value: randomData() }
//		            ]
//		        },
//		        {
//		            name: 'iphone5',
//		            type: 'map',
//		            mapType: 'china',
//		            label: {
//		                normal: {
//		                    show: true
//		                },
//		                emphasis: {
//		                    show: true
//		                }
//		            },
//		            data:[
//		                {name: '北京',value: randomData() },
//		                {name: '天津',value: randomData() },
//		                {name: '上海',value: randomData() },
//		                {name: '广东',value: randomData() },
//		                {name: '台湾',value: randomData() },
//		                {name: '香港',value: randomData() },
//		                {name: '澳门',value: randomData() }
//		            ]
//		        }
//		    ]
		};
	myechart.setOption(option);
	myechart.on('click', function (params) {
	    mapBack(params);
	});
}

function randomData() {
    return Math.round(Math.random()*1000);
} 