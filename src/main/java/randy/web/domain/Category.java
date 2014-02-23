package randy.web.domain;

import java.util.List;

import randy.core.j2ee.domain.AbstractPageDomain;

/**
 * 카테고리 도메인.
 * 
 * @author jace
 */
public class Category extends AbstractPageDomain {

	/** 카테고리 태그 목록 */
	private List<CategoryTag> cateTagList;

	private List<Category> childrenList;

	/** 카테고리 순번 */
	private Integer cateSeq;
	/** 부모 카테고리 순번 */
	private Integer pcateSeq;
	/** 카테고리 명 */
	private String cateName;
	/** 사용여부 */
	private String useYn;
	
	public List<CategoryTag> getCateTagList() {
		return cateTagList;
	}

	public void setCateTagList(List<CategoryTag> cateTagList) {
		this.cateTagList = cateTagList;
	}

	public List<Category> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<Category> childrenList) {
		this.childrenList = childrenList;
	}

	public Integer getCateSeq() {
		return cateSeq;
	}

	public void setCateSeq(Integer cateSeq) {
		this.cateSeq = cateSeq;
	}

	public Integer getPcateSeq() {
		return pcateSeq;
	}

	public void setPcateSeq(Integer pcateSeq) {
		this.pcateSeq = pcateSeq;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

}
