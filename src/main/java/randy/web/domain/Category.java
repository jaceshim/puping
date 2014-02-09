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

	/** 카테고리 순번 */
	private Integer cateSeq;
	/** 부모 카테고리 순번 */
	private Integer pcateSeq;
	/** 카테고리 명 */
	private String cateName;
	/** 사용여부 */
	private String useYn;
	/** tree구조 표현시 레벨 */
	private int level;

	public List<CategoryTag> getCateTagList() {
		return cateTagList;
	}

	public void setCateTagList(List<CategoryTag> cateTagList) {
		this.cateTagList = cateTagList;
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

	public void setPcateId(Integer pcateSeq) {
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
