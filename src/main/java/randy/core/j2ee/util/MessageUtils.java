package randy.core.j2ee.util;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * i18n 메세지 호출 클래스.
 * 
 * @author jace
 */
@Component
public class MessageUtils {

	static Logger logger = LoggerFactory.getLogger(MessageUtils.class);

	private static ReloadableResourceBundleMessageSource messageSource;

	/**
	 * 생성자를 통해서  load한 messasge properties를 injection받는다.
	 * 
	 * @param messageSource
	 */
	@Autowired
	public MessageUtils(ReloadableResourceBundleMessageSource messageSource) {
		MessageUtils.messageSource = messageSource;
	}

	public static String getMessage(String code) {
		return getMessage(code, "");
	}

	public static String getMessage(String code, String defaultMessage) {
		return messageSource.getMessage(code, null, defaultMessage, getLocale());
	}

	public static String getMessage(String code, Object[] args) {
		return getMessage(code, args, "");
	}

	public static String getMessage(String code, Object[] args, String defaultMessage) {
		return messageSource.getMessage(code, args, defaultMessage, getLocale());
	}

	public static Locale getLocale() {
		return LocaleContextHolder.getLocale();
	}

}
