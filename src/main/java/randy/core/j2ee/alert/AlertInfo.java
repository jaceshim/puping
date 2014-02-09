package randy.core.j2ee.alert;

import java.util.Map;

import randy.core.j2ee.domain.AbstractDomain;

/**
 * Alert처리 정보.
 * 
 * @author jace
 */
public class AlertInfo extends AbstractDomain {

	/** 리다이렉트 URL */
	private String redirectUrl;
	/** alert 처리 유형 */
	private AlertType alertType;
	/** redirect시 전달될 파라미터 */
	private Map<String, Object> params;
	/** confirm창 취소시 이동 url */
	private String confirmCancelUrl;
	/** form submit처리시 target */
	private String target;
	/** alert처리 메세지 */
	private String message;
	/** alert처리 메세지 구성 argument */
	private String[] messageArgs;

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public AlertType getAlertType() {
		return alertType;
	}

	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getConfirmCancelUrl() {
		return confirmCancelUrl;
	}

	public void setConfirmCancelUrl(String confirmCancelUrl) {
		this.confirmCancelUrl = confirmCancelUrl;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getMessageArgs() {
		return messageArgs;
	}

	public void setMessageArgs(String[] messageArgs) {
		this.messageArgs = messageArgs;
	}

}
