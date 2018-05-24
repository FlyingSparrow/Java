<!DOCTYPE html> 
<html lang="en">
	<head>
	<#include "/public.ftl"/>
	<script type="text/javascript" src="/js/public/query.js"></script>
	<script type="text/javascript" src="/js/public/query-province-less.js"></script>
	<script type="text/javascript" src="/js/public/echartBar.js"></script>
	<script type="text/javascript" src="/js/public/echartBaralias.js"></script>
	<script type="text/javascript" src="/js/public/echartLine.js"></script>
	<script type="text/javascript" src="/js/policybenefit/publicBenefit.js"></script>
	</head>
	<body>
		<#include "/header.ftl"/>
		<section class="section">
			<#include "/left.ftl"/>
			<div class="right">
			<input type="hidden" id="modelId" value="${modelId}" />
			<input type="hidden" id="menuId"value="${menuId}" />
			<br>
			<#include "/query-province-less.ftl"/>
		<div class="rightBody">
            <div class="rightBody-big " >
                 <h5>公共服务环境指数
                  <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="每个月中央政策发布数量与关注度的数据统计">
						</div>
                 </h5>
                 <span class="rightBody-third-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>