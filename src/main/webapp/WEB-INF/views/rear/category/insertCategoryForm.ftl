<head>

	<script type="text/javascript">
		function insertCategory() {
			var form = document.insertForm;
			if (form.cateName.value == '') {
				alert('카테고리명을 입력해 주십시오.');
				form.cateName.focus();
				return;
			}
			
			form.action = '/rear/category/insert';
			form.submit();
		}
	</script>
</head>
<body>

	<form id="insertForm" name="insertForm" class="form-horizontal" method="post">
		<input type="hidden" name="pcateId" value="${RequestParameters.pcateId!''}" >
	<fieldset>
		<div class="control-group">
			<label class="control-label" for="siteId">카테고리명</label>
			<div class="controls">
				<input id="cateName" name="cateName" style="width: 150px;" type="text">
			</div>									
		</div>
		<div class="row-fluid" style="text-align: right; padding-right: 10px;">
			<button class="btn btn-primary" onclick="insertCategory(); return false;">등록</button>	
		</div>
	</fieldset>
	</form>
	


</body>