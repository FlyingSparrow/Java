$(function() {
	var menuId=$("#menuId").val();
	var node=$(".che-left-ul").find(".left-active");
	node.removeClass("left-active");
	node.addClass("left-normal");
	$(".che-left-ul").find("li").each(function(i){
		if(i==menuId){
			$(this).addClass("left-active");
			$(this).removeClass("left-normal");
		}
	});
	
	
});
//左侧菜单
$(document).on('click', '.che-left-ul>li>a', function() {
	$(this).parent().parent().find(".left-active").removeClass("left-active");
	$(this).addClass("left-active");
	load();
});
//head
$(document).on('click', '.header-ul>li', function() {
	$(this).parent().find(".choose").removeClass("choose");
	$(this).addClass("choose");
	load();
});

//float
$(document).on('click', '.float-right-a', function() {
	if($(this).hasClass("a-active")){
		$(this).removeClass("a-active");
		$(this).addClass("a-normal");
		$(".float-right-div").hide();
	}else{
		$(this).removeClass("a-normal");
		$(this).addClass("a-active");
		$(".float-right-div").show();
	}
}); 

function setNoData(nodeId){
	$(nodeId).find("div").remove();
	$(nodeId).append('<div class="no-data"><span><img src="/images/noData.png" alt=""></span></div>');
}

function initECharts(dom){
	var myechart= echarts.init(dom);
	return myechart;
}
function mapBack(params){
	
}
/*开启loding的状态*/
function showloading(dom) {
    var str = "";
    str += "<div class='loding'>";
    str += "<img src='/images/load-big.gif' alt=''>";
    str += "</div>";

    $(dom).append(str);
}
/*关闭loding的状态*/
function closeloading(dom) {
    $(dom).children('.loding').remove()
};
//鼠标进入提示
function tishi(obj) {
    $(obj).siblings("p").css({
        display: "inline-block"
    })
}
//鼠标退出消失
function xiaoshi(obj) {
    $(obj).siblings("p").css({
        display: "none"
    })
}