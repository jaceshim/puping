package randy.web.controller.rear;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import randy.web.domain.Product;
import randy.web.service.CategoryService;
import randy.web.service.MallService;
import randy.web.service.ProductService;

/**
 * 상품 콘트롤러.
 * 
 * @author jace
 */
@Controller
public class ProductController extends AbstractRearController {

	private static final String PATH = "/product/";

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private MallService mallService;
	
	@Autowired
	private ProductService productService;

	/**
	 * 상품 목록 조회
	 * 
	 * @param product
	 * @param model
	 * @return
	 */
	@RequestMapping(PATH + "list")
	public String getProductList(@ModelAttribute Product product, Model model) {

		// 최상위 카테고리 호출.
		model.addAttribute("categoryList", categoryService.getCategoryTreeList(null));

		model.addAttribute("mallList", mallService.getMallList(null));

		model.addAttribute("page", productService.getProductPageList(product));

		return VIEW_PREFIX + PATH + "getProductList";
	}

}
