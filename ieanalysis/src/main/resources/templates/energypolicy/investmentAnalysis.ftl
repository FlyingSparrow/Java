<!DOCTYPE html> 
<html lang="en">
	<head>
	<#include "/public.ftl"/>
	<script type="text/javascript" src="/js/public/query.js"></script>
	<script type="text/javascript" src="/js/public/query-province-less.js"></script>
	<script type="text/javascript" src="/js/public/echartBar.js"></script>
	<script type="text/javascript" src="/js/public/echartRadar.js"></script>
	<script type="text/javascript" src="/js/energypollicy/investmentAnalysis.js"></script>
	<#include "/header-float.ftl"/>
	</head>
	<body>
		<#include "/header-float.ftl"/>
		<section class="section">
			<#include "/left.ftl"/>
			<div class="right">
			<input type="hidden" id="modelId" value="${modelId}" />
			<input type="hidden" id="menuId"value="${menuId}" />
			<br>
			 <#include "/query-province-less.ftl"/>
		<div class="rightBody">
             <div class="rightBody-big">
                 <h5>区域投资总额及投资增长率分布 
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="区域每年每个月的投资金额，以及每个月的投资增长比率">
				</div>
				</h5>
                 <span class="rightBody-first-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
            <div class="rightBody-small">
                <div>
                    <h5 id="volumeTrendTitle">热门投资投资方向TOP5（全年）
                    <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="全年某一个区域投资金额最多的前五个行业">
				</div>
                    </h5>
                    <span class="rightBody-second-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
                </div>
                <div class="rightBody-switchover">
                    <h5 id="volumeTrendTitle">区域投资方向分布
                    <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="区域投资金额最多的前五个方向">
				  </div>
                    &nbsp;
                    <select id="queryMonthSelect"><option>2016年1月</option><option>2016年2月</option></select></h5>
                    <span class="rightBody-second-two"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
                </div>
         	</div>
         </div>
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>