<div class="che-left">
	<ul class="che-left-ul">
	<#if modelId==0>
		<li class="left-active">
		<img src="/images/energypolicy/touzifennxi.png" alt="">
			<a href="/energypolicy/investmentAnalysis.json">投资分析</a>
		</li>
		<li class="left-normal">
		<img src="/images/energypolicy/zhuceliangfenxi.png" alt="">
			<a href="/energypolicy/registerAmountAnalysis.json">注册量分析</a>
		</li>
		<li class="left-normal">
		<img src="/images/energypolicy/zhucexiaolv.png" alt="">
			<a href="/energypolicy/registerRateAnalysis.json">注册效率分析</a>
		</li>
	<#elseif modelId==1>
		<li class="left-active">
		<img src="/images/policyindex/capitalIndex.png" alt="">
			<a href="/policyindex/capitalIndexAnalysis.json">资本指数分析</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyindex/talentIndex.png" alt="">
			<a href="/policyindex/talentIndexAnalysis.json">人才指数分析</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyindex/healthDegree.png" alt="">
			<a href="/policyindex/healthDegreeAnalysis.json">健康度指数分析</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyindex/managementEnvironment.png" alt="">
			<a href="/policyindex/managementEnvironmentAnalysis.json">经营环境指数分析</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyindex/liveness.png" alt="">
			<a href="/policyindex/livenessAnalysis.json">活跃度分析</a>
		</li>
	<#elseif modelId==2>
		<li class="left-active">
		<img src="/images/policyoriented/text.png" alt="">
			<a href="/policyoriented/policyText.json">政策文本信息采集</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyoriented/image.png" alt="">
			<a href="/policyoriented/policyImage.json">政策图解信息采集</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyoriented/video.png" alt="">
			<a href="/policyoriented/policyVideo.json">政策视频信息采集</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyoriented/policyaffect.png" alt="">
			<a href="/policyoriented/policyAffect.json">政策影响信息采集</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyoriented/reportcontent.png" alt="">
			<a href="/policyoriented/reportContent.json">具体报道内容分析</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyoriented/articletrend.png" alt="">
			<a href="/policyoriented/articleTrend.json">文章趋势分析</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyoriented/mediabiasanalysis.png" alt="">
			<a href="/policyoriented/mediaParticipationAnalysis.json">倾向性/参与性分析</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyoriented/specialeventshaft.png" alt="">
			<a href="/policyoriented/specialEventShaft.json">专题事件时间轴统计</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyoriented/hotkeyword.png" alt="">
			<a href="/policyoriented/hotKeyword.json">热点关键词统计</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyoriented/hotevent.png" alt="">
			<a href="/policyoriented/hotEvent.json">热点事件地点分布统计</a>
		</li>
	<#elseif modelId==3>
		<li class="left-active">
		<img src="/images/policyhot/area.png" alt="">
			<a href="/policyhot/areaHot.json">区域热点分析</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyhot/channel.png" alt="">
			<a href="/policyhot/focusChannel.json">关注渠道分析</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyhot/meida.png" alt="">
			<a href="/policyhot/mediaParticipation.json">媒体参与度分析</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyhot/social.png" alt="">
			<a href="/policyhot/socialParticipation.json">社会参与度分析</a>
		</li>
		<li class="left-normal">
		<img src="/images/policyhot/industry.png" alt="">
			<a href="/policyhot/industryParticipation.json">行业参与度分析</a>
		</li>
	<#elseif modelId==4>
		<li class="left-active">
		<img src="/images/policybenefit/touzi.jpg" alt="">
			<a href="/policybenefit/policyBenefit.json">投资效益分析</a>
		</li>
		<li class="left-normal">
		<img src="/images/policybenefit/jingji.jpg" alt="">
			<a href="/policybenefit/economicBenefit.json">经济效益分析</a>
		</li>
		<li class="left-normal">
		<img src="/images/policybenefit/benefit.png" alt="">
			<a href="/policybenefit/publicBenefit.json">公共服务执行效率分析</a>
		</li>
	<#else>
	  ...
	</#if>
				
	</ul>   
</div>