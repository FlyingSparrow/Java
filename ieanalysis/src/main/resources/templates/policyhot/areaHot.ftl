<!DOCTYPE html> 
<html lang="en">
	<head>
	<#include "/public.ftl"/>
	<script type="text/javascript" src="/lib/echarts/bmap.min.js"></script>
	<script type="text/javascript" src="/lib/echarts/map/js/china.js"></script>
	<script type="text/javascript" src="/js/public/thermodynamicMap.js"></script>
	<script type="text/javascript" src="/js/public/query.js"></script>
	<script type="text/javascript" src="/js/public/echartBar.js"></script>
	<script type="text/javascript" src="/js/public/echartMapNo.js"></script>
	<script type="text/javascript" src="/js/policyhot/areaHot.js"></script>
	</head>
	<body>
		<#include "/header.ftl"/>
		<section class="section">
			<#include "/left.ftl"/>
			<div class="right">
			<input type="hidden" id="modelId" value="${modelId}" />
			<input type="hidden" id="menuId"value="${menuId}" />
			<br>
			 <#include "/query-time-select.ftl"/>
		<div class="rightBody">
             <div class="rightBody-big rightBody-big-map" >
                 <h5>区域分布图
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="双创政策区域排行关注度地域分布热力图">
						</div>
                 </h5>
                 <span class="rightBody-first-one rightBody-big-span"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
            <div class="rightBody-small">
                <div>
                    <h5 id="volumeTrendTitle">地区关注排行
                    <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="双创政策关注度前10名统计">
						</div>
                    </h5>
                    <span class="rightBody-second-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
                </div>
                <div class="rightBody-switchover">
                    <h5 id="volumeTrendTitle">行业关注分布
                    <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="关注双创政策前5名的行业统计">
						</div></h5>
                    <span class="rightBody-second-two"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
                </div>
         	</div>
              
         </div>
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>