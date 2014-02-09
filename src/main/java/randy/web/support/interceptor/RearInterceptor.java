package randy.web.support.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import randy.core.j2ee.util.ConfigUtils;

/**
 * 관리자단 인터셉터
 * 
 * @author jace
 */
public class RearInterceptor implements HandlerInterceptor {

	private static final String LOGIN_URL = "/rear/manager/loginform";
	/** 로그인 체크 제외대상 URI패턴 */
	private static String EXCLUDE_PATTERN = null;

	static {
		// 제외대상 uri 패턴 등록.
		StringBuilder sb = new StringBuilder();
		sb.append("(/rear/manager/loginform*)");
		sb.append("|").append("(/rear/manager/login*)");
		//sb.append("|").append("(/rear/manager/insertform*)");
		//sb.append("|").append("(/rear/manager/insert*)");

		EXCLUDE_PATTERN = sb.toString();
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
		String uri = request.getRequestURI().replaceAll("/+(.*)", "/$1");
		if (!uri.matches(EXCLUDE_PATTERN)) {
			Object sessObj = request.getSession().getAttribute(ConfigUtils.getString("global.manager.session.key"));

			if (sessObj == null) {
				response.sendRedirect(LOGIN_URL);
				return false;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handle, ModelAndView modelAndView) throws Exception {
		// nothing

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handle, Exception exception) throws Exception {
		// nothing
	}

}
