package randy.web.controller.rear;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Main 콘트롤러
 * 
 * @author jace
 */
@Controller
public class MainController extends AbstractRearController {

	/**
	 * 로그인 화면
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/main")
	public String getMain(Model model) {

		return VIEW_PREFIX + "/main/main";
	}
	
}
