<!DOCTYPE html> 
<html lang="en">
	<head>
		<#include "/public.ftl"/>
	<script type="text/javascript" src="/js/public/query.js"></script>
	<script type="text/javascript" src="/js/public/echartBar.js"></script>
	<script type="text/javascript" src="/js/public/echartLine.js"></script>
	<script type="text/javascript" src="/js/energypollicy/registerAmountAnalysis.js"></script>
	</head>
	<body>
		<#include "/header-float.ftl"/>
		<section class="section">
			<#include "/left.ftl"/>
			<div class="right">
			<input type="hidden" id="modelId" value="${modelId}" />
			<input type="hidden" id="menuId"value="${menuId}" />
			<br>
			 <#include "/query-time.ftl"/>
		<div class="rightBody">
             <div class="rightBody-big">
                 <h5>新企业注册量统计 
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="2016年全国每个月新企业注册的数量">
				  </div>
                 </h5>
                 <span class="rightBody-first-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
             <div class="rightBody-big">
                 <h5>新企业注册增长率
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="2016年全国新企业每月注册量的增长率">
				  </div>
                  </h5>
                 <span class="rightBody-first-two"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
         </div> 
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>