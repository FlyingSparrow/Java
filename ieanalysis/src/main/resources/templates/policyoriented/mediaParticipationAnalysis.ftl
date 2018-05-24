<!DOCTYPE html> 
<html lang="en">
	<head>
 	<#include "/public.ftl"/>
 	<script type="text/javascript" src="/lib/echarts/bmap.min.js"></script>
	<script type="text/javascript" src="/lib/echarts/map/js/china.js"></script>
	<script type="text/javascript" src="/js/public/thermodynamicMap.js"></script>
	<script type="text/javascript" src="/js/public/echartBar.js"></script> 
	<script type="text/javascript" src="/js/public/echartBaralias.js"></script>
	<script type="text/javascript" src="/js/public/echartMapNo.js"></script>
	<script type="text/javascript" src="/js/policyoriented/mediaParticipation.js"></script>
	</head>
	<body>
		<#include "/header-float.ftl"/>
		<section class="section">
			<#include "/left.ftl"/>
			<div class="right">
			<input type="hidden" id="modelId" value="${modelId}" />
			<input type="hidden" id="menuId"value="${menuId}" />
		<div class="rightBody">
             <div class="rightBody-big rightBody-big-map" >
                 <h5><a href="javascript:void(0);">媒体参与地图</a>|<a  href="javascript:void(0);" class="a-change-color">社交参与地图</a>
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="网络媒体与社交媒体关于双创政策参与度的地域分布热力图">
						</div>
                 </h5>
                 <span class="rightBody-first-one rightBody-big-span"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
            <div class="rightBody-small">
                <div>
                    <h5 id="volumeTrendTitle">媒体倾向
                    <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="网络媒体对于双创政策情感的分析统计">
						</div>
                    </h5>
                    <span class="rightBody-second-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
                </div> 
                <div class="rightBody-switchover">
                    <h5 id="volumeTrendTitle">社交倾向
                    <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="微信用户对于双创政策的情感分析统计">
						</div>
                    </h5>
                    <span class="rightBody-second-two"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
                </div>
         	</div>
              
         </div>
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>