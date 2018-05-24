function loadBarLeftAjax(echartdata){
	$("."+echartdata.nodeId).find(".no-data").remove();
	showloading("."+echartdata.nodeId);
	$.ajax({
        url: echartdata.url,
        dataType: "json",
        type: 'post',
        data: echartdata.data,
        success: function(result) {
        	if(result.data!=null){
        		if(echartdata.xName==null||echartdata.xName==""){
        			echartdata.xName=result.data.name;
        		}
        		if(echartdata.legend==null||echartdata.legend==""){
        			if(result.data.legend!=null){
        				echartdata.legend=result.data.legend;
        			}else{
        				echartdata.legend=result.data.name;
        			}
        		}
        		loadBarLeftEchart(result,document.getElementsByClassName(echartdata.nodeId)[0],echartdata);
        	}else{
        		setNoData("."+echartdata.nodeId);
        	}
        },
        error: function(result) {
            return;
        }
    });
}
function loadBarLeftEchart(result,dom,echartdata){
	closeloading(dom);
	var myechart= initECharts(dom);
	var option = {
	    color: echartdata.color,
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        },
	        formatter:function(params){
	        	var len=params.length;
        		var str="";
        		if(len>1){
        			str=params[0].name+"<br/>";
        			for(var x=0;x<len;x++){
        				if(params[x].value==undefined){
        					str+= params[x].seriesName+":-";
        				}else{
        					str+= params[x].seriesName+":"+params[x].value;
        				}
        				if(x!=len-1){
        					str+="<br/>";
        				}
        			}
        		}else if(len==1){
        			if(params[0].value==undefined){
    					str= params[0].name+":-";
    				}else{
    					str= params[0].name+":"+params[0].value;
    				}
        		}
	       	 return str;
	        },
	    },
	    grid: {
	        left: '10%',
	        right: '15%',
	        bottom: '3%',
	        top:echartdata.top,
	        containLabel: true
	    },
	    legend: {
	    	show:echartdata.legendMark,
	        x: 'center',
	        data:echartdata.legend,//['教育','母婴','电商','互联网金融','娱乐影视']
	    },
	    yAxis : [
	        {
	            type : 'category',
	            data : echartdata.xName,///['娱乐', '电商', 'B2B', '母婴'],
	            axisTick: {
	                alignWithLabel: true
	            }
	        }
	    ],
	    xAxis : [
	        {
	            type : 'value',
	            name:echartdata.yName
	        }
	    ],
	    series :  result.data.series
	};
	myechart.setOption(option);
} 