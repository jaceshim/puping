package randy.core.pagination;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import randy.core.j2ee.util.ConfigUtils;
import randy.core.j2ee.util.SpringUtils;

/**
 * 페이징
 * 
 * @author jace
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = -2805408045242748580L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final int DEFAULT_PAGE_SIZE = ConfigUtils.getInt("global.page.default-page-size");

	private static final int DEFAULT_PAGE_UNIT = ConfigUtils.getInt("global.page.default-page-unit");

	static final String VELOCITY_CONFIG_BEAN_NAME = "velocityConfig";

	/** 페이지 아이템 */
	private Collection<T> items;
	/** 현재 페이지 */
	private int currentPage;
	/** 총 개수 */
	private int totalCount;
	/** 페이지 번호가 화면에 보여질 개수 ex) [1], [2], [3].. [10] */
	private int pageUnit;
	/** 한 페이지에 보여질  개수 */
	private int pageSize;
	/** 최대 페이지 번호 (전체 페이지 개수) */
	private int maxPage;
	/** 화면에 보여지는 페이지번호의 처음 페이지번호 */
	private int beginUnitPage;
	/** 화면에 보여지는 페이지번호의 마지막 페이지번호 */
	private int endUnitPage;
	/** form 명 */
	public String formName;

	public Page(Collection<T> items, int currentPage, int totalCount) {
		this(items, currentPage, totalCount, DEFAULT_PAGE_SIZE);
	}

	public Page(Collection<T> items, int currentPage, int totalCount, int pageSize) {
		this(items, currentPage, totalCount, pageSize, DEFAULT_PAGE_UNIT);
	}

	public Page(Collection<T> items, int currentPage, int totalCount, int pageSize, int pageUnit) {

		this.items = items;
		this.totalCount = totalCount;
		this.pageUnit = pageUnit;
		this.pageSize = pageSize;
		this.maxPage = pageSize == 0 ? this.totalCount : (totalCount - 1) / pageSize + 1;
		this.currentPage = currentPage > maxPage ? maxPage : currentPage;
		this.beginUnitPage = ((currentPage - 1) / pageUnit) * pageUnit + 1;
		this.endUnitPage = beginUnitPage + (pageUnit - 1);
	}

	public boolean hasNextPage() {
		return currentPage < maxPage;
	}

	public boolean hasPrevPage() {
		return currentPage > 1;
	}

	public int getNextPage() {
		return currentPage + 1;
	}

	public void setNextPage(int val) {
		// not called
	}

	public int getPrevPage() {
		return currentPage - 1;
	}

	public void setPrevPage(int val) {
		// not called
	}

	public boolean hasNextPageUnit() {
		return endUnitPage < maxPage;
	}

	public boolean hasPrevPageUnit() {
		return currentPage >= pageUnit + 1;
	}

	public int getStartOfNextPageUnit() {
		return endUnitPage + 1;
	}

	public int getStartOfPrevPageUnit() {
		return beginUnitPage - 1;
	}

	public int getPageOfNextPageUnit() {
		return (currentPage + pageUnit < maxPage) ? currentPage + pageUnit : maxPage;
	}

	public int getPageOfPrevPageUnit() {
		return (currentPage - pageUnit > 1) ? currentPage - pageUnit : 1;
	}

	public int getEndItemsPage() {
		return (endUnitPage > maxPage) ? this.maxPage : this.endUnitPage;
	}

	public boolean isEmptyPage() {
		return (this.items == null || items.size() == 0) ? true : false;
	}

	/**
	 * form기반으로 페이징이 처리되는 html을 얻는다.
	 * 
	 * @param formName
	 * @return
	 */
	public String getPageHtml(String formName) {

		this.setFormName(formName);

		String resultHtml = "";

		VelocityConfigurer velocityConfig = (VelocityConfigurer)SpringUtils.getBean(VELOCITY_CONFIG_BEAN_NAME);

		VelocityEngine velocityEngine = velocityConfig.getVelocityEngine();
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("paging", this);

		StringWriter stringWriter = new StringWriter();

		try {
			velocityEngine.mergeTemplate(ConfigUtils.getString("global.page.template"), ConfigUtils.getString("default-encoding"), velocityContext, stringWriter);
			resultHtml = stringWriter.toString();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				stringWriter.close();
			} catch (IOException ignore) {
			}
		}

		return resultHtml;
	}

	/*--------------------------------------------
	 * define getter, setter 
	 *---------------------------------------------*/
	public Collection<T> getItems() {
		return items;
	}

	public void setItems(Collection<T> items) {
		this.items = items;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getBeginUnitPage() {
		return beginUnitPage;
	}

	public void setBeginUnitPage(int beginUnitPage) {
		this.beginUnitPage = beginUnitPage;
	}

	public int getEndUnitPage() {
		return endUnitPage;
	}

	public void setEndUnitPage(int endUnitPage) {
		this.endUnitPage = endUnitPage;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getFormName() {
		return this.formName;
	}

}
