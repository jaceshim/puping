package randy.web.domain;

import java.util.List;

import randy.core.j2ee.domain.AbstractPageDomain;

/**
 * 상품 도메인.
 * 
 * @author jace
 */
public class Product extends AbstractPageDomain {

	/** 카테고리 id */
	private List<Integer> cateIdList;

	/** 미등록 카테고리 태그. */
	private CategoryTagUnreg categoryTagUnreg;

	/** 상품순번 */
	private Integer prdSeq;
	/** 상품유형 ( 1: 인기상품, 2: 오늘 특가 상품 ) */
	private Integer prdType;
	/** 카테고리 순번 */
	private Integer cateSeq;
	/** mall id */
	private Integer mallId;
	/** 상품명 */
	private String prdName;
	/** 상품 본 아이디 */
	private String prdOrgId;
	/** 원 상품 가격 */
	private Integer prdOrgPrice;
	/** 상품 가격 */
	private Integer prdPrice;
	/** 상품 URL */
	private String prdUrl;
	/** 썸네일 URL */
	private String prdThumbUrl;
	/** 상품 카테고리 태그 */
	private String categoryTag;
	/** 사용여부 */
	private String regYn;
	/** 등록일시 */
	private String regDt;

	public List<Integer> getCateIdList() {
		return cateIdList;
	}

	public void setCateIdList(List<Integer> cateIdList) {
		this.cateIdList = cateIdList;
	}

	public CategoryTagUnreg getCategoryTagUnreg() {
		return categoryTagUnreg;
	}

	public void setCategoryTagUnreg(CategoryTagUnreg categoryTagUnreg) {
		this.categoryTagUnreg = categoryTagUnreg;
	}

	public Integer getPrdSeq() {
		return prdSeq;
	}

	public void setPrdSeq(Integer prdSeq) {
		this.prdSeq = prdSeq;
	}

	public Integer getPrdType() {
		return prdType;
	}

	public void setPrdType(Integer prdType) {
		this.prdType = prdType;
	}

	public Integer getCateSeq() {
		return cateSeq;
	}

	public void setCateSeq(Integer cateSeq) {
		this.cateSeq = cateSeq;
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

	public String getPrdOrgId() {
		return prdOrgId;
	}

	public void setPrdOrgId(String prdOrgId) {
		this.prdOrgId = prdOrgId;
	}

	public Integer getPrdOrgPrice() {
		return prdOrgPrice;
	}

	public void setPrdOrgPrice(Integer prdOrgPrice) {
		this.prdOrgPrice = prdOrgPrice;
	}

	public Integer getPrdPrice() {
		return prdPrice;
	}

	public void setPrdPrice(Integer prdPrice) {
		this.prdPrice = prdPrice;
	}

	public String getPrdUrl() {
		return prdUrl;
	}

	public void setPrdUrl(String prdUrl) {
		this.prdUrl = prdUrl;
	}

	public String getPrdThumbUrl() {
		return prdThumbUrl;
	}

	public void setPrdThumbUrl(String prdThumbUrl) {
		this.prdThumbUrl = prdThumbUrl;
	}

	public String getCategoryTag() {
		return categoryTag;
	}

	public void setCategoryTag(String categoryTag) {
		this.categoryTag = categoryTag;
	}

	public String getRegYn() {
		return regYn;
	}

	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

}
