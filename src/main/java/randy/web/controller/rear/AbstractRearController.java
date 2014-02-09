package randy.web.controller.rear;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import randy.core.j2ee.controller.AbstractController;
import randy.core.j2ee.util.ConfigUtils;
import randy.web.domain.Manager;

/**
 * 관리자단 추상 콘트롤러.
 * 
 * @author jace
 */
@Controller
@RequestMapping("/rear/*")
public abstract class AbstractRearController extends AbstractController {

	protected static final String REDIRECT = "redirect:";

	protected static final String FORWARD = "forward:";

	protected static final String VIEW_PREFIX = "/rear";

	protected static final String MANAGER_SESS_KEY = ConfigUtils.getString("global.manager.session.key");

	/**
	 * 로그인한 관리자 정보를 얻는다.
	 * 
	 * @param request
	 * @return Manager
	 */
	protected Manager getLoginManager(HttpServletRequest request) {
		return (Manager)request.getSession().getAttribute(MANAGER_SESS_KEY);
	}
}
