package randy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import randy.core.j2ee.service.AbstractService;
import randy.web.domain.Mall;

/**
 * 몰 서비스
 * 
 * @author jace
 */
@Service
public class MallService extends AbstractService {
	public static final String NAMESPACE = "mall" + ".";

	/**
	 * 몰 목록을 얻는다.
	 * 
	 * @param mall
	 * @return List<Category>
	 */
	public List<Mall> getMallList(Mall mall) {
		if (mall == null) {
			mall = new Mall();
		}
		return commonDao.selectList(NAMESPACE + "getMallList", mall);
	}

	/**
	 * 몰 등록
	 * 
	 * @param mall
	 * @return Integer
	 */
	public Integer isnertMall(Mall mall) {
		commonDao.insert(NAMESPACE + "insertMall", mall);

		return mall.getMallId();
	}

	/**
	 * 몰 수정
	 * 
	 * @param mall
	 * @return Integer
	 */
	public int updateMall(Mall mall) {
		return commonDao.update(NAMESPACE + "updateMall", mall);
	}
}
