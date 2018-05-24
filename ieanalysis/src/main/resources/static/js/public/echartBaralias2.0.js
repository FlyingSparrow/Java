function loadBarAliasAjax(echartdata){
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
        		loadBarAliasEchart(result,document.getElementsByClassName(echartdata.nodeId)[0],echartdata);
        	}else{
        		setNoData("."+echartdata.nodeId);
        	}
        },
        error: function(result) {
            return;
        }
    });
}
function loadBarAliasEchart(result,dom,echartdata){
	closeloading(dom);
	var myechart= initECharts(dom);
	var option = {
			 calculable:false,
	    color: echartdata.color,
	    tooltip : {
	        trigger: 'axis',
	        formatter: function(params){
	        	var len=params.length;
        		var str="";
        		if(len>1){
        			var dataIndex=params[0].dataIndex;
        			str=echartdata.xName[dataIndex]+"<br/>";
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
        			var dataIndex=params[0].dataIndex;
        			if(params[0].value==undefined){
    					str= echartdata.xName[dataIndex]+":-";
    				}else{
    					str= echartdata.xName[dataIndex]+":"+params[0].value;
    				}
        		}
	       	 return str;
	        },//echartdata.formatter,//'{b0}: {c0}'
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	    	x: echartdata.leftx,
	    	y: echartdata.lefty,
	    	x2: echartdata.rightx,
	    	y2: echartdata.righty, 
	    	},
	    legend: {
	    	show:echartdata.legendMark,
	        x: 'center',
	        data:echartdata.legend,//['教育','母婴','电商','互联网金融','娱乐影视']
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : result.data.aliasName,///['娱乐', '电商', 'B2B', '母婴'],
	            axisTick: {
	                alignWithLabel: true
	            },
	            axisLabel: {
		            interval: 0,
		            rotate: 0
		        },
		        axisLine:{
	                lineStyle:{
	                    color:'#111',
	                    width:1,//这里是为了突出显示加上的，可以去掉
	                }
	            }
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            name:echartdata.yName,
	            min: echartdata.yAxisMin,
	        	max: echartdata.yAxisMax,
	        	axisLine:{
	                lineStyle:{
	                    color:'#111',
	                    width:1,//这里是为了突出显示加上的，可以去掉
	                }
	            }
	        }
	    ],
	    series :  result.data.series
	};
	//console.log(JSON.stringify(option));
	myechart.setOption(option);
} 

