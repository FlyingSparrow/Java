<!DOCTYPE html> 
<html lang="en">
	<head>
	<#include "/public.ftl"/>
	<script type="text/javascript" src="/js/public/query.js"></script>
	<script type="text/javascript" src="/js/public/query-province-less.js"></script>
	<script type="text/javascript" src="/js/public/echartLine.js"></script>
	<script type="text/javascript" src="/js/public/echartBar.js"></script>
	<script type="text/javascript" src="/js/policyindex/healthDegreeAnalysis.js"></script>
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
                 <h5>企业核心竞争力--发明专利数
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="2016年全国创新企业每月产品数量的统计">
				  </div>
                 </h5>
                 <span class="rightBody-first-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
             <div class="rightBody-big">
                 <h5>企业市场力--企业营收总额
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="2016年度全国创新企业季度盈利金额统计">
				  </div>
                 </h5>
                 <span class="rightBody-second-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
             <div class="rightBody-big">
                 <h5>企业创新创造力--企业利润总额
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="2016年度创新企业季度营收总额统计">
				  </div>
                 </h5>
                 <span class="rightBody-three-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
         </div> 
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>