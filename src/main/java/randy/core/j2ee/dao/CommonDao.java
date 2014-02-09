package randy.core.j2ee.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.util.StringUtils;

import randy.core.j2ee.domain.AbstractPageDomain;
import randy.core.j2ee.util.MessageUtils;
import randy.core.pagination.Page;

/**
 * 서비스레이어에서 공용으로 사용하는 Dao. <br>
 * 이 클래스는 {@link DataSource} 또는 {@link SqlMapClient}와 기본 namespace를 필요로 한다.<br>
 * 이 클래스를 사용하는 프로젝트별로 기본 namespace가 다르며, 다중 DataSource를 사용할 수 있기 때문에 스프링 설정파일에 선언해서 써야한다.<br>
 * 2개 이상의 CommonDao를 정의할 경우, primary=true 옵션을 주어야 부작용을 방지할 수 있다.
 *
 * e.g)
 * <code>
 * 	<bean class="randy.core.dao.CommonDao">
 *   	<property name="defaultNamespace" value="randy.web"/>
 *   	<property name="dataSource" ref="dataSource"/>
 *  </bean>
 * </code>
 *
 * @author cheonbo, shim
 */
public class CommonDao extends AbstractDao {
	private String defaultNamespace;

	public void setDefaultNamespace(String defaultNamespace) {
		if (StringUtils.hasText(defaultNamespace)) {
			this.defaultNamespace = defaultNamespace + ".";
		} else {
			this.defaultNamespace = MessageUtils.getMessage("global.default.namespace") + ".";
		}
	}

	public Object insert(String statementName, Object obj) {
		return getSqlSession().insert(getStatementName(statementName), obj);
	}

	public int update(String statementName, Object obj) {
		return getSqlSession().update(getStatementName(statementName), obj);
	}

	public int delete(String statementName, Object obj) {
		return getSqlSession().delete(getStatementName(statementName), obj);
	}

	@SuppressWarnings("unchecked")
	public <T> T selectOne(String statementName, Object obj) {
		return (T)getSqlSession().selectOne(getStatementName(statementName), obj);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> selectList(String statementName, Object obj) {
		return (List<T>)getSqlSession().selectList(getStatementName(statementName), obj);
	}

	@SuppressWarnings("unchecked")
	public <T> Page<T> selectPageList(String statementName, AbstractPageDomain obj) {

		List<T> dataList = (List<T>)getSqlSession().selectList(getStatementName(statementName), obj);
		// 주어진  statement에 Count가 추가되는 규약. ex) getDataList 이면 getDataListCount가 된다.
		Integer totalCount = getSqlSession().selectOne(getStatementName(statementName + "Count"), obj);

		return new Page<T>(dataList, obj.getPageNum(), totalCount, obj.getPageSize(), obj.getPageUnit());
	}

	private String getStatementName(String statementName) {
		
		logger.debug("--> 호출 대상 statement 명 : " + defaultNamespace.concat(statementName));
		return defaultNamespace.concat(statementName);
	}
}
