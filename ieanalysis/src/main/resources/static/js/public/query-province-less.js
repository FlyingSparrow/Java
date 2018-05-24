$(function() {
	selectProvince="北京"; 
	var commonProvince=new Array();
	var provinceStr="北京,上海,广东";
	commonProvince=provinceStr.split(",");
	var provinceHtml="";
	for(var i=0;i<commonProvince.length;i++){
		if(i==0){
			provinceHtml+="<li><a href='javascript:;' class='changeColor'>"+commonProvince[i]+"</a></li>";
		}else{
			provinceHtml+="<li><a href='javascript:;' >"+commonProvince[i]+"</a></li>";
		}
	}
	$(".queryProvinceSelect").append(provinceHtml);
});
 