package randy.core.crypto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;

/**
 * Triple DES 대칭키 생성
 * 
 * @author jace
 */
public final class TripleDESKeyBuilder {

	/**
	 * Triple DES 대칭키 생성
	 * 
	 * @param fileName
	 * @exception Exception
	 */
	public static void make(String keyStr, String fileName) throws Exception {

		KeyGenerator generator = KeyGenerator.getInstance("DESede");
		generator.init(new SecureRandom(keyStr.getBytes()));
		Key key = generator.generateKey();

		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(fileName)));

		out.writeObject(key);
		out.close();
	}

	public static void main(String[] args) throws Exception {

		//파일명 절대경로 : c:/randy-secret.key
		if (args.length < 1) {
			System.out.println("사용예 : java randy.core.crypto.TripleDESKeyBuilder [16자리암화key문자열] [파일명 절대경로]");
			return;
		}

		TripleDESKeyBuilder.make(args[0], args[1]);
	}

}
