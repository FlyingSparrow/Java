<!DOCTYPE html> 
<html lang="en">
	<head>
	<#include "/public.ftl"/>
	<link rel="stylesheet" href="/css/public/shaft.css"> 
	<script type="text/javascript" src="/js/policyoriented/specialEventShaft.js"></script>
	</head>
	<body>
		<#include "/header-float.ftl"/>
		<section class="section">
			<#include "/left.ftl"/>
			<div class="right">
			<input type="hidden" id="modelId" value="${modelId}" />
			<input type="hidden" id="menuId"value="${menuId}" />
		<div class="rightBody">
             <div class="event-time-line" id="event_time_line" style="height:1270px;overflow:hidden;">
    		 </div>
    		 <p class="text-center" id="loadMore">向下滚动加载更多</p>
         </div> 
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>