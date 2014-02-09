package randy.web.support.parser.domain;

/**
 * 상품유형 
 * 
 * @author jace
 */
public enum ProductType {

	/** 인기상품 */
	HOT(1),
	/** 오늘만 특가 상품 */
	TODAY(2);

	private Integer type;

	private ProductType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return this.type;
	}

}
