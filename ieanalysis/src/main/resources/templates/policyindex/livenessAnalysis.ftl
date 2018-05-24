<!DOCTYPE html> 
<html lang="en">
	<head>
	<#include "/public.ftl"/>
	<script type="text/javascript" src="/js/public/query.js"></script>
	<script type="text/javascript" src="/js/public/query-province-less.js"></script>
	<script type="text/javascript" src="/js/public/echartBar.js"></script>
	<script type="text/javascript" src="/js/policyindex/liveness.js"></script>
	</head>
	<body>
		<#include "/header.ftl"/>
		<section class="section">
			<#include "/left.ftl"/>
			<div class="right">
			<input type="hidden" id="modelId" value="${modelId}" />
			<input type="hidden" id="menuId"value="${menuId}" />
			<br>
			<#include "/query-time.ftl"/>
		<div class="rightBody">
             <div class="rightBody-big">
                 <h5>注册企业数量增长率
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="2016年全国每个月新企业的注册增长率变化">
				  </div>
                 </h5>
                 <span class="rightBody-first-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
             <div class="rightBody-big">
                 <h5>注册企业资金总额
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="2016年全国新企业每个月注册资金的总额统计">
				  </div>
                 </h5>
                 <span class="rightBody-second-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
         </div> 
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>