package com.jdjr.opinion.utils;

import com.jdjr.opinion.constants.SysConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static void writeContentToFile(String filePath, String content) {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.setWritable(true, false);
                file.setReadable(true, false);
                file.createNewFile();
            }
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),
                    SysConst.ENCODING_UTF_8));
            bufferedWriter.write(content);
            bufferedWriter.flush();
        } catch (FileNotFoundException e) {
            logger.error("FileNotFound", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                logger.error("BufferedWriter 关闭失败", e);
            }
        }
    }

    public static void deleteFiles(File directory){
        if(directory != null && directory.isDirectory()){
            File[] files = directory.listFiles();
            if(files != null){
                for(File file : files){
                    file.delete();
                }
            }
        }
    }

    public static void modifyFilePermission(File file){
        if(!file.exists()){
            file.mkdirs();
            if(!StringUtils.isWindows()){
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null){
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
