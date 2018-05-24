<!DOCTYPE html> 
<html lang="en">
	<head>
	<#include "/public.ftl"/>
	<script type="text/javascript" src="/js/public/query.js"></script>
	<script type="text/javascript" src="/js/public/query-province.js"></script>
	<script type="text/javascript" src="/js/public/echartPieEmotion.js"></script>
	<script type="text/javascript" src="/js/public/echartBaralias.js"></script>
	<script type="text/javascript" src="/js/policyhot/mediaParticipation.js"></script>
	</head>
	<body>
		<#include "/header-float.ftl"/>
		<section class="section">
			<#include "/left.ftl"/>
			<div class="right">
			<input type="hidden" id="modelId" value="${modelId}" />
			<input type="hidden" id="menuId"value="${menuId}" />
			<br>
			 <#include "/query-province.ftl"/>
		<div class="rightBody">
             <div class="rightBody-small">
                <div>
                    <h5 id="volumeTrendTitle">双创政策媒体渠道倾向性分布
                     <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="双创政策媒体渠道情感占比分析">
						</div>
                    </h5>
                    <span class="rightBody-first-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
                </div>
                <div class="rightBody-switchover">
                    <h5 id="volumeTrendTitle">各大媒体渠道政策参与度排行
                     <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="报道双创政策阅读量最多的前十名">
						</div>
                    </h5>
                    <span class="rightBody-first-two"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
                </div>
         	</div>
            <div class="rightBody-big" >
                 <h5>各大媒体渠道倾向性分布统计
                  <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="各大媒体渠道情感分析占比">
						</div>
                 </h5>
                 </h5>
                 <span class="rightBody-second-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
         </div> 
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>