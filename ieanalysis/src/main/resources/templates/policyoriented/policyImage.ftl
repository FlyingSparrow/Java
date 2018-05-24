<!DOCTYPE html> 
<html lang="en">
	<head>
	<#include "/public.ftl"/>
	<script type="text/javascript" src="/js/public/query.js"></script>
	<script type="text/javascript" src="/lib/jqpaginator/jqPaginator.min.js"></script>
	<script type="text/javascript" src="/js/policyoriented/policyimage.js"></script>
	<style>
	
	</style>
	</head>
	<body>
		<#include "/header-float.ftl"/>
		<section class="section">
			<#include "/left.ftl"/>
			<div class="right">
			<input type="hidden" id="modelId" value="${modelId}" />
			<input type="hidden" id="menuId"value="${menuId}" />
			<div class="che-right-h3 clearfix">
              <h3>政策图解信息</h3>
            </div>
			<div class="rightBody">
		         <div id="imageDiv">
		         </div>   
				<div class="PageDiv clearfix" style="display: block;"> 
					<ul id="paging" class="pagination paging-div " >
					</ul>
				</div>  
         	</div> 
         	</div>
			<#include "/footer.ftl"/>
		</section>
	</body>
</html>