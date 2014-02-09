<head>
	<title>카테고리 태그 목록</title>
	<script type="text/javascript">
		var URI_PREFIX = '/rear/category/tag/';
	
		function jsList() {
			var form = document.cateForm;
			form.action = URI_PREFIX + 'list';
			form.submit();
		}
	</script>
</head>
<body>

<div class="box-content">

	<div class="row-fluid">
		<form name="cateForm" id="cateForm">
			<input type="hidden" name="pageNum" >
		<div class="span6">
			<span>카테고리 : </span>
			<select name="cateId" onchange="jsList()">
				<option value="">-- 선택 --</option>
				<#assign cateIdParam="-1">
				<#if !RequestParameters.cateId?exists || RequestParameters.cateId == "">
					<#assign cateIdParam="-1">
				<#else>
					<#assign cateIdParam=RequestParameters.cateId>
				</#if>
				<#list categoryList as item>
				<option value="${item.cateId}" <#if cateIdParam?number == item.cateId>selected="selected"</#if> >${item.cateName}</option>
				</#list>
			</select>
		</div>
		</form>
	</div>


	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th style="width:60px;">순번</th>
				<th style="width:120px;">카테고리 아이디</th>
				<th>카테고리 명</th>
				<th>태그</th>
				<th>Action</th> 
			</tr>
		</thead>
		<tbody>
			<#list page.items as item>
			<tr>
				<td>${item.seq}</td>
				<td>${item.cateId}</td>
				<td>${item.cateName}</td>
				<td>${item.tag}</td>
				<td>&nbsp;</td>
			</tr>
			</#list>
			<#if !page.items?has_content>
			<tr>
				<td colspan="5">등록된 데이터가 없습니다.</td>
			</tr>
			</#if>
		</tbody>
	</table>
	
	${page.getPageHtml("cateForm")}
	
</div>

</body>




	