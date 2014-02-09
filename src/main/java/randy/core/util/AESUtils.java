package randy.core.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 암복호화 유틸 클래스.
 * 
 * @author jace
 */
public final class AESUtils {
	/**
	 * 암호화
	 */
	public static String encrypt(String secretKey, String value) {
		if (value == null) {
			return null;
		}

		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");

			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

			byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));

			return byteArrayToHex(encrypted);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 복호화
	 */
	public static String decrypt(String secretKey, String value) {
		if (value == null) {
			return value;
		}

		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");

		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

			byte[] original = cipher.doFinal(hexToByteArray(value));

			return new String(original, "UTF-8");
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	/**
	 * hexToByteArray
	 */
	private static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() == 0) {
			return null;
		}

		byte[] ba = new byte[hex.length() / 2];

		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}

		return ba;
	}

	/**
	 * byteArrayToHex
	 */
	private static String byteArrayToHex(byte[] ba) {
		if (ba == null || ba.length == 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder(ba.length * 2);
		String hexNumber;

		for (byte element : ba) {
			hexNumber = "0" + Integer.toHexString(0xff & element);
			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}

		return sb.toString();
	}

}
