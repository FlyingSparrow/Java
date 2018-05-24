<!DOCTYPE html> 
<html lang="en">
	<head>
	<#include "/public.ftl"/>
	<script type="text/javascript" src="/lib/echarts/bmap.min.js"></script>
	<script type="text/javascript" src="/lib/echarts/map/js/china.js"></script>
	<script type="text/javascript" src="/js/public/thermodynamicMap.js"></script>
	<script type="text/javascript" src="/js/public/echartBar.js"></script> 
	<script type="text/javascript" src="/js/public/echartMapNo.js"></script>
	<script type="text/javascript" src="/js/policyoriented/hotevent.js"></script>
	</head>
	<body>
		<#include "/header-float.ftl"/>
		<section class="section">
			<#include "/left.ftl"/>
			<div class="right">
			<input type="hidden" id="modelId" value="${modelId}" />
			<input type="hidden" id="menuId"value="${menuId}" />
		<div class="rightBody">
             <div class="rightBody-big rightBody-big-map" >
                 <h5>热点事件地点分布
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="双创热点事件分布热力图">
						</div>
                 </h5>
                 <span class="rightBody-first-one rightBody-big-span"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
             <div class="rightBody-big" >
                 <h5>热点事件数量排行
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="双创热点事件政府网站数量排行">
						</div>
                 </h5>
                 <span class="rightBody-second-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
         </div> 
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>