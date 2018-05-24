function loadPieAjax(echartdata){
	$("."+echartdata.nodeId).find(".no-data").remove();
	showloading("."+echartdata.nodeId);
	$.ajax({
        url: echartdata.url,
        dataType: "json",
        type: 'post',
        data: echartdata.data,
        success: function(result) {
        	if(result.data!=null){
        		if(echartdata.legend==null||echartdata.legend==""){
        			echartdata.legend=result.data.legend;
        		}
        		if(echartdata.radius==null||echartdata.radius==""){
        			echartdata.radius='70%';
        		}
        		if(echartdata.center==null||echartdata.center==""){
        			echartdata.center=['50%', '50%'];
        		}
        		loadPieEchart(result,document.getElementsByClassName(echartdata.nodeId)[0],echartdata);
        	}else{
        		setNoData("."+echartdata.nodeId);
        	}
        },
        error: function(result) {
            return;
        }
    });
}
function loadPieEchart(result,dom,echartdata){
	closeloading(dom);
	var myechart= initECharts(dom);
	var option = {
			color:echartdata.color,//['#ade7d5','#79d2f7','#a8b8dc','#f19ec2','#f6d29b'],pnn['#79d2f7','#9fe3ff','#ff9271']
		    title : {
		        text: '',
		        x:'center',
		        show:false
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{b} : {c} ({d}%)"
		    },
		    legend: {
		    	show:echartdata.legendMark,
		        orient: 'vertical',
		        left: '3%',
		        data: echartdata.legend//['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
		    },
		    series : [
		        {
		            name: '',
		            type: 'pie',
		            radius : echartdata.radius,
		            center: echartdata.center,
		            
		            data:result.data.piedata,
//		            [
//		                {value:335, name:'直接访问'},
//		                {value:310, name:'邮件营销'},
//		                {value:234, name:'联盟广告'},
//		                {value:135, name:'视频广告'},
//		                {value:1548, name:'搜索引擎'}
//		            ],
		             
		        }
		    ]
		}; 
	myechart.setOption(option);
}