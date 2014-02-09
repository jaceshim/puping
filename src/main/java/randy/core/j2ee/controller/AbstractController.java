package randy.core.j2ee.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

import randy.core.j2ee.alert.AlertInfo;
import randy.core.j2ee.util.ConfigUtils;
import randy.core.j2ee.util.MessageUtils;

/**
 * Controller 추상 클래스.
 * 
 * @author jace
 */
public abstract class AbstractController {

	protected static final String REDIRECT = "redirect:";
	protected static final String FORWARD = "forward:";

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * view단 alert처리.
	 * 
	 * @param request
	 * @param alertInfo
	 * @return String
	 */
	protected String alert(HttpServletRequest request, AlertInfo alertInfo) {

		FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);

		flashMap.put("redirectUrl", alertInfo.getRedirectUrl());
		flashMap.put("alertType", alertInfo.getAlertType().getType());

		String messageKey = alertInfo.getMessage();
		String[] messageArgs = alertInfo.getMessageArgs();
		String message = MessageUtils.getMessage(messageKey, messageArgs);
		
		// 해당 message key에 대한 값이 없는 경우 넘어온 message를 그냥 출력.
		if (message == null || message.length() == 0) {
			message = messageKey;
		}

		flashMap.put("message", message);
		flashMap.put("params", alertInfo.getParams());
		/*
		Map<String, Object> params = alertInfo.getParams();
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				flashMap.put(key, params.get(key));
			}
		}*/
		return REDIRECT + ConfigUtils.getString("global.alert.url");
	}
	
	/**
	 * Message Key enum
	 * 
	 * @author jace
	 */
	protected enum Message {
		INSERT_SUCCESS("common.insert.success"), 
		INSERT_FAIL("common.insert.fail"), 
		UPDATE_SUCCESS("common.update.success"), 
		UPDATE_FAIL("common.update.fail"), 
		DELETE_SUCCESS("common.delete.success"), 
		DELETE_FAIL("common.delete.fail");

		private String key;

		private Message(String key) {
			this.key = key;
		}

		public String getKey() {
			return this.key;
		}
	}	
}
