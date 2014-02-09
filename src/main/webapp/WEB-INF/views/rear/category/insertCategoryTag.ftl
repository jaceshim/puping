<head>
<title>카테고리 태크 등록.</title>

<script type="text/javascript">
	function getCategoryList(pcateId) {
		if (pcateId != '') {
			$.ajax({
					url: '/api/get/category?pcateId=' + pcateId
				,	dataType: 'json'
			}).error(function( err ) {
				alert(err);
			}).done(function( result ) {
				var appendHtml = '<select name="subCateId" id="subCateId">';
				appendHtml += '<option value="">선택</option>';
				var cateList = result.data;
				$.each(cateList, function(i, item) {
					
					appendHtml += '<option value="' + item.cateId + '">' + item.cateName + '</option>';
				});
				appendHtml += '</select>';
				$('#subCateArea').html(appendHtml);			
			});		
		}
	}
	
	function insertCategory() {
		var form = document.cateForm;
		if (form.pcateId.value == '') {
			alert('카테고리를 선택해 주십시오.');
			return;
		} else if (form.tag.value == '') {
			alert('태그명을 입력해 주십시오.');
			return;
		}
		
		if (form.subCateId.value == '') {
			if (confirm('최 상위 카테고리에 태그를 등록하시겠습니까?')) {
				form.cateId.value = form.pcateId.value;
			} else {
				alert('하위 카테고리를 선택해 주십시오.');
				return;
			}
		} else {
			form.cateId.value = form.subCateId.value;
		}
		
		form.action = 'insert';
		form.submit();
	}
</script>
</head>
<body>
	
	<form id="cateForm" name="cateForm" method="post">
		<input type="hidden" id="cateId" name="cateId" />
	<div id="cateArea" style="padding: 10px; border: 1px solid blue;">
	
	<select id="pcateId" name="pcateId" onchange="getCategoryList(this.value)">
		<option value="">선택</option>
		<#list categoryList as item>
		<option value="${item.cateId}">${item.cateName}</option>
		</#list>
	</select>
	
	<div id="subCateArea" style="display: inline;">
		
	</div>
	
	</div>
	
	<br />
	
	<input type="text" id="tag" name="tag" /> <br />
	
	<button onclick="insertCategoryForm(); return false;">등록</button>
	
	</form>
</body>