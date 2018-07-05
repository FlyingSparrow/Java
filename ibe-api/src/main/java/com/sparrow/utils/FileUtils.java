package com.sparrow.utils;

import com.sparrow.constants.SysConst;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具类
 *
 * @author wangjianchun
 * @date 2018-7-4
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static synchronized void writeContentToFile(String filePath, String content) {
        BufferedWriter bw = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.setWritable(true, false);
                file.setReadable(true, false);
                file.createNewFile();
            }
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),
                    SysConst.ENCODING_UTF_8));
            bw.write(content);
            bw.flush();
        } catch (FileNotFoundException e) {
            logger.error("FileNotFound", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            close(bw);
        }
    }

    public static void copyFile(InputStream source, File target){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(source);
            bos = new BufferedOutputStream(new FileOutputStream(target));
            byte[] buffer = new byte[4096];
            int length;
            while ((length = bis.read(buffer, 0, buffer.length)) != -1) {
                bos.write(buffer, 0, length);
            }
        } catch (IOException e) {
            logger.error("读写文件出错", e);
        } finally {
            close(bis);
            close(bos);
        }
    }

    /**
     * 使用指定的字符集读取文件的内容并返回
     * 说明：如果指定的字符集为空，那么默认将使用UTF-8字符集读取文件内容
     *
     * @param file
     * @param charset
     * @return
     */
    public static String readFile(File file, String charset) {
        StringBuilder sd = new StringBuilder();
        BufferedReader br = null;
        try {
            if (file != null && file.isFile()) {
                String line = null;
                if (StringUtils.isNotEmpty(charset)) {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(file),
                            charset));
                } else {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(file),
                            SysConst.ENCODING_UTF_8));
                }
                while ((line = br.readLine()) != null) {
                    sd.append(line);
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("FileNotFound", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            close(br);
        }
        return sd.toString();
    }

    public static void deleteFiles(File directory){
        if(directory != null && directory.isDirectory()){
            File[] files = directory.listFiles();
            if(files == null){
                return;
            }
            for(File file : files){
                file.delete();
            }
        }
    }

    public static void modifyFilePermission(File file){
        if(!file.exists()){
            file.mkdirs();
            if(!com.sparrow.utils.StringUtils.isWindows()){
                //如果不是windows系统，需要修改文件权限
                try {
                    Runtime.getRuntime().exec("sudo chmod 777 "+file.getAbsolutePath());
                } catch (IOException e) {
                    logger.error("修改文件权限出错", e);
                }
            }
        }
    }

    /**
     * 从 classpath 路径下按行读取指定文件的内容
     * @param fileName
     * @return 按行读取的内容列表
     */
    public static List<String> getContentFromClassPath(String fileName) {
        List<String> result = new ArrayList<String>();

        BufferedReader br = null;
        try {
            InputStream inputStream = FileUtils.class.getResource("/"+fileName).openStream();
            br = new BufferedReader(new InputStreamReader(inputStream, SysConst.ENCODING_UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            close(br);
        }
        return result;
    }

    /**
     * 从 classpath 路径下按行读取指定文件的内容
     * @param fileName
     * @return 按行读取的内容列表
     */
    public static String readContentFromClassPath(String fileName) {
        StringBuilder result = new StringBuilder();

        BufferedReader br = null;
        try {
            InputStream inputStream = FileUtils.class.getResource("/"+fileName).openStream();
            br = new BufferedReader(new InputStreamReader(inputStream, SysConst.ENCODING_UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            close(br);
        }
        return result.toString();
    }

    public static void close(Reader br){
        try {
            if(br != null){
                br.close();
            }
        } catch (Exception e) {
            logger.error("BufferedReader 关闭失败", e);
        }
    }

    public static void close(OutputStream bos) {
        try {
            if (bos != null) {
                bos.close();
            }
        } catch (IOException e) {
            logger.error("BufferedOutputStream 关闭出错", e);
        }
    }

    public static void close(InputStream bis) {
        try {
            if (bis != null) {
                bis.close();
            }
        } catch (IOException e) {
            logger.error("BufferedInputStream 关闭出错", e);
        }
    }

    public static void close(Writer bw) {
        try {
            if (bw != null) {
                bw.close();
            }
        } catch (IOException e) {
            logger.error("BufferedWriter 关闭失败", e);
        }
    }

}
