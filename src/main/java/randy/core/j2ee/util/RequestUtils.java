package randy.core.j2ee.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Request관련 유틸 클래스.
 * 
 * @author jace
 */
public final class RequestUtils {
	public static HttpServletRequest getCurrentRequest() {
		ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();

		return request;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static Map getParams() {
		Map params = new HashMap();
		HttpServletRequest request = getCurrentRequest();

		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String name = parameterNames.nextElement();
			String[] values = request.getParameterValues(name);
			if (values != null && values.length == 1) {
				params.put(name, request.getParameter(name));
			} else {
				params.put(name, Arrays.asList(values));
			}
		}

		return params;
	}

	public static String getHeader(String name) {
		return getCurrentRequest().getHeader(name);
	}

	public static String getMethod() {
		return getCurrentRequest().getMethod();
	}

	public static String getRequestFullURI() {
		String uri = UrlUtils.buildFullRequestUrl(getCurrentRequest());
		try {
			uri = URLEncoder.encode(uri, ConfigUtils.getString("global.default-encoding"));
		} catch (UnsupportedEncodingException ignore) {
		}
		return uri;
	}
}
