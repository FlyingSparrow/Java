$(function() {
	loadCloud();
	loadsecond(); 
});
 
function loadCloud(){
	$(".rightBody-first-one").find(".no-data").remove();
	showloading(".rightBody-first-one");
	var data={};
	data.year=new Date().getFullYear();
	$.ajax({ 
        url: "/policyoriented/searchPolicyHotKeyWords.json",
        dataType: "json",
        type: 'post',
        data: data,
        success: function(result) {
        	console.log(result);
        	if(result.data.data!=null){
        		loadWordCloud(result);
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
	var data={};
    var echartdata={};
	echartdata.data=data;
	echartdata.color=['#ade7d5','#79d2f7', '#a5b8dc', '#f19ec2', '#f6d29b'];
	echartdata.url="/policyoriented/searchPolicyHotKeyWordsFrequency.json";
	echartdata.nodeId="rightBody-second-one";
	echartdata.xName="";
	echartdata.yName="数量(次)";
	echartdata.legendMark=true;
	echartdata.legend="";
	echartdata.top="15%";
	loadBarAjax(echartdata);
} 
function loadWordCloud(result){
	closeloading('.rightBody-first-one');
	var myechart= echarts.init(document.getElementsByClassName('rightBody-first-one')[0]);
     var option = {
         tooltip: {},
         series: [ {
             type: 'wordCloud',
             gridSize: 2,
             sizeRange: [-10, 50],
             rotationRange: [-90, 90],
             shape: 'pentagon',
             width: 800,
             height: 400,
             textStyle: {
                 normal: {
                     color:  function () {
                         return 'rgb(' + [
                             Math.round(Math.random() * 160),
                             Math.round(Math.random() * 160),
                             Math.round(Math.random() * 160)
                         ].join(',') + ')';
                     } 
                 },
                 emphasis: {
                     shadowBlur: 1,
                     shadowColor: '#333'
                 }
             },
             data:result.data.data
//            	 [
//                 {
//                     name: 'Sam S Club',
//                     value: 10000,
//                     textStyle: {
//                         normal: {
//                             color: 'black'
//                         },
//                         emphasis: {
//                             color: 'red'
//                         }
//                     }
//                 },
//                 {
//                     name: 'Macys',
//                     value: 6181
//                 },
//                 {
//                     name: 'Amy Schumer',
//                     value: 4386
//                 },
//                 {
//                     name: 'Jurassic World',
//                     value: 4055
//                 },
//                 {
//                     name: 'Charter Communications',
//                     value: 2467
//                 },
//                 {
//                     name: 'Chick Fil A',
//                     value: 2244
//                 },
//                 {
//                     name: 'Planet Fitness',
//                     value: 1898
//                 },
//                 {
//                     name: 'Pitch Perfect',
//                     value: 1484
//                 },
//                 {
//                     name: 'Express',
//                     value: 1112
//                 },
//                 {
//                     name: 'Home',
//                     value: 965
//                 },
//                 {
//                     name: 'Johnny Depp',
//                     value: 847
//                 },
//                 {
//                     name: 'Lena Dunham',
//                     value: 582
//                 },
//                 {
//                     name: 'Lewis Hamilton',
//                     value: 555
//                 },
//                 {
//                     name: 'KXAN',
//                     value: 550
//                 },
//                 {
//                     name: 'Mary Ellen Mark',
//                     value: 462
//                 },
//                 {
//                     name: 'Farrah Abraham',
//                     value: 366
//                 },
//                 {
//                     name: 'Rita Ora',
//                     value: 360
//                 },
//                 {
//                     name: 'Serena Williams',
//                     value: 282
//                 },
//                 {
//                     name: 'NCAA baseball tournament',
//                     value: 273
//                 },
//                 {
//                     name: 'Point Break',
//                     value: 265
//                 }
//             ]
         } ]
     };

     myechart.setOption(option);
     window.onresize = myechart.resize;
} 
