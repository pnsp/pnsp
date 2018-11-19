package com.iexiao.pnsp.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Random;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.iexiao.pnsp.utils.shiro.ShiroUtil;

/**
 * 影音/图片工具类
 * @author lizhiyong
 * @date 2018年10月19日
 */
public class MultipartUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShiroUtil.class);
	
	/**
	 * 本地上传文件
	 * @author lizhiyong
	 * @date 2018年10月19日
	 * @param file 上传的文件
	 * @param path 文件存储目录
	 * @param filename 文件名
	 * @return
	 */
	public static boolean uploadFile(MultipartFile file, String path, String filename) {  
        // 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  
                File filepath = new File(path);
                //判断目录，不存在则创建
                if(!filepath.exists())filepath.mkdirs();
                //拼接目录和文件名，根据系统对应使用相应的分隔符
                String savePath = concatFileSavePath(path, filename);  
                LOGGER.info("image save path：" + savePath);
                //转存文件  
                file.transferTo(new File(savePath));  
                return true;  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return false;  
    }  
	
	/**
	 * 获取文件名
	 * @author lizhiyong
	 * @date 2018年10月19日
	 * @param file
	 * @return
	 */
	public static String getFileName(MultipartFile file) {
		return file.getOriginalFilename();
	}
	
	/**
	 * 获取文件名后缀
	 * @author lizhiyong
	 * @date 2018年10月19日
	 * @param fileName
	 * @return
	 */
	public static String getFileNameSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	
	/**
	 * 拼接目录和文件名，根据系统对应使用相应的分隔符
	 * @author lizhiyong
	 * @date 2018年10月19日
	 * @param path
	 * @param filename
	 * @return
	 */
	public static String concatFileSavePath(String path, String filename) {
		return FilenameUtils.concat(path, filename);
	}
	
	/**
	 * 校验文件格式
	 * 如：image/jpg,image/jpeg,image/png,image/gif
	 * @author lizhiyong
	 * @date 2018年10月19日
	 * @param file
	 * @param contentTypes 支持的格式集合
	 * @return
	 */
	public static boolean checkType(MultipartFile file,List<String> contentTypes) {
		return contentTypes.contains(file.getContentType());
	}
	
	/**
	 * 获取文件类型
	 * @author lizhiyong
	 * @date 2018年10月19日
	 * @param file
	 * @return
	 */
	public static String getFileType(MultipartFile file) {
		return file.getContentType();
	}
	
	/**
	 * 从指定的url下载文件，保存到目标文件destination
	 * @author lizhiyong
	 * @date 2018年10月19日
	 * @param source 源URL
	 * @param destination 目标文件
	 * @param connectionTimeout 连接超时时长
	 * @param readTimeout 读取超时时长
	 * @return
	 */
	public static boolean downloadURLToFile(URL source, File destination, int connectionTimeout, int readTimeout) {
		try {
			FileUtils.copyURLToFile(source, destination, connectionTimeout, readTimeout);
			LOGGER.info("[downloadURLToFile success！]");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 生成文件名
	 * @author lizhiyong
	 * @date 2018年10月19日
	 * @return
	 */
	public static String createFileName() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//加上三位随机数
		Random random = new Random();
		int end3 = random.nextInt(999);
		//如果不足三位前面补0
		String str = millis + String.format("%03d", end3);
		return str;
	}
	
	/**
	 * Multipart文件转化为输入流
	 * @author lizhiyong
	 * @date 2018年10月19日
	 * @param file
	 * @return
	 */
	public static InputStream multipartFileToInputStream(MultipartFile file) {
		try {
			CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) file;
	        DiskFileItem diskFileItem = (DiskFileItem) commonsMultipartFile.getFileItem();
	        InputStream inputStream = diskFileItem.getInputStream();
			return inputStream;
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
}
