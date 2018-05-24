function loadLineAjax(echartdata){
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
        		loadLineEchart(result,document.getElementsByClassName(echartdata.nodeId)[0],echartdata);
        	}else{
        		setNoData("."+echartdata.nodeId);
        	}
        },
        error: function(result) {
            return;
        }
    });
}
function loadLineEchart(result,dom,echartdata){
	var myechart= initECharts(dom);
	var option = {
			color:echartdata.color,
		    title: {
		        text: '',
		        show:false
		    },
		    tooltip : {
		        trigger: 'axis',
		        formatter: function(params){
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
		        }
		    },
		    legend: {
		        data:echartdata.legend,//[],//['邮件营销','联盟广告','视频广告','直接访问','搜索引擎'],
		        show:echartdata.legendMark
		    },
		    toolbox: {
		        feature: {
		            saveAsImage: {show:false}
		        }
		    },
		    grid: {
		    	top:echartdata.top,
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : echartdata.xName//['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            name:echartdata.yName
		        }
		    ],
 		    series : result.data.series
		    //[
//		        {
//		            name:'',
//		            type:'line',
//		           /* areaStyle: {normal: {}},*/
//		            data:result.data.value//[120, 132, 101, 134, 90, 230, 210]
//		        }, 
//		    ]
		};
	myechart.setOption(option);
}