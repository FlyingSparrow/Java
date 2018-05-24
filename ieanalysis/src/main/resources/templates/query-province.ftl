<div class="div-form">
<form id="formParam" method="post" class="formParam">
	<!-- 基础查询开始 -->
	<div class="normalSelect">    		
		<div>
			<span><a href="javascript:;">区域:</a></span>
			<ul class="queryProvinceSelect">
			<#list provinceData  as  provice>
				 <li><a href="javascript:;" >${provice}</a></li>
			</#list>
			</ul> 
		</div>
		<div>
			<span><a href="javascript:;">时间 :</a></span>
			 <ul class="queryYearSelect">
			</ul>
		</div> 
	</div> 
</form>
</div>
