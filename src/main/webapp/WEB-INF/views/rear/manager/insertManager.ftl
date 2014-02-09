<head>
	<title>관리자 등록</title>
	<script type="text/javascript">
		$(function() {
			var URI_PREFIX = '/rear/manager/';
			
			$('form').validate({
				rules: {
					siteId: {required: true},
					mgrId: {
						required: true,
						minlength: 4,
						engnum: true,
						remote: {
							url: URI_PREFIX + 'duplecheck',
							type: 'post',
							data: {
								mgrId: function() {
									return $('#mgrId').val()
								}
							}
						}
					},
					mgrName: {required: true},
					password: {
						required: true,
						minlength: 4
					},
					password2: {
						required: true,
						equalTo: '#password'
					}
				},
				messages: {
					siteId: '사이트를 선택해 주십시오.',
					mgrId: {
						required: '관리자 아이디를 입력해 주십시오.',
						remote: '이미 사용중인 아이디 입니다.'
					},
					mgrName: '관리자명을 입력해 주십시오.',
					password: '비밀번호를 입력해 주십시오..',
					password2: {
						required: '비밀번호를 다시 한번 입력해 주십시오.',
						equalTo: '비밀번호가 일치 하지 않습니다.'
					}
				},
				submitHandler: function (frm) {
					$(frm).ajaxForm({
						target: Common.getContentWrapper(),
						success: function() {
							Common.message(Common.info, '정상적으로 처리되었음.');
						}
					});
            	}
			});
		});
	</script>	
</head>
<body>
	<div class="row-fluid">		
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2><i class="icon-user"></i> 관리자 등록</h2>
			</div>
			<div class="box-content">
				<form id="insertForm" name="insertForm" method="post" action="insert" class="form-horizontal">
				<fieldset>			
					<div class="control-group">
						<label class="control-label" for="mgrId">관리자 아이디</label>
						<div class="controls">
							<input id="mgrId" name="mgrId" type="text">
						</div>									
					</div>
					<div class="control-group">
						<label class="control-label" for="mgrName">관리자 명</label>
						<div class="controls">
							<input id="mgrName" name="mgrName" type="text">
						</div>									
					</div>
					<div class="control-group">
						<label class="control-label" for="password">비밀번호</label>
						<div class="controls">
							<input id="password" name="password" type="password">
						</div>									
					</div>
					<div class="control-group">
						<label class="control-label" for="password2">비밀번호 확인</label>
						<div class="controls">
							<input id="password2" name="password2" type="password">
						</div>									
					</div>										
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">등록</button>
						<button class="btn" onclick="history.back(); return false;">취소</button>
					</div>														
				</fieldset>
				</form>								
			</div>
		</div>
	</div>
	
</body>
