package randy.web.domain;

/**
 * redis 기반에서 domain을 활용할경우 본 인터페이스를 구현해야함.
 * 
 * @author jace
 */
public interface IRedisDomain {

	String getKey();
	
	String getObjectKey();
}
