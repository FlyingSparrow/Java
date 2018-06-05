 <header class="header">
        <!-- 页头start-->
        <div class="container-fluid">
            <div class="row header-por">
                <div class="col-md-2 col-xs-2">
            	<span class="header-logo">双创政策信息服务平台</span> 
                </div>
                <div class="col-md-7 col-xs-7 header-title-width">
                    <ul class="header-ul">
                    <#if modelId==0>
	                    <li class="choose"><a href="/energypolicy/investmentAnalysis.json">双创活力分析</a></li>
					<#else>
					  	<li class=""><a href="/energypolicy/investmentAnalysis.json">双创活力分析</a></li>
					</#if>
					<#if modelId==1>
	                   <li class="choose"><a href="/policyindex/capitalIndexAnalysis.json">双创指数分析</a></li>
					<#else>
					  	<li class=""><a href="/policyindex/capitalIndexAnalysis.json">双创指数分析</a></li>
					</#if>
					<#if modelId==2>
	                  <li class="choose"><a href="/policyoriented/policyText.json">双创政策导向分析</a></li>
					<#else>
					  	<li class=""><a href="/policyoriented/policyText.json">双创政策导向分析</a></li>
					</#if>
					<#if modelId==3>
	                  <li class="choose"><a href="/policyhot/areaHot.json">双创政策热点分析</a></li>
					<#else>
					  <li class=""><a href="/policyhot/areaHot.json">双创政策热点分析</a></li>
					</#if>
					<#if modelId==4>
	                  <li class="choose"><a href="/policybenefit/policyBenefit.json">双创政策效益分析</a></li>
					<#else>
					  <li class=""><a href="/policybenefit/policyBenefit.json">双创政策效益分析</a></li>
					</#if>
                       
                    </ul>
                </div>
                 <div class="header-right col-md-3 col-xs-3">
                    <span class="header-right-trumpet">
                    </span> 
                    <span class="header-right-feedback">
                     </span> 
                     <a href="javascript:void(0);">微博，微信</a>  
                </div>
            </div>
        </div>  
        <div class="float-right">
			<a class="float-right-a a-normal">${floatName}</a>
			<div class="float-right-div">
			<#if (datas?size > 0) >
				<#list datas  as  data>
				<div class="div-hover">
 				<p><a target="_blank" href="${data.policyUrl}">${data_index+1}、${data.policyTitle}</a></p>
					<#if (data.time?length > 10) >
                        <p><span>时间：${data.time[0..10]}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span></span></p>
					<#else>
                        <p><span>时间：${data.time}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span></span></p>
					</#if>
				</div> 
				</#list>
			<#else>
			 	<div class="div-hover">
 				<p>暂无数据</p>
				</div> 
			</#if>
			</div>
		</div>
</header>
