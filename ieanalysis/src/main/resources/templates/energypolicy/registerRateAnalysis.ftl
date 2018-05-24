<!DOCTYPE html> 
<html lang="en">
	<head>
	<#include "/public.ftl"/>
	<script type="text/javascript" src="/js/public/query.js"></script>
	<script type="text/javascript" src="/js/public/query-province-less.js"></script>
	<script type="text/javascript" src="/js/public/echartPie.js"></script>
	<script type="text/javascript" src="/js/public/echartBar.js"></script>
	<script type="text/javascript" src="/js/energypollicy/registerRateAnalysis.js"></script>
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
			<div class="rightBody-small">
	                <div>
	                    <h5 id="volumeTrendTitle">新企业注册量占比
	                    <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="2016年全国新企业注册量的季度占比">
				  </div>
	                    </h5>
	                    <span class="rightBody-first-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
	                </div>
	                <div class="rightBody-switchover">
	                    <h5 id="volumeTrendTitle">新企业注册数量
	                    <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="2016全国年每一个季度新企业注册的数量的统计">
				  		</div>
	                    </h5>
	                    <span class="rightBody-first-two"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
	                </div>
	         </div>
             <div class="rightBody-big">
                 <h5>新企业注册数量和众创空间量
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="2016年全国新企业注册的数量与重创空间的数量统计">
				  </div>
			</h5>
                 <span class="rightBody-second-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
         </div> 
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>