package randy.web.domain;

/**
 * 카테고리 태그 도메인.
 * 
 * @author jace
 */
public class CategoryTag extends Category {

	/** 순번 */
	private Integer seq;
	/** 태그 */
	private String tag;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
