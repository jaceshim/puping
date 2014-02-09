package randy.web.domain;

import randy.core.j2ee.domain.AbstractPageDomain;

/**
 * 관리자 도메인
 * 
 * @author jace
 */
public class Manager extends AbstractPageDomain {

	/** 관리자 아이디 */
	private String mgrId;
	/** 관리자 명 */
	private String mgrName;
	/** 비밀번호 */
	private String password;
	/** 활성여부 */
	private String useYn;
	/** 등록일시 */
	private String regDt;
	/** 마지막 로그인 일시 */
	private String loginDt;

	public String getMgrId() {
		return mgrId;
	}

	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}

	public String getMgrName() {
		return mgrName;
	}

	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getLoginDt() {
		return loginDt;
	}

	public void setLoginDt(String loginDt) {
		this.loginDt = loginDt;
	}

}
