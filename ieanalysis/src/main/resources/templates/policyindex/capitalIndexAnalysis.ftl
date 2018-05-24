<!DOCTYPE html> 
<html lang="en">
	<head>
	<#include "/public.ftl"/>
	<script type="text/javascript" src="/js/public/query.js"></script>
	<script type="text/javascript" src="/js/public/query-province.js"></script>
	<script type="text/javascript" src="/js/public/echartLine.js"></script>
	<script type="text/javascript" src="/js/policyindex/capitalIndexAnalysis.js"></script>
	</head>
	<body>
		<#include "/header.ftl"/>
		<section class="section">
			<#include "/left.ftl"/>
			<div class="right">
			<input type="hidden" id="modelId" value="${modelId}" />
			<input type="hidden" id="menuId" value="${menuId}" />
			<br>
			 <#include "/query-province.ftl"/>
		<div class="rightBody">
             <div class="rightBody-big">
                 <h5>融资金额
                  <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="区域创新企业每年某个月的融资金额统计">
				  </div>
                 </h5>
                 <span class="rightBody-first-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
             <div class="rightBody-big">
                 <h5>并购金额
                  <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="区域创新企业每年某个月的并购金额统计">
				  </div>
                 </h5>
                 <span class="rightBody-second-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
             <div class="rightBody-big">
                 <h5>退出金额
                  <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="区域创新企业每年某个月的退出金额统计">
				  </div>
                 </h5>
                 <span class="rightBody-third-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
         </div> 
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>