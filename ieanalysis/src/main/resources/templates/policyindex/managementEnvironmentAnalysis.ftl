<!DOCTYPE html> 
<html lang="en">
	<head>
	<#include "/public.ftl"/>
	<script type="text/javascript" src="/js/public/query.js"></script>
	<script type="text/javascript" src="/js/public/query-province.js"></script>
	<script type="text/javascript" src="/js/public/echartRadar.js"></script>
	<script type="text/javascript" src="/js/public/echartBar.js"></script>
	<script type="text/javascript" src="/js/policyindex/managementEnvironment.js"></script>
	</head>
	<body>
		<#include "/header.ftl"/>
		<section class="section">
			<#include "/left.ftl"/>
			<div class="right">
			<input type="hidden" id="modelId" value="${modelId}" />
			<input type="hidden" id="menuId"value="${menuId}" />
			<br>
			<#include "/query-province.ftl"/>
		<div class="rightBody">
             <div class="rightBody-big">
                 <h5>经营环境指数 
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="区域某一年中央政策、地方政策的发布数、关注度、报道数、知识产权保护诉讼的数量分布">
				  </div>
                 </h5>
                 <span class="rightBody-first-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
             <div class="rightBody-small">
                <div>
                    <h5 id="volumeTrendTitle">中央政策发布数量
                    <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="区域某一年每个月关于中央双创政策发布的数量统计">
				  </div>
                    </h5>
                    <span class="rightBody-second-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
                </div>
                <div class="rightBody-switchover">
                    <h5 id="volumeTrendTitle">地方政策发布数量
                    <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="区域某一年每个月关于地方双创政策发布的数量统计">
				  </div>
                    </h5>
                    <span class="rightBody-second-two"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
                </div>
         	</div>
              <div class="rightBody-small">
                <div> 
                    <h5 id="volumeTrendTitle">知识产权保护诉讼数量
                    <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="2016年每个月关于知识产权保护诉讼数量的统计">
				  </div>
                    </h5>
                    <span class="rightBody-third-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
                </div>
                <div class="rightBody-switchover">
                    <h5 id="volumeTrendTitle">报道数/关注度
                    <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="区域某一年每个月关于双创政策的报道数量与关注度统计">
				  </div>
                    </h5>
                    <span class="rightBody-third-two"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
                </div>
         	</div>
         </div>
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>