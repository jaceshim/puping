package randy.web.support.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis를 사용하여 data처리시 이를 지원하기 위한 dao.
 * 
 * @author jace
 */
//@Component
public class RedisDao {
	
	@Autowired
	private JedisPool pool;
	
	private Jedis jedis() {
		return pool.getResource();
	}
}
