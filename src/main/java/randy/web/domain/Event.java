package randy.web.domain;

import randy.core.j2ee.domain.AbstractDomain;

/**
 * 이벤트 도메인.
 * 
 * @author jace
 */
public class Event extends AbstractDomain {

	/** 이벤트 명 */
	private String evtName;
	/** 이벤트 URL */
	private String evtUrl;
	/** 이벤트 혜택 */
	private String evtBenefit;
	/** 이벤트 기간 */
	private String evtPeriod;
	/** 이벤트 썸네일 URL */
	private String evtThumbUrl;
	/** 몰 아이디 */
	private Integer mallId;
	/** 카테고리 아이디 */
	private Integer cateId;

	public String getEvtName() {
		return evtName;
	}

	public void setEvtName(String evtName) {
		this.evtName = evtName;
	}

	public String getEvtUrl() {
		return evtUrl;
	}

	public void setEvtUrl(String evtUrl) {
		this.evtUrl = evtUrl;
	}

	public String getEvtBenefit() {
		return evtBenefit;
	}

	public void setEvtBenefit(String evtBenefit) {
		this.evtBenefit = evtBenefit;
	}

	public String getEvtPeriod() {
		return evtPeriod;
	}

	public void setEvtPeriod(String evtPeriod) {
		this.evtPeriod = evtPeriod;
	}

	public String getEvtThumbUrl() {
		return evtThumbUrl;
	}

	public void setEvtThumbUrl(String evtThumbUrl) {
		this.evtThumbUrl = evtThumbUrl;
	}

	public int getMallId() {
		return mallId;
	}

	public void setMallId(int mallId) {
		this.mallId = mallId;
	}

	public int getCateId() {
		return cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

}
