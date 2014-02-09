package randy.web.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import randy.core.j2ee.controller.AbstractController;
import randy.core.j2ee.util.ConfigUtils;

/**
 * view단에서 스크립트 처리용 공통 콘트롤러.
 * 
 * @author jace
 */
@Controller
public class AlertController extends AbstractController {

	/**
	 * 공통 스크립트 처리.
	 * 
	 * @return String
	 */
	@RequestMapping("/common/alert")
	public String alert() {
		return ConfigUtils.getString("global.alert.template");
	}
}
