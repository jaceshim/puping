package randy.web.support.parser;

import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import randy.core.j2ee.dao.CommonDao;
import randy.web.service.CategoryService;
import randy.web.service.MallService;
import randy.web.service.ProductService;

/**
 * 파서를 실행시키는 worker클래스.
 * 
 * @author jace
 */
@Component
public class ParseWorker {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "commonDao")
	protected CommonDao commonDao;

	private static Map<String, AbstractShopParser> parserMap = new ConcurrentHashMap<String, AbstractShopParser>();

	public void run() {
		if (parserMap == null || parserMap.size() == 0) {
			logger.warn(" loaded parser is empty");
			return;
		}

		// 로드되어 생성된 Processor객체 전체를 loop돌면서 실행.
		for (AbstractShopParser item : parserMap.values()) {
			item.execute();
		}
	}

	/**
	 * worker 초기화 진행.
	 * class loader에 의해 로드된 {@link AbstractShopParser} 를 구현한 클래스을 로드한다.
	 */
	@PostConstruct
	public void init() {
		// 파서 클래스 로딩
		Reflections reflections = new Reflections(this.getClass().getPackage().getName());
		Set<Class<? extends AbstractShopParser>> parsers = reflections.getSubTypesOf(AbstractShopParser.class);

		if (parsers != null) {
			logger.debug("-------------------------------------------------");
			int loadCount = 0;
			try {

				for (Class<? extends AbstractShopParser> item : parsers) {
					String parserName = null;
					AbstractShopParser parser = null;
					try {
						parser = item.newInstance();
						parserName = parser.getClass().getSimpleName();

						parser.init(this.commonDao);

						parserMap.put(parserName, parser);

					} catch (Exception e) {
						logger.error(item.getName() + " create error :" + e.getMessage(), e);
						throw e;
					}
					logger.debug(" load parser : " + parserName);

					loadCount++;
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				logger.debug("total parser load count : " + loadCount);
				logger.debug("-------------------------------------------------");
			}

		}

		// 파서실행.
		//this.run();
	}

	/**
	 * worker destory 수행.
	 */
	@PreDestroy
	public void destory() {
		// 등록된 timer 객체를 모두 cancel처리한다.
		for (AbstractShopParser parser : parserMap.values()) {
			Timer parsingTimer = parser.getParsingTimer();
			if (parsingTimer != null) {
				parsingTimer.cancel();
			}
		}
	}

}
