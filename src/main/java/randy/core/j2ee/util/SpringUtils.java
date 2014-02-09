package randy.core.j2ee.util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;

/**
 * Spring관련 유틸클래스.
 * 
 * @author jace
 */
@Component
public final class SpringUtils implements ServletContextAware {

	/** web.xml 에 기술되어 있는 @see org.springframework.web.servlet.DispatcherServlet 의 servlet-name */
	private static final String SPRING_SERVLET_NAME = "appServlet";

	private static ServletContext context;

	public void setServletContext(ServletContext _context) {
		context = _context;
	}

	public static ServletContext getContext() {
		return context;
	}

	public static ApplicationContext getApplicationContext() {
		return WebApplicationContextUtils.getWebApplicationContext(getContext(), FrameworkServlet.SERVLET_CONTEXT_PREFIX + SPRING_SERVLET_NAME);
	}

	public static Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}

	public static <T> T getBean(Class<T> type) {
		return getApplicationContext().getBean(type);
	}
	
	public static <T> T getBean(String beanName, Class<T> type) {
		return getApplicationContext().getBean(beanName, type);
	}	

	public static boolean containsBean(String name) {
		return getApplicationContext().containsBean(name);
	}

}
