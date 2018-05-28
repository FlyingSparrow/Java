package com.huishu.utils;

import com.huishu.constants.SysConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * @author wangjianchun
 */
public class FileUtils {

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {
    }

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
            e.printStackTrace();
            logger.error("readFile---FileNotFound", e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("readFile----UnsupportedEncodingException", e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("readFile---IOException", e);
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

    public static void writeContentToFile(String filePath, String content) {
        BufferedWriter bw = null;
        try {
            File file = new File(filePath);
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), SysConst.ENCODING_UTF_8));
            bw.write(content);
            bw.flush();
        } catch (FileNotFoundException e) {
            logger.error("writeContentToFile---FileNotFound", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("writeContentToFile----UnsupportedEncodingException", e);
        } catch (IOException e) {
            logger.error("writeContentToFile---IOException", e);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                logger.error("writeContentToFile关闭失败", e);
            }
        }
    }

    public static synchronized void writeProperties(String filePath, Map<String, String> map) {
        Properties prop = new Properties();
        InputStream fis = null;
        OutputStream fos = null;
        try {
            fis = new FileInputStream(filePath);
            // 从输入流中读取属性列表（键和元素对）
            // 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            fos = new FileOutputStream(filePath);
            if (map != null && map.size() > 0) {
                for (Map.Entry<String, String> key : map.entrySet()) {
                    prop.setProperty(key.getKey(), key.getValue());
                }
            }
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            prop.store(fos, "Update value success");
            logger.info("temp配置更新成功");
        } catch (IOException e) {
            logger.error("temp配置更新失败");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error("temp配置输入关闭失败");
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error("temp配置输出关闭失败");
                }
            }
        }
    }

    /**
     * 如果文件不存在，那么创建文件并设置文件的读写权限；否则直接返回
     * 如果file为null，那么直接返回false
     * @param file
     * @return
     */
    public static boolean createFileIfNotExists(File file){
        try {
            if(file == null){
                return false;
            }
            if (!file.exists()) {
                file.setReadable(true, false);
                file.setWritable(true, false);
                file.createNewFile();
            }
            return true;
        } catch (IOException e) {
            logger.error("创建文件失败", e);
            return false;
        }
    }

}
