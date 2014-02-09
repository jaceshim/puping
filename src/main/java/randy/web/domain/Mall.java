package randy.web.domain;

import randy.core.j2ee.domain.AbstractDomain;

/**
 * Mall 도메인.
 * 
 * @author jace
 */
public class Mall extends AbstractDomain {

	/** 몰 아이디 */
	private Integer mallId;
	/** 몰 이름 */
	private String mallName;
	/** 몰 URL */
	private String mallUrl;
	/** 스케쥴 주기 */
	private Integer taskInterval;

	public Integer getMallId() {
		return mallId;
	}

	public void setMallId(Integer mallId) {
		this.mallId = mallId;
	}

	public String getMallName() {
		return mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
	}

	public String getMallUrl() {
		return mallUrl;
	}

	public void setMallUrl(String mallUrl) {
		this.mallUrl = mallUrl;
	}

	public Integer getTaskInterval() {
		return taskInterval;
	}

	public void setTaskInterval(Integer taskInterval) {
		this.taskInterval = taskInterval;
	}

}
