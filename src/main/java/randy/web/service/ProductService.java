package randy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import randy.core.j2ee.service.AbstractService;
import randy.core.pagination.Page;
import randy.web.domain.Product;

/**
 * 상품 서비스.
 * 
 * @author jace
 */
@Service
public class ProductService extends AbstractService {
	public static final String NAMESPACE = "product" + ".";

	/**
	 * 상품 목록을 얻는다.
	 * 
	 * @param product
	 * @return List<Product>
	 */
	public List<Product> getProductList(Product product) {
		return commonDao.selectList(NAMESPACE + "getProductList", product);
	}

	/**
	 * 상품 페이지 목록 호출.
	 * 
	 * @param product
	 * @return Page<Product>
	 */
	public Page<Product> getProductPageList(Product product) {
		if (product.getPageSize() == null) {
			// 페이지당 100개씩 노출.
			product.setPageSize(50);
		}

		return commonDao.selectPageList(NAMESPACE + "getProductPageList", product);
	}

	/**
	 * 상품 정보 등록.
	 * 
	 * @param product
	 * @return
	 */
	public Integer insertProduct(Product product) {
		return (Integer)commonDao.insert(NAMESPACE + "insertProduct", product);
	}

	/**
	 * 상품 정보 변경.
	 * 
	 * @param product
	 * @return int
	 */
	public int updateProduct(Product product) {
		return commonDao.update(NAMESPACE + "updateProduct", product);
	}

	/**
	 * 상품 정보 삭제.
	 * 
	 * @param product
	 * @return
	 */
	public int deleteProduct(Product product) {
		return commonDao.delete(NAMESPACE + "deleteProduct", product);
	}

}
