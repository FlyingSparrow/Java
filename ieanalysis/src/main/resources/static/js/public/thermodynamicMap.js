function loadthermodynamicMapAjax(echartdata){
	$("."+echartdata.nodeId).find(".no-data").remove();
	showloading("."+echartdata.nodeId);
	$.ajax({
        url: echartdata.url,
        dataType: "json",
        type: 'post',
        data: echartdata.data,
        success: function(result) {
        	if(result.data!=null){
        		loadthermodynamicMapEchart(result,document.getElementsByClassName(echartdata.nodeId)[0],echartdata);
        	}else{
        		setNoData("."+echartdata.nodeId);
        	}
        },
        error: function(result) {
            return;
        }
    });
}
function loadthermodynamicMapEchart(result,dom,echartdata){
	var myechart= initECharts(dom);
	closeloading(dom);
	var geoCoordMap = {
		    "安徽":[117.283042,31.86119],
		    "澳门":[113.552965,22.207882],
		    "北京":[116.418757,39.917544],
		    "重庆":[108.380246,30.807807],
		    "福建":[119.306239,26.075302],
		    "甘肃":[103.823557,36.058039],
		    "广东":[113.280637,23.125178],
		    "广西":[108.320004,22.82402],
		    "贵州":[106.713478,26.578343],
		    "海南":[110.33119,20.031971],
		    "河北":[114.502461,38.045474],
		    "黑龙江":[126.642464,45.756967],
		    "河南":[113.665412,34.757975],
		    "湖北":[114.298572,30.584355],
		    "湖南":[112.982279,28.19409],
		    "江苏":[118.767413,32.041544],
		    "江西":[115.892151,28.676493],
		    "吉林":[125.3245,43.886841],
		    "辽宁":[123.429096,41.796767],
		    "内蒙古":[111.670801,40.818311],
		    "宁夏":[106.278179,38.46637],
		    "青海":[102.10327,36.502916],
		    "山东":[117.000923,36.675807],
		    "上海":[121.490317,31.222771],
		    "山西":[112.549248,37.857014],
		    "陕西":[108.948024,34.263161],
		    "四川":[104.065735,30.659462],
		    "台湾":[121.509062,25.044332],
		    "天津":[117.195907,39.118327],
		    "香港":[114.154334,22.281931],
		    "新疆":[87.617733,43.792818],
		    "西藏":[91.132212,29.660361],
		    "云南":[102.712251,25.040609],
		    "浙江":[120.153576,30.287459]
		};
	var convertData = function (data) {
	    var res = [];
	    for (var i = 0; i < data.length; i++) {
	        var geoCoord = geoCoordMap[data[i].name];
	        if (geoCoord) {
	            res.push(geoCoord.concat(data[i].value));
	        }
	    }
	    return res;
	};

	option = {
	    title: {
	    	show:false,
	        text: '',
	        subtext: '',
	        sublink: '',
	        left: 'center',
	        textStyle: {
	            color: '#fff'
	        }
	    },
	    backgroundColor: '#FFFFFF',
	    visualMap: {
	    	type:'continuous',
	        min: 0,
	        max: result.data.max,
	        left: 'left',
	        top: 'bottom',
	        //text:['高', '低'],
	        //splitNumber: 5,
	        inRange: {
	            color: ['#0000CD','#00CD00','#EEEE00','#FF0000']
	        },
	        textStyle: {
	            color: '#000000'
	        },
	        calculable: true
	    },
	    geo: {
	        map: 'china',
	        label: {
	            emphasis: {
	                show: false
	            }
	        },
	        roam: false,
	        itemStyle: {
	            normal: {
	                areaColor: '#FFFFFF',
	                borderColor: '#000000'
	            },
	            emphasis: {
	                areaColor: '#FFFFFF'
	            }
	        }
	    },
	    series: [{
	        name: 'AQI',
	        type: 'heatmap',
	        coordinateSystem: 'geo',
	        data: convertData(result.data.data),
	        tooltip:{
	        	formatter:function (params){
	        		return params;
	        	}
	    
	        }
	    
	    }]
	};
	myechart.setOption(option);
	myechart.on('click', function (params) {
	    mapBack(params);
	});
}

function randomData() {
    return Math.round(Math.random()*1000);
} 
 