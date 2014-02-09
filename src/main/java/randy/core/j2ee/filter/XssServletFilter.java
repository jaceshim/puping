package randy.core.j2ee.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StringUtils;

import com.nhncorp.lucy.security.xss.XssFilter;

/**
 * 이 클래스를 서블릿필터로 등록하면 xss 처리를 해준다.<br>
 * request래핑 방식이기 때문에 직접 xss 방어코드를 작성할 필요 없다.
 * 
 * @author jace
 */
public class XssServletFilter implements Filter {
	private XssFilter xssFilter;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String xssFilterConfig = filterConfig.getInitParameter("xssFilterConfig");
		if (!StringUtils.hasText(xssFilterConfig)) {
			xssFilterConfig = "lucy-xss-superset.xml";
		}
		xssFilter = XssFilter.getInstance(xssFilterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new XssRequestWrapper((HttpServletRequest)request, xssFilter), response);
	}

	@Override
	public void destroy() {
	}

	/**
	 * XssRequestWrapper
	 *
	 * @author jace
	 */
	private class XssRequestWrapper extends HttpServletRequestWrapper {
		private XssFilter xssFilter;

		public XssRequestWrapper(HttpServletRequest request, XssFilter xssFilter) {
			super(request);
			this.xssFilter = xssFilter;
		}

		@Override
		public String getParameter(String name) {
			String value = super.getParameter(name);
			if (StringUtils.hasText(value)) {
				value = xssFilter.doFilter(value);
			}
			return value;
		}

		@Override
		public String getHeader(String name) {
			String value = super.getHeader(name);
			if (StringUtils.hasText(value)) {
				value = xssFilter.doFilter(value);
			}
			return value;
		}
	}
}
