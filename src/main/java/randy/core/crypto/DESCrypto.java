package randy.core.crypto;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import randy.core.j2ee.util.MessageUtils;

/**
 * DES 기반의 대칭키 암호화 복호화
 * 
 * @author jace
 */
public final class DESCrypto {
	private static Logger logger = LoggerFactory.getLogger(DESCrypto.class);

	private static String keyFile = MessageUtils.getMessage("global.secret.key-file");
	private static String algorithm = MessageUtils.getMessage("global.secret.algorithm");

	/**
	 * 파일 암호화에 쓰이는 버퍼 크기 지정
	 */
	public static final int kBufferSize = 8192;

	private InputStream defaultInputStream = null;

	private DESCrypto() {
		defaultInputStream = getClass().getResourceAsStream(keyFile);
	}

	/**
	 * 지정된 비밀키를 가지고 오는 메서드
	 * 
	 * @return Key 비밀키 클래스
	 * @exception Exception
	 */
	private static Key getKey(String keyFile) throws Exception {

		String fileURL = null;
		ObjectInputStream in = null;

		if (keyFile == null) {
			in = new ObjectInputStream(new DESCrypto().defaultInputStream);
		} else {
			fileURL = keyFile;
			in = new ObjectInputStream(new FileInputStream(fileURL));
		}

		Key key = (Key)in.readObject();
		in.close();

		return key;
	}

	/**
	 * 문자열 대칭 암호화
	 * 
	 * @param ID
	 *            비밀키 암호화를 희망하는 문자열
	 * @return String 암호화된 ID
	 * @exception Exception
	 */
	public static String encrypt(String ID, String keyFile) throws Exception {

		if (ID == null || ID.length() == 0)
			return "";

		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, getKey(keyFile));
		String amalgam = ID;

		byte[] inputBytes1 = amalgam.getBytes();
		byte[] outputBytes1 = cipher.doFinal(inputBytes1);
		String outputStr1 = Base64.encodeBase64String(outputBytes1);

		return outputStr1;
	}

	public static String encrypt(String ID) throws Exception {

		return encrypt(ID, null);
	}

	/**
	 * 문자열 대칭 복호화
	 * 
	 * @param codedID
	 *            비밀키 복호화를 희망하는 문자열
	 * @return String 복호화된 ID
	 * @exception Exception
	 */
	public static String decrypt(String codedID, String keyFile) throws Exception {

		if (codedID == null || codedID.length() == 0)
			return "";

		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, getKey(keyFile));

		byte[] inputBytes1 = Base64.decodeBase64(codedID);
		byte[] outputBytes2 = cipher.doFinal(inputBytes1);

		String strResult = new String(outputBytes2);

		return strResult;
	}

	public static String decrypt(String codedID) throws Exception {

		return decrypt(codedID, null);
	}

	/**
	 * 파일 대칭 암호화
	 * 
	 * @param infile
	 *            암호화을 희망하는 파일명
	 * @param outfile
	 *            암호화된 파일명
	 * @exception Exception
	 */
	public static void encryptFile(String infile, String outfile, String keyFile) throws Exception {

		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, getKey(keyFile));

		FileInputStream in = new FileInputStream(infile);
		FileOutputStream fileOut = new FileOutputStream(outfile);

		CipherOutputStream out = new CipherOutputStream(fileOut, cipher);
		byte[] buffer = new byte[kBufferSize];
		int length;
		while ((length = in.read(buffer)) != -1)
			out.write(buffer, 0, length);
		in.close();
		out.close();
	}

	public static void encryptFile(String infile, String outfile) throws Exception {

		encryptFile(infile, outfile, null);
	}

	/**
	 * 파일 대칭 복호화
	 * 
	 * @param infile
	 *            복호화을 희망하는 파일명
	 * @param outfile
	 *            복호화된 파일명
	 * @exception Exception
	 */
	public static void decryptFile(String infile, String outfile, String keyFile) throws Exception {

		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, getKey(keyFile));

		FileInputStream in = new FileInputStream(infile);
		FileOutputStream fileOut = new FileOutputStream(outfile);

		CipherOutputStream out = new CipherOutputStream(fileOut, cipher);
		byte[] buffer = new byte[kBufferSize];
		int length;
		while ((length = in.read(buffer)) != -1)
			out.write(buffer, 0, length);
		in.close();
		out.close();
	}

	public static void decryptFile(String infile, String outfile) throws Exception {

		decryptFile(infile, outfile, null);
	}

	public static void main(String[] ars) throws Exception {

		if (ars.length < 2) {
			System.out.println("USE : DESCrypto [-d | -e | -fd | -fe] [text | inputfilename outputfilename]");
			System.exit(0);
		}
		if (ars[0].equals("-d"))
			System.out.println(DESCrypto.decrypt(ars[1]));

		if (ars[0].equals("-e"))
			System.out.println(DESCrypto.encrypt(ars[1]));

		if (ars[0].equals("-fd"))
			DESCrypto.decryptFile(ars[1], ars[2]);

		if (ars[0].equals("-fe"))
			DESCrypto.encryptFile(ars[1], ars[2]);

	}
}
