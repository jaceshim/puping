<head>
	<title>${data.mgrName} - 수정</title>
	<script type="text/javascript">
		$(function() {
			var URI_PREFIX = '/rear/manager/';
			
			$('form').validate({
				rules: {
					siteId: {required: true},
					mgrName: {required: true},
				},
				messages: {
					siteId: '사이트를 선택해 주십시오.',
					mgrName: '관리자명을 입력해 주십시오.',
					password: '비밀번호를 입력해 주십시오.',
					password2: {
						required: '비밀번호를 다시 한번 입력해 주십시오.',
						equalTo: '비밀번호가 일치 하지 않습니다.'
					}
				},
				submitHandler: function (frm) {
					var msg = '입력하신 정보로 변경하시겠습니까?';
					if (confirm(msg)) {
						frm.submit();
					}
					return false;
            	}
			});
			
			
			$('#passwdChange').bind('change', function() {
				var $passwdArea = $('#wcsdev-password-area');
				if ($(this).is(':checked')) {
					$passwdArea.show();
					
					$('#password').rules('add', 'required');
					$('#password2').rules('add', {
						required: true,
						equalTo: '#password'
					});
					
				} else {
					$passwdArea.hide();
					$('#password').rules('remove', 'required');
					$('#password2').rules('remove', 'required equalTo');
				}
			})
		});
	</script>
</head>
<body>
	<div class="row-fluid">		
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2><i class="icon-user"></i> 관리자 수정</h2>
			</div>
			<div class="box-content">
				<form id="updateForm" name="updateForm" method="post" action="update" class="form-horizontal">
					<input type="hidden" name="mgrId" id="mgrId" value="${data.mgrId}">
				<fieldset>
					<#assign isSysAccount=Session.MANAGER_SESS_KEY.siteId == "sys">
					<#if isSysAccount>
					<div class="control-group">
						<label class="control-label" for="siteId">사이트</label>
						<div class="controls">
							<select id="siteId" name="siteId">
								<option value="">사이트 선택 ${data.siteId}</option>
								<#list siteList as item>
								<option value="${item.siteId}" <#if item.siteId == data.siteId>selected="selected"</#if> >${item.siteName}</option>
								</#list>
								<option value="-1" <#if data.siteId == "sys">selected="selected"</#if> >시스템관리자</option>
							</select>									
						</div>									
					</div>
					<#else>
					<input type="hidden" name="siteId" id="siteId" value="${Session.MANAGER_SESS_KEY.siteId}">
					</#if>		
					<div class="control-group">
						<label class="control-label" for="mgrId">관리자 아이디</label>
						<div class="controls">${data.mgrId}</div>									
					</div>
					<div class="control-group">
						<label class="control-label" for="mgrName">관리자 명</label>
						<div class="controls">
							<input id="mgrName" name="mgrName" type="text" value="${data.mgrName}">
						</div>									
					</div>
					<#if isSysAccount>
					<div class="control-group">
						<label class="control-label">계정 활성</label>
						<div class="controls">
							<select id="useYn" name="useYn">
								<option value="Y" <#if "Y" == data.useYn>selected="selected"</#if> >활성</option>
								<option value="N" <#if "N" == data.useYn>selected="selected"</#if> >비활성</option>
							</select>
						</div>									
					</div>						
					</#if>
					<div class="control-group">
						<label class="control-label">비밀번호 변경</label>
						<div class="controls">
							<label><input type="checkbox" name="passwdChange" id="passwdChange" >
							<span>비밀번호를 변경하시려면 체크 하십시오.</span></label>
						</div>									
					</div>												
					<div id="wcsdev-password-area" style="display: none;">		
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
					</div>											
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">수정</button>
						<button class="btn" onclick="history.back(); return false;">취소</button>
					</div>														
				</fieldset>
				</form>								
			</div>
		</div>
	</div>
</body>