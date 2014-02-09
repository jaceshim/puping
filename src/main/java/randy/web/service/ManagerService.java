package randy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import randy.core.crypto.DESCrypto;
import randy.core.j2ee.service.AbstractService;
import randy.core.pagination.Page;
import randy.web.domain.Manager;

/**
 * 관리자 서비스
 * 
 * @author jace
 */
@Service
public class ManagerService extends AbstractService {
	public static final String NAMESPACE = "manager".concat(".");

	/**
	 * 관리자 목록조회
	 * 
	 * @param manager
	 * @return List<Manager>
	 */
	public List<Manager> getManagerList(Manager manager) {
		return commonDao.selectList(NAMESPACE + "getManagerList", manager);
	}

	/**
	 * 관리자 페이지 목록조회
	 * @param manager
	 * @return
	 */
	public Page<Manager> getManagerPageList(Manager manager) {
		return commonDao.selectPageList(NAMESPACE + "getManagerPageList", manager);
	}

	/**
	 * 관리자 상세조회
	 * 
	 * @param manager
	 * @return Manager
	 */
	public Manager getManager(Manager manager) {
		return commonDao.selectOne(NAMESPACE + "getManager", manager);
	}

	/**
	 * 관리자 등록.
	 * 
	 * @param manager
	 */
	public void insertManager(Manager manager) throws Exception {
		// 비밀번호 암호화
		manager.setPassword(DESCrypto.encrypt(manager.getPassword()));
		manager.setUseYn("Y");

		commonDao.insert(NAMESPACE + "insertManager", manager);
	}

	/**
	 * 관리자 수정.
	 * 
	 * @param manager
	 * @return int
	 */
	public int updateManager(Manager manager) throws Exception {
		// 비밀번호 암호화
		String password = manager.getPassword();
		if (password != null && password.length() > 0) {
			manager.setPassword(DESCrypto.encrypt(password));
		}

		return commonDao.update(NAMESPACE + "updateManager", manager);
	}

	/**
	 * 관리자 마지막 로그인 일시 변경.
	 * 
	 * @param manager
	 * @return int
	 */
	public int updateLoginDt(Manager manager) {
		return commonDao.update(NAMESPACE + "updateLoginDt", manager);
	}
}
