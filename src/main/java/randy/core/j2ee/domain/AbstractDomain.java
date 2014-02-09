package randy.core.j2ee.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Domain 추상 클래스.
 * 
 * @author jace
 */
public abstract class AbstractDomain {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	/** 검색값 */
	private String searchVal;
	/** 검색조건 */
	private String searchKey;

	public String getSearchVal() {
		return searchVal;
	}

	public void setSearchVal(String searchVal) {
		this.searchVal = searchVal;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
