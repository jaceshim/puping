package randy.core.j2ee.domain;

import randy.core.j2ee.util.ConfigUtils;

/**
 * 페이징처리 추상 클래스.
 * 
 * <p>페이징 처리를 원하는 domain은 본 클래스를 상속받아 구현한다. </p>
 * 
 * @author jace
 */
public abstract class AbstractPageDomain extends AbstractDomain {

	/** 선택된 페이지 번호 */
	private Integer pageNum;
	/** 페이지 번호가 화면에 보여질 개수 ex) [1], [2], [3].. [10] */
	private Integer pageUnit;
	/** 한 페이지에 보여질  개수 */
	private Integer pageSize;
	/** 선택된 페이지번호를 mysql limit에 대입시 사용되는 변수 */
	private Integer startRow;

	public Integer getPageNum() {
		return pageNum == null ? 1 : pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageUnit() {
		return pageUnit == null ? ConfigUtils.getInt("global.page.default-page-unit") : pageUnit;
	}

	public void setPageUnit(Integer pageUnit) {
		this.pageUnit = pageUnit;
	}

	public Integer getPageSize() {
		return pageSize == null ? ConfigUtils.getInt("global.page.default-page-size") : pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStartRow() {
		return (getPageNum() - 1) * getPageSize();
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

}
