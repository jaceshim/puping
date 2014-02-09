package randy.core.j2ee.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import randy.core.j2ee.dao.CommonDao;

/**
 * Service 추상 클래스.
 * 
 * @author jace
 */
public class AbstractService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "commonDao")
	protected CommonDao commonDao;
}
