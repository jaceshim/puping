package randy.core.crypto;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SHA1 암호화 관련 유틸 클래스
 * 
 * @author jace
 */
public final class SHA1Util {
	static final Logger logger = LoggerFactory.getLogger(SHA1Util.class);

	private static final String ALGORITHM = "SHA1";

	/**
	 * 주어진 문자열은 SHA1 알고리즘으로 암호화 한다.
	 * 
	 * @param input
	 * @return
	 */
	public static String encrypt(String input) {

		if (input == null || input.length() == 0) {
			return input;
		}

		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			byte[] inputByte = input.getBytes();
			md.update(inputByte);

			byte[] digest = md.digest();
			for (int i = 0, j = digest.length; i < j; i++) {
				result = result + Integer.toHexString(digest[i] & 0xFF).toUpperCase();
			}
		} catch (Exception e) {
			logger.error(ALGORITHM + " encrypting error." + e.getMessage(), e);
		}

		return result;
	}

	public static void main(String[] args) {

		SHA1Util.encrypt(args[0]);
	}
}
