package randy.web.domain;

import randy.core.j2ee.domain.AbstractDomain;

/**
 * 기획전 도메인.
 * 
 * @author jace
 */
public class Promotion extends AbstractDomain {

	/** 기획전명 */
	private String prmName;
	/** 기획전 썸네일 URL */
	private String prmThumbUrl;
	/** 기획전 URL */
	private String prmUrl;
	/** 기획전 혜택 */
	private String prmBenefit;
	/** 기획전 기간 */
	private String prmPeriod;
	/** 카테고리 아이디 */
	private Integer cateId;
	/** 몰 아이디 */
	private Integer mallId;

	public String getPrmName() {
		return prmName;
	}

	public void setPrmName(String prmName) {
		this.prmName = prmName;
	}

	public String getPrmThumbUrl() {
		return prmThumbUrl;
	}

	public void setPrmThumbUrl(String prmThumbUrl) {
		this.prmThumbUrl = prmThumbUrl;
	}

	public String getPrmUrl() {
		return prmUrl;
	}

	public void setPrmUrl(String prmUrl) {
		this.prmUrl = prmUrl;
	}

	public String getPrmBenefit() {
		return prmBenefit;
	}

	public void setPrmBenefit(String prmBenefit) {
		this.prmBenefit = prmBenefit;
	}

	public String getPrmPeriod() {
		return prmPeriod;
	}

	public void setPrmPeriod(String prmPeriod) {
		this.prmPeriod = prmPeriod;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public Integer getMallId() {
		return mallId;
	}

	public void setMallId(Integer mallId) {
		this.mallId = mallId;
	}

}
