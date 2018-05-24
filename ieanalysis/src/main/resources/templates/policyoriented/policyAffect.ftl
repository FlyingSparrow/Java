<!DOCTYPE html> 
<html lang="en">
	<head>
	<#include "/public.ftl"/>
	<script type="text/javascript" src="/js/public/query.js"></script>
	<script type="text/javascript" src="/js/public/echartPie.js"></script>
	<script type="text/javascript" src="/js/policyoriented/policyaffect.js"></script>
	</head>
	<body>
		<#include "/header-float.ftl"/>
		<section class="section">
			<#include "/left.ftl"/>
			<div class="right">
				<input type="hidden" id="modelId" value="${modelId}" />
				<input type="hidden" id="menuId"value="${menuId}" />
				<div class="che-right-h3 clearfix">
	              <h3>政策影响信息</h3>
	            </div>
				<div class="rightBody">
					<div class="rightBody-big">
		                 <h5>招聘量和企业注册量
		                 <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="全国每个月新企业注册数量的统计以及企业招聘的岗位数统计">
						</div>
		                 </h5>
		                 <span class="rightBody-first-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
			        </div> 
		            <div class="rightBody-big">
		                <h5>招聘量行业分布 
		                <div class="tishi"> 
					    <img src="/images/tishi.png" onmouseenter="tishi(this)" onmouseleave="xiaoshi(this)" 
							    title="招聘数量前5名的行业">
						</div>
		                </h5>
		                <span class="rightBody-second-one"><div class="no-data"><span><img src="/images/noData.png" alt=""></span></div></span>
		            </div>
	         	</div>
         	</div>
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>