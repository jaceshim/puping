package randy.web.support.async;

public enum AsyncStatus {

	/** 성공 */
	SUCCESS("success"),
	/** 에러 */
	ERROR("error");

	private String status;

	private AsyncStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
}
