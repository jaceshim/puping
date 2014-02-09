package randy.web.support.parser;

import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 파싱처리 타이머 클래스.
 * 
 * @author jaceshim
 */
public class ParseTimerTask extends TimerTask {

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	private AbstractShopParser parser;
	
	private AtomicBoolean running = new AtomicBoolean(false);
	
	private String parserName;
	
	public ParseTimerTask(AbstractShopParser parser) {
		this.parser = parser;
		this.parserName = this.parser.getClass().getSimpleName();
	}
	
	@Override
	public void run() {
		log.info(" run parsing -> " + parserName);
		doFlush();
	}
	
	private void doFlush() {
		synchronized (running) {
			if (running.get()) {
				log.warn("already running " + parserName );
				return;
			}

			try {
				running.set(true);
				try {
					this.parser.parse();
				} catch (Exception e) {
					log.error(parserName + " parsing error :" + e.getMessage(), e);
				}
			} finally {
				running.set(false);
			}
		}
	}

}
