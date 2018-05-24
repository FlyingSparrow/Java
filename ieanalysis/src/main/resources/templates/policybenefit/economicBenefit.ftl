<!DOCTYPE html> 
<html lang="en">
	<head>
	<#include "/public.ftl"/>
	<script type="text/javascript" src="/js/public/query.js"></script>
	<script type="text/javascript" src="/js/public/query-province-less.js"></script>
	<script type="text/javascript" src="/js/public/echartBar.js"></script>
	<script type="text/javascript" src="/js/public/echartBaralias.js"></script>
	<script type="text/javascript" src="/js/public/echartLine.js"></script>
	<script type="text/javascript" src="/js/policybenefit/economicBenefit.js"></script>
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
         	<div class="rightBody-small">
                <div>
                    <h5 id="volumeTrendTitle">全国投资地区top5
                    <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="全国对外投资金额前五名金额统计">
						</div>
                    </h5>
                    <span class="rightBody-second-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
                </div>
                <div class="rightBody-switchover">
                    <h5 id="volumeTrendTitle">投资行业Top5
                    <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="对外投资前五名的金额统计">
						</div>
                    </h5>
                    <span class="rightBody-second-two"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
                </div>
         	</div>
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>