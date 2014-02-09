package randy.web.support.parser.domain;

import randy.core.j2ee.domain.AbstractDomain;

/**
 * HttpClient 요청 응답 결과 도메인.
 * 
 * 
 * @author jace
 */
public class HttpResult extends AbstractDomain {
	
	public static final String LAST_MODIFIED_NAME = "last-modified";

	private String content;

	private int statusCode;

	private String lastModified;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

}
