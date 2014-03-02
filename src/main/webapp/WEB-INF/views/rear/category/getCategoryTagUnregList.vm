<head>
	<title>카테고리 미등록 태그 목록</title>
	<script type="text/javascript">
		var URI_PREFIX = '/rear/category/tag/';
	
		function insertCategoryTag(seq, cateId) {
			$.ajax({
					url: URI_PREFIX + 'unreg/update.ajax'
				,	dataType: 'json'
				,	data: 'seq=' + seq + '&cateId=' + cateId
			}).fail(function(err) {
				alert(err);
			}).done(function(res) {
				if (res.status == 'success') {
					alert('선택하신 미 등록 태그를 정상적으로 등록하였습니다.');
					document.location.href = document.location.href;
				}
			});
		}
		
		function jsList() {
			var form = document.cateForm;
			form.action = URI_PREFIX + 'unreg/list';
			form.submit();
		}
	</script>
</head>
<body>


	
</form>
<div class="box-content">
	
	
	<div class="row-fluid">
		<form name="cateForm" id="cateForm">
			<input type="hidden" name="pageNum" >
		<div class="span6">
			<span>몰 : </span>
			<select name="mallId" onchange="jsList()">
				<option value="">-- 선택 --</option>
				<#assign mallIdParam="-1">
				<#if !RequestParameters.mallId?exists || RequestParameters.mallId == "">
					<#assign mallIdParam="-1">
				<#else>
					<#assign mallIdParam=RequestParameters.mallId>
				</#if>
				<#list mallList as item>
				<option value="${item.mallId}" <#if mallIdParam?number == item.mallId>selected="selected"</#if> >${item.mallName}</option>
				</#list>
			</select>
		</div>
		</form>
	</div>
	

	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th width="60">순번</th>
				<th width="80">몰</th>
				<th>상품순번</th>
				<th>상품이미지</th>
				<th>상품명</th>
				<th>태그</th>
				<th width="40">처리여부</th>
				<th>등록일시</th>
				<th width="100">Action</th> 
			</tr>
		</thead>
		<tbody>
			<#list page.items as item>
			<tr>
				<td>${item.seq?c}</td>
				<td>${item.mall.mallName}</td>
				<td>${item.prdSeq?c}</td>
				<td><a href="${item.prdUrl!''}" target="_blank"><img src="${item.prdThumbUrl!'N/A'}"></a></td>
				<td><a href="${item.prdUrl!''}" target="_blank">${item.prdName!'N/A'}</a></td>
				<td>${item.tag}</td>
				<td>${item.procYn}</td>
				<td>${item.regDt}</td>
				<td>
					<div class="btn-group">
						<button type="button" class="btn btn-primary">태그등록</button>
						<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
						<ul class="dropdown-menu">
							<#assign tempPcateId=-1>
							<#list categoryList as cate>
								<#if tempPcateId != -1 && tempPcateId != cate.pcateId>
								<li class="divider"></li>
								</#if>
								<li>
									<a href="#" onclick="insertCategoryTag(${item.seq?c}, ${cate.cateId?c});">
										<#if 1 < cate.level>
											&nbsp;&nbsp;&nbsp;
										</#if>
										${cate.cateName}
									</a>
								</li>
								<#assign tempPcateId=cate.pcateId>
							</#list>
						</ul>
					</div>		
				</td>
			</tr>
			</#list>
			<#if !page.items?has_content>
			<tr>
				<td colspan="9">등록된 데이터가 없습니다.</td>
			</tr>
			</#if>
		</tbody>
	</table>
	
	${page.getPageHtml("cateForm")}
	
</div>

</body>




	