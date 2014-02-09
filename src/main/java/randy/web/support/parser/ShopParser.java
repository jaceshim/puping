package randy.web.support.parser;

import java.util.List;

import randy.web.domain.Event;
import randy.web.domain.Product;
import randy.web.domain.Promotion;

/**
 * shop parsing 인터페이스
 * 
 * @author jace
 */
public interface ShopParser {
	
	/**
	 * 몰 아이디
	 * 
	 * @return String
	 */
	Integer getMallId();
	
	/**
	 * 스케쥴 실행 주기. (단위: 분)
	 * 
	 * @return
	 */
	long getScheduleInterval();
	
	/**
	 * 인코딩
	 * 
	 * @return String
	 */
	String getEnconding();
	
	/**
	 * 이벤트 parse
	 * 
	 * @return String
	 */
	List<Event> parseEvent();
	
	/**
	 * 기획전 parse
	 * 
	 * @return String
	 */
	List<Promotion> parsePromotion();
	
	/**
	 * 상품 parse
	 * 
	 * @return String
	 */
	List<Product> parseProduct();
	
	/**
	 * 오늝 특가 parse
	 * 
	 * @return String
	 */
	List<Product> parseTodaySpecial();
	
}
