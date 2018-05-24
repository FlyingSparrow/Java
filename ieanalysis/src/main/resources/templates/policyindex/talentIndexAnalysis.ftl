<!DOCTYPE html> 
<html lang="en">
	<head>
	<#include "/public.ftl"/>
	<script type="text/javascript" src="/js/public/query.js"></script>
	<script type="text/javascript" src="/js/public/query-province.js"></script>
	<script type="text/javascript" src="/js/public/echartBar.js"></script>
	<script type="text/javascript" src="/js/policyindex/talentIndexAnalysis.js"></script>
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
                 <h5>网络招聘岗位数
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="区域创新企业每个月招聘的岗位数统计">
				  </div>
                  </h5>
                 <span class="rightBody-first-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
             <div class="rightBody-big">
                 <h5>网络招聘平均薪酬
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="区域高新企业每月招聘人才的平均薪酬">
				  </div>
                 </h5>
                 <span class="rightBody-second-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
             <div class="rightBody-big">
                 <h5>求职热度 
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="2016全国各季度求职热度">
				  </div>
                 </h5>
                 <span class="rightBody-three-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
             <div class="rightBody-big">
                 <h5>双高人才比例
                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
					    title="区域当月招聘高学历高薪资的人才数量">
				  </div>
                  </h5>
                 <span class="rightBody-four-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
             </div>
         </div> 
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>