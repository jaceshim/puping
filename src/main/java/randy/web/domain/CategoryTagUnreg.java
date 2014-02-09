package randy.web.domain;

import randy.core.j2ee.domain.AbstractPageDomain;

/**
 * 카테고리 미 등록 태그.
 * 
 * @author jace
 */
public class CategoryTagUnreg extends AbstractPageDomain {

	/** 몰 정보 */
	private Mall mall;

	/** 순번 */
	private Integer seq;
	/** 미등록 태그를 발생시킨 상푼순번 */
	private Integer prdSeq;
	/** 몰 아이디 */
	private Integer mallId;
	/** 상품명 */
	private String prdName;
	/** 상품 썸네일 URL */
	private String prdThumbUrl;
	/** 상품 URL */
	private String prdUrl;
	/** 태그 */
	private String tag;
	/** 등록일시 */
	private String regDt;

	private Integer cateSeq;

	public Mall getMall() {
		return mall;
	}

	public void setMall(Mall mall) {
		this.mall = mall;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getPrdSeq() {
		return prdSeq;
	}

	public void setPrdSeq(Integer prdSeq) {
		this.prdSeq = prdSeq;
	}

	public Integer getMallId() {
		return mallId;
	}

	public void setMallId(Integer mallId) {
		this.mallId = mallId;
	}

	public String getPrdName() {
		return prdName;
	}

	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}

	public String getPrdThumbUrl() {
		return prdThumbUrl;
	}

	public void setPrdThumbUrl(String prdThumbUrl) {
		this.prdThumbUrl = prdThumbUrl;
	}

	public String getPrdUrl() {
		return prdUrl;
	}

	public void setPrdUrl(String prdUrl) {
		this.prdUrl = prdUrl;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public Integer getCateSeq() {
		return cateSeq;
	}

	public void setCateSeq(Integer cateSeq) {
		this.cateSeq = cateSeq;
	}

}
