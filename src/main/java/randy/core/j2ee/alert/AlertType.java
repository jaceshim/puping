package randy.core.j2ee.alert;

/**
 * Alert를 처리하고 이동하는 유형
 * 
 * @author jace
 */
public enum AlertType {
	/** 단순 페이지 이동 */
	GO_PAGE("go_page"),
	/** alert메세지 출력 후 history back.  */
	ALERT_AND_BACK("alert_and_back"),
	/** alert메세지 출력 후 창 닫음. */
	ALERT_AND_CLOSE("alert_and_close"),
	/** alert메세지 출력 후 페이지 이동. */
	ALERT_AND_GO("alert_and_go"),
	/** alert메세지 출력 후 post방식으로 페이지 이동. */
	ALERT_AND_POST("alert_and_post"),
	/** confirm 메세지 출력 후 페이지 이동. */
	CONFIRM_AND_GO("confirm_and_go"),
	/** confirm 메세지 출력 후 post방식으로 페이지 이동. */
	CONFIRM_AND_POST("confirm_and_post");

	private String type;

	private AlertType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
}
