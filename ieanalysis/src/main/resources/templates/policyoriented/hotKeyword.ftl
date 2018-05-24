<!DOCTYPE html> 
<html lang="en">
	<head>
	<#include "/public.ftl"/>
	<script type="text/javascript" src="/lib/echarts/echarts-wordcloud.js"></script>
	<script type="text/javascript" src="/js/public/echartBar.js"></script> 
	<script type="text/javascript" src="/js/policyoriented/hotkeyword.js"></script>
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
                 	 <h5>热点关键词
                 	 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="双创政策中出现次数最多的双创热点关键词">
						</div>
                 	 </h5>
	                 <span class="rightBody-first-one rightBody-big-span" ><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
	             </div>
	             <div class="rightBody-big" >
	                 <h5>热点关键词出现频率趋势
	                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="双创热点关键词出现次数最多的前十名的数量统计">
						</div>
	                 </h5>
	                 <span class="rightBody-second-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
	             </div>
	         </div> 
			<#include "/footer.ftl"/>
		</section> 
	</body>
</html>