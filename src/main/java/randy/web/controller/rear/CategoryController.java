package randy.web.controller.rear;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import randy.core.j2ee.alert.AlertInfo;
import randy.core.j2ee.alert.AlertType;
import randy.web.domain.Category;
import randy.web.domain.CategoryTag;
import randy.web.domain.CategoryTagUnreg;
import randy.web.service.CategoryService;
import randy.web.service.MallService;
import randy.web.support.async.AsyncResult;
import randy.web.support.async.AsyncStatus;

/**
 * 카테고리 콘트롤러.
 * 
 * @author jace
 */
@Controller
public class CategoryController extends AbstractRearController {

	private static final String PATH = "/category/";

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private MallService mallService;

	/**
	 * 카테고리 목록 화면
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(PATH + "list")
	public String getCategoryList(@ModelAttribute Category category, Model model) {

		// nothing...

		return VIEW_PREFIX + PATH + "getCategoryList";
	}

	/**
	 * 카테고리 목록 for tree
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(PATH + "tree")
	public String getCategoryListForTree(HttpServletResponse response, @ModelAttribute Category category, Model model) {

		response.setContentType(JSON_CONTENT_TYPE);

		List<Category> dataList = categoryService.getCategoryList(category);
		model.addAttribute("dataList", dataList);

		return VIEW_PREFIX + PATH + "getCategoryListForTree";
	}

	/**
	 * 카테고리 조회 화면
	 * 
	 * @param cateId
	 * @param model
	 * @return
	 */
	@RequestMapping(PATH + "{cateId}")
	public String getCategoryForAjax(@PathVariable Integer cateId, Model model) {

		Category cateParam = new Category();
		cateParam.setCateSeq(cateId);

		return VIEW_PREFIX + PATH + "getCategory";
	}

	/**
	 * 카테고리 등록 화면
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(PATH + "insertform")
	public String insertCategoryFormForAjax(@ModelAttribute Category category, Model model) {

		Category param = new Category();
		param.setCateSeq(category.getPcateSeq());

		// 상위 카테고리 정보 호출.
		Category data = categoryService.getCategory(param);
		model.addAttribute("data", data);

		return VIEW_PREFIX + PATH + "insertCategoryForm";
	}

	/**
	 * 카테고리 등록
	 * 
	 * @param request
	 * @param category
	 * @param model
	 * @return
	 */
	@RequestMapping(PATH + "insert")
	public String insertCategory(HttpServletRequest request, @ModelAttribute Category category, Model model) {

		categoryService.insertCategory(category);

		// alert처리 정보생성.
		AlertInfo alertInfo = new AlertInfo();
		alertInfo.setAlertType(AlertType.ALERT_AND_GO);
		alertInfo.setMessage("message.insert.success");
		alertInfo.setRedirectUrl(VIEW_PREFIX + PATH + "list");

		return this.alert(request, alertInfo);
	}

	/**
	 * 카테고리 TAG목록 화면
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(PATH + "tag/list")
	public String getCategoryTagList(@ModelAttribute CategoryTag categoryTag, Model model) {

		// 카테고리 목록
		model.addAttribute("categoryList", categoryService.getCategoryList(null));

		model.addAttribute("page", categoryService.getCategoryTagPageList(categoryTag));

		return VIEW_PREFIX + PATH + "getCategoryTagList";
	}

	/**
	 * 카테고리 TAG등록 화면
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(PATH + "tag/insertform")
	public String insertCategoryTagForm(Model model) {

		// 최상위 카테고리 호출.
		Category categoryParam = new Category();
		categoryParam.setPcateSeq(0);
		model.addAttribute("categoryList", categoryService.getCategoryList(categoryParam));

		return VIEW_PREFIX + PATH + "insertCategoryTag";
	}

	/**
	 * 카테고리 TAG등록
	 * 
	 * @param request
	 * @param categoryTag
	 * @param model
	 * @return
	 */
	@RequestMapping(PATH + "tag/insert")
	public String insertCategoryTag(HttpServletRequest request, @ModelAttribute CategoryTag categoryTag, Model model) {

		categoryService.insertCategoryTag(categoryTag);

		// alert처리 정보생성.
		AlertInfo alertInfo = new AlertInfo();
		alertInfo.setAlertType(AlertType.ALERT_AND_GO);
		alertInfo.setMessage("message.insert.success");
		alertInfo.setRedirectUrl(VIEW_PREFIX + PATH + "tag/list");

		return this.alert(request, alertInfo);
	}

	/**
	 * 카테고리 미등록 TAG목록 화면
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(PATH + "tag/unreg/list")
	public String getCategoryTagUnregList(@ModelAttribute CategoryTagUnreg categoryTagUnreg, Model model) {

		// 최상위 카테고리 호출.
		model.addAttribute("categoryList", categoryService.getCategoryTreeList(null));

		model.addAttribute("mallList", mallService.getMallList(null));

		model.addAttribute("page", categoryService.getCategoryTagUnregPageList(categoryTagUnreg));

		return VIEW_PREFIX + PATH + "getCategoryTagUnregList";
	}

	/**
	 * 카테고리 미등록 TAG 등록처리.
	 * 
	 * @param request
	 * @param categoryTag
	 * @param model
	 * @return
	 */
	@RequestMapping(PATH + "tag/unreg/update.ajax")
	@ResponseBody
	public AsyncResult updateCategoryTagUnregForAjax(@ModelAttribute CategoryTagUnreg categoryTagUnreg, Model model) {

		AsyncResult result = new AsyncResult();
		try {
			int resultCount = categoryService.updateCategoryTagUnreg(categoryTagUnreg);
			if (resultCount > 0) {
				result.setStatus(AsyncStatus.SUCCESS.getStatus());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setStatus(AsyncStatus.ERROR.getStatus());
		}

		return result;
	}

}