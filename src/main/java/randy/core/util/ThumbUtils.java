package randy.core.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * thumbnail 유틸 클래스.
 * 
 * @author jace
 */
public final class ThumbUtils {

	static Logger logger = LoggerFactory.getLogger(ThumbUtils.class);

	/**
	 * 썸네일 생성 <br>
	 * 주어진 orgName을 기준으로 
	 * 원본이미지명_width_height.확장자 형태로 썸네일 이미지를 생성한다.
	 * 
	 * @param orgName
	 *            원본 이미지 파일 경로
	 * @param width
	 *            줄일 가로 길이
	 * @param height
	 *            줄일 세로 길이
	 * @return 썸네일 파일 이름
	 * @throws IOException
	 */
	public static String createThumb(String orgName, int width, int height) throws IOException {

		File orgFile = new File(orgName);

		String orgFileNm = orgFile.getName();
		int lastIndexPoint = orgFileNm.lastIndexOf('.');

		String tempFileNm = orgFileNm.substring(0, lastIndexPoint);
		String tempFileExt = orgFileNm.substring(lastIndexPoint + 1);

		String destFilePath = orgFile.getParent() + "/" + tempFileNm + "_" + width + "_" + height + "." + tempFileExt;

		File destFile = new File(destFilePath);

		return createThumb(orgFile, destFile, width, height);
	}

	/**
	 * 썸네일 생성
	 * 
	 * @param orgName
	 *            원본 이미지 파일 경로
	 * @param destName
	 *            썸에릴로 저장될 이미지 파일 경로
	 * @param width
	 *            줄일 가로 길이
	 * @param height
	 *            줄일 세로 길이
	 * @return 썸네일 파일 이름
	 * @throws IOException
	 */
	public static String createThumb(String orgName, String destName, int width, int height) throws IOException {
		File orgFile = new File(orgName);
		File destFile = new File(destName);

		return createThumb(orgFile, destFile, width, height);
	}

	/**
	 * 썸네일 생성
	 * 
	 * @param orgFile
	 *            원본 이미지 파일 객체
	 * @param destFile
	 *            썸네일로 저장될 이미지 파일 객체
	 * @param width
	 *            줄일 가로 길이
	 * @param height
	 *            줄일 세로 길이
	 * @return 썸네일 파일이름
	 * @throws IOException
	 */
	public static String createThumb(File orgFile, File destFile, int width, int height) throws IOException {
		Image srcImg = null;
		String suffix = orgFile.getName().substring(orgFile.getName().lastIndexOf('.') + 1).toLowerCase();

		if (suffix.equals("bmp") || suffix.equals("png") || suffix.equals("gif")) {
			logger.debug("--> original file path : {} ", orgFile.getAbsolutePath());
			logger.debug("--> original file can read : {} ", orgFile.canRead());
			srcImg = ImageIO.read(orgFile);
		} else {
			srcImg = new ImageIcon(orgFile.toString()).getImage();
		}

		int srcWidth = srcImg.getWidth(null);
		int srcHeight = srcImg.getHeight(null);
		int destWidth = -1, destHeight = -1;

		if (width < 0) {
			destWidth = srcWidth;
		} else if (width > 0) {
			destWidth = width;
		}

		if (height < 0) {
			destHeight = srcHeight;
		} else if (height > 0) {
			destHeight = height;
		}

		Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight, Image.SCALE_SMOOTH);
		int pixels[] = new int[destWidth * destHeight];
		PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth, destHeight, pixels, 0, destWidth);

		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		BufferedImage destImg = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
		destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);
		ImageIO.write(destImg, "jpg", destFile);

		return destFile.getName();
	}
	/*
	public static void main(String[] args) {

	    String newDir = "/Users/cure/Dev/nimdoo/webapp/upload/new_member/";

	    String dir = "/Users/cure/Dev/nimdoo/webapp/upload/member";
	    
	    try {

	    File file = new File(dir);
	    if (file.isDirectory()) {
	        File[] files = file.listFiles();
	        for (File item : files) {
	            if (item.isFile()) {
	                String fileNm = item.getName();
	                System.out.println("파일명 : " + fileNm);
	                if (fileNm.indexOf(".gif") != -1 || fileNm.indexOf(".jpg") != -1 || fileNm.indexOf(".png") != -1) {

	                    String fileExt = PathUtils.getExtension(fileNm);
	                    // 썸네일 이미지 생성
	                    ImageUtils.createThumb(item.getAbsolutePath(),
	                        dir + "/" + fileNm.replaceAll("." + fileExt, "") + "_100." + fileExt,
	                        100, 100);
	                    // 썸네일 이미지 생성
	                    ImageUtils.createThumb(item.getAbsolutePath(),
	                        dir + "/" + fileNm.replaceAll("." + fileExt, "") + "_55." + fileExt,
	                        55, 55);
	                    // 썸네일 이미지 생성
	                    ImageUtils.createThumb(item.getAbsolutePath(),
	                        dir + "/" + fileNm.replaceAll("." + fileExt, "") + "_32." + fileExt,
	                        32, 32);
	                }
	            }
	        }
	    }
	    }
	    catch (Exception e) {
	        e.printStackTrace(System.out);
	        
	    }

	    System.out.println("-->종료");
	}
	*/

}
