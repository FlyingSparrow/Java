package com.huishu.ieanalysis.utils;

import com.huishu.ieanalysis.constants.SysConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author wangjianchun
 */
public class FileUtils {

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils(){}

    public static String readFile(String filePath) {
        BufferedReader br = null;
        StringBuilder content = new StringBuilder();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(file), SysConst.ENCODING_UTF_8));
                String line = null;
                while ((line = br.readLine()) != null) {
                    content.append(line.trim());
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("FileNotFound", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                logger.error("关闭流资源失败", e);
            }
        }
        return content.toString();
    }


    public static boolean copeFile(String source, String target) {
        boolean flag = false;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(source));
            bw = new BufferedWriter(new FileWriter(target));
            String line = null;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
            bw.flush();
            flag = true;
        } catch (Exception e) {
            logger.error("文件复制失败,源文件名: {}, error: ", source, e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                logger.error("关闭流资源失败", e);
            }
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                logger.error("关闭流资源失败", e);
            }
        }
        return flag;

    }
}
