package randy.core.j2ee.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * config성격의 properties파일 static access 처리 클래스.
 * 
 * @author jace
 */
@Component
public class ConfigUtils {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private static Map<String, String> propertiesMap = new HashMap<String, String>();;

	/**
	 * 생성자를 통해서 util:properties를 통해 load한 properties를 injection받는다.
	 * <strong>생성자 매개변수명과 util:properties의 id명과 동일해야함.</strong>
	 * 
	 * @param global
	 * @param config
	 */
	@Autowired
	public ConfigUtils(Properties global, Properties config) {
		if (global != null) {
			setProps(global);
		}
		if (config != null) {
			setProps(config);
		}
	}

	private void setProps(Properties props) {
		for (Object key : props.keySet()) {
			String keyStr = key.toString();

			//logger.debug("--> key : " + keyStr);

			propertiesMap.put(keyStr, props.getProperty(keyStr));
		}
	}

	public static int getInt(String key) {
		return Integer.parseInt(getString(key));
	}

	public static boolean getBoolean(String key) {
		return Boolean.parseBoolean(getString(key));
	}

	public static double getDouble(String key) {
		return Double.parseDouble(getString(key));
	}

	public static float getFloat(String key) {
		return Float.parseFloat(getString(key));
	}

	public static long getLong(String key) {
		return Long.parseLong(getString(key));
	}

	public static String getString(String key) {
		return propertiesMap.get(key);
	}
}
