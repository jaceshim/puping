package randy.web.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import randy.core.j2ee.service.AbstractService;
import randy.core.pagination.Page;
import randy.web.domain.Category;
import randy.web.domain.CategoryTag;
import randy.web.domain.CategoryTagUnreg;
import randy.web.domain.Product;

/**
 * 카테고리 서비스
 * 
 * @author jace
 */
@Service
public class CategoryService extends AbstractService {

	public static final String NAMESPACE = "category" + ".";

	/**
	 * 각 부모카테고리 하위 카테고리를 tree형태의 목록으로 조회한다. 
	 * 
	 * @param category
	 * @return List<Category>
	 */
	public List<Category> getCategoryTreeList(Category category) {
		if (category == null) {
			category = new Category();
		}
		return commonDao.selectList(NAMESPACE + "getCategoryTreeList", category);
	}

	/**
	 * 주어진 카테고리를 부모로 하는 하위 카테고리 목록을 얻는다.
	 * 
	 * @param category
	 * @return List<Category>
	 */
	public List<Category> getCategoryList(Category category) {
		if (category == null) {
			category = new Category();
		}
		return commonDao.selectList(NAMESPACE + "getCategoryList", category);
	}
	
	/**
	 * 카테고리 상세정보 조회
	 * 
	 * @param category
	 * @return
	 */
	public Category getCategory(Category category) {
		return commonDao.selectOne(NAMESPACE + "getCategory", category);
	}

	/**
	 * 신규 카테고리 등록
	 * 
	 * @param category
	 * @return Integer
	 */
	public Integer insertCategory(Category category) {

		if (StringUtils.isEmpty(category.getUseYn())) {
			category.setUseYn("Y");
		}
		commonDao.insert(NAMESPACE + "insertCategory", category);

		return category.getCateSeq();
	}

	/**
	 * 카테고리 목록 호출.
	 * 
	 * @param categoryTag
	 * @return List<CategoryTag>
	 */
	public List<CategoryTag> getCategoryTagList(CategoryTag categoryTag) {
		if (categoryTag == null) {
			categoryTag = new CategoryTag();
		}
		return commonDao.selectList(NAMESPACE + "getCategoryTagList", categoryTag);
	}

	/**
	 * 카테고리 페이지 목록 호출.
	 * 
	 * @param categoryTagUnreg
	 * @return Page<CategoryTag>
	 */
	public Page<CategoryTag> getCategoryTagPageList(CategoryTag categoryTag) {
		if (categoryTag.getPageSize() == null) {
			// 페이지당 100개씩 노출.
			categoryTag.setPageSize(50);
		}

		return commonDao.selectPageList(NAMESPACE + "getCategoryTagPageList", categoryTag);
	}

	/**
	 * 카테고리 태그 등록
	 * 
	 * @param categoryTag
	 * @return Integer
	 */
	public Integer insertCategoryTag(CategoryTag categoryTag) {
		commonDao.insert(NAMESPACE + "insertCategoryTag", categoryTag);
		return categoryTag.getSeq();
	}

	/**
	 * 미등록 태그 등록처리.
	 * 
	 * @param categoryTagUnreg
	 * @return int
	 */
	@Transactional
	public int updateCategoryTagUnreg(CategoryTagUnreg categoryTagUnreg) {

		CategoryTagUnreg tagUnregInfo = this.getCategoryTagUnreg(categoryTagUnreg);

		CategoryTag categoryTag = new CategoryTag();
		categoryTag.setCateSeq(categoryTagUnreg.getCateSeq());
		categoryTag.setTag(tagUnregInfo.getTag());

		// 카테고리 태그 등록.
		int updateCount = commonDao.update(NAMESPACE + "insertCategoryTag", categoryTag);
		if (updateCount > 0) {
			// 상품의 등록여부 Y로 업데이트 처리.
			// 미등록 태그의 상품정보 업데이트.
			Product prdParam = new Product();
			prdParam.setPrdSeq(tagUnregInfo.getPrdSeq());
			prdParam.setRegYn("Y");
			prdParam.setCateSeq(categoryTagUnreg.getCateSeq());

			commonDao.update(ProductService.NAMESPACE + "updateProduct", prdParam);

			// 미등록 태그 정보 삭제처리.
			commonDao.delete(CategoryService.NAMESPACE + "deleteCategoryTagUnreg", tagUnregInfo);
		}

		return updateCount;
	}

	/**
	 * 카테고리 미 등록 태그 상세정보.
	 * 
	 * @param categoryTag
	 * @return List<CategoryTagUnreg>
	 */
	public CategoryTagUnreg getCategoryTagUnreg(CategoryTagUnreg categoryTagUnreg) {
		return commonDao.selectOne(NAMESPACE + "getCategoryTagUnreg", categoryTagUnreg);
	}

	/**
	 * 카테고리 미 등록 태그 목록을 얻는다.
	 * 
	 * @param categoryTag
	 * @return List<CategoryTagUnreg>
	 */
	public List<CategoryTagUnreg> getCategoryTagUnregList(CategoryTagUnreg categoryTagUnreg) {
		if (categoryTagUnreg == null) {
			categoryTagUnreg = new CategoryTagUnreg();
		}
		return commonDao.selectList(NAMESPACE + "getCategoryTagUnregList", categoryTagUnreg);
	}

	/**
	 * 카테고리 미 등록 태그 목록을 얻는다.
	 * 
	 * @param categoryTag
	 * @return Page<CategoryTagUnreg>
	 */
	public Page<CategoryTagUnreg> getCategoryTagUnregPageList(CategoryTagUnreg categoryTagUnreg) {
		if (categoryTagUnreg.getPageSize() == null) {
			// 페이지당 100개씩 노출.
			categoryTagUnreg.setPageSize(50);
		}

		return commonDao.selectPageList(NAMESPACE + "getCategoryTagUnregPageList", categoryTagUnreg);
	}
}
