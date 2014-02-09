package randy.core.util;

import org.apache.commons.lang.StringUtils;

/**
 * 파일경로 및 확장자를 다루는 유틸클래스
 * 
 * @author jace
 */
public final class PathUtils {

	/**
	 * 주어진 파일경로 문자열의 경로구분자를 윈도우즈 path로 변환한다.
	 * 
	 * @param path
	 * @return String
	 */
	public static String toWindowsPath(String path) {

		if (StringUtils.isEmpty(path)) {
			return path;
		}

		return StringUtils.replace(path, "/", "\\");
	}

	/**
	 * 주어진 파일경로 문자열의 경로구분자를 유닉스 path로 변환한다.
	 * 
	 * @param path
	 * @return String
	 */
	public static String toUnixPath(String path) {

		if (StringUtils.isEmpty(path)) {
			return path;
		}

		return StringUtils.replace(StringUtils.replace(path, "\\\\", "/"), "\\", "/");
	}

	/**
	 * 주어진 파일명에서 확장자를 얻는다.
	 * 
	 * @param fileName
	 * @return 파일확장자(Dot is not included)
	 */
	public static String getExtension(String fileName) {

		if (StringUtils.isEmpty(fileName))
			return fileName;

		int dotInd = fileName.lastIndexOf(".");

		return StringUtils.lowerCase((dotInd > 0 && dotInd < fileName.length()) ? fileName.substring(dotInd + 1) : "");
	}

}
