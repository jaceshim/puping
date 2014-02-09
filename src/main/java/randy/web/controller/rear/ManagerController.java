package randy.web.controller.rear;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import randy.core.crypto.DESCrypto;
import randy.core.j2ee.alert.AlertInfo;
import randy.core.j2ee.alert.AlertType;
import randy.core.j2ee.util.ConfigUtils;
import randy.web.domain.Manager;
import randy.web.service.ManagerService;
import randy.web.support.async.AsyncResult;
import randy.web.support.async.AsyncStatus;

/**
 * 관리자 관련 콘트롤러.
 * 
 * @author jace
 */
@Controller
public class ManagerController extends AbstractRearController {

	private static final String PATH = "/manager/";

	@Autowired
	ManagerService managerService;

	/**
	 * 로그인 화면
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(PATH + "loginform")
	public String loginForm(Model model) {

		return VIEW_PREFIX + PATH + "login";
	}

	/**
	 * 로그인 처리.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = PATH + "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, @ModelAttribute Manager manager, Model model) throws Exception {

		manager.setPassword(DESCrypto.encrypt(manager.getPassword()));
		// 파라미터로 useYn을 전송해서 로그인 처리를 할 수 있도록함.
		if (manager.getUseYn() == null) {
			// 활성화된 계정만 유효함.
			manager.setUseYn("Y");
		}

		Manager managerInfo = managerService.getManager(manager);
		if (managerInfo == null) {
			AlertInfo alertInfo = new AlertInfo();
			alertInfo.setAlertType(AlertType.ALERT_AND_GO);
			alertInfo.setRedirectUrl(VIEW_PREFIX + PATH + "loginform");
			alertInfo.setMessage("common.login.fail");

			return alert(request, alertInfo);
		} else {
			// 마지막 로그인 일시 업데이트
			managerService.updateLoginDt(managerInfo);

			request.getSession().setAttribute(MANAGER_SESS_KEY, managerInfo);

			return REDIRECT + "/rear/main";
		}
	}

	/**
	 * 로그아웃 처리.
	 * 
	 * @param request
	 * @param manager
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(PATH + "logout")
	public String logout(HttpServletRequest request, Model model) throws Exception {

		Object sessObj = request.getSession().getAttribute(MANAGER_SESS_KEY);
		if (sessObj == null) {
			return REDIRECT + VIEW_PREFIX + PATH + "loginform";
		} else {
			request.getSession().removeAttribute(MANAGER_SESS_KEY);

			AlertInfo alertInfo = new AlertInfo();
			alertInfo.setAlertType(AlertType.ALERT_AND_GO);
			alertInfo.setRedirectUrl(VIEW_PREFIX + PATH + "loginform");
			alertInfo.setMessage("common.logout.success");

			return alert(request, alertInfo);
		}
	}

	/**
	 * 관리자 페이지 목록 조회
	 * 
	 * @param manager
	 * @param model
	 * @return
	 */
	@RequestMapping(PATH + "list")
	public String getManagerList(@ModelAttribute Manager manager, Model model) {

		model.addAttribute("page", managerService.getManagerPageList(manager));

		return VIEW_PREFIX + PATH + "getManagerList";
	}

	/**
	 * 관리자 아이디 중복체크
	 * 
	 * @param manager
	 * @param model
	 * @return
	 */
	@RequestMapping(value = PATH + "duplecheck", method = RequestMethod.POST)
	@ResponseBody
	public AsyncResult managerIdDupleCheckForAjax(@ModelAttribute Manager manager, Model model) {

		AsyncResult result = new AsyncResult();
		try {
			boolean notUse = true;
			// 등록불가 manager id 인지 판단.
			String denyId = ConfigUtils.getString("global.managerid.deny");

			logger.debug("--> 관리자 거부 아이디 목록 : " + denyId);

			String[] denyIdSplit = StringUtils.split(denyId, ",");
			if (ArrayUtils.contains(denyIdSplit, manager.getMgrId())) {
				notUse = false;
			} else {
				Manager data = managerService.getManager(manager);
				if (data != null) {
					notUse = false;
				}
			}
			result.setData(notUse);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setMessage(e.getMessage());
			result.setStatus(AsyncStatus.ERROR.getStatus());
		}

		return result;
	}

	/**
	 * 관리자 등록폼.
	 * 
	 * @param manager
	 * @param model
	 * @return
	 */
	@RequestMapping(value = PATH + "insertform")
	public String insertManagerForm(@ModelAttribute Manager manager, Model model) {

		return VIEW_PREFIX + PATH + "insertManager";
	}

	/**
	 * 관리자 신규등록
	 * 
	 * @param request
	 * @param manager
	 * @param model
	 * @return
	 */
	@RequestMapping(value = PATH + "insert", method = RequestMethod.POST)
	public String insertManager(HttpServletRequest request, @ModelAttribute Manager manager, Model model) throws Exception {

		managerService.insertManager(manager);

		AlertInfo alertInfo = new AlertInfo();
		alertInfo.setAlertType(AlertType.ALERT_AND_GO);
		alertInfo.setRedirectUrl(VIEW_PREFIX + PATH + "list");
		alertInfo.setMessage(Message.INSERT_SUCCESS.getKey());

		return alert(request, alertInfo);
	}

	/**
	 * 관리자 정보 수정폼.
	 * 
	 * @param manager
	 * @param model
	 * @return
	 */
	@RequestMapping(PATH + "updateform")
	public String updateManagerForm(HttpServletRequest request, @ModelAttribute Manager manager, Model model) {

		model.addAttribute("data", managerService.getManager(manager));

		return VIEW_PREFIX + PATH + "updateManager";
	}

	/**
	 * 관리자 정보 수정.
	 * 
	 * @param manager
	 * @param model
	 * @return
	 */
	@RequestMapping(value = PATH + "update", method = RequestMethod.POST)
	public String updateManager(HttpServletRequest request, @ModelAttribute Manager manager, Model model) throws Exception {
		managerService.updateManager(manager);

		AlertInfo alertInfo = new AlertInfo();
		alertInfo.setAlertType(AlertType.ALERT_AND_GO);
		alertInfo.setRedirectUrl(VIEW_PREFIX + PATH + "list");
		alertInfo.setMessage(Message.UPDATE_SUCCESS.getKey());

		return alert(request, alertInfo);
	}

}
