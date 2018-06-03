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
            logger.error("ileNotFound", e);
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

    public static void writeContentToFile(String filePath, String content) {
        BufferedWriter bw = null;
        try {
            File file = new File(filePath);
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), SysConst.ENCODING_UTF_8));
            bw.write(content);
            bw.flush();
        } catch (FileNotFoundException e) {
            logger.error("FileNotFound", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                logger.error("关闭流资源失败", e);
            }
        }
    }

    public static synchronized void writeProperties(String filePath, Map<String, String> map) {
        OutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);

            Properties prop = new Properties();
            if (map != null && map.size() > 0) {
                for (Map.Entry<String, String> key : map.entrySet()) {
                    prop.setProperty(key.getKey(), key.getValue());
                }
            }
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            prop.store(fos, "Update value success");
        } catch (IOException e) {
            logger.error("写入属性文件失败", e);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                logger.error("关闭流资源失败", e);
            }
        }
    }

    /**
     * 如果文件不存在，那么创建文件并设置文件的读写权限；否则直接返回
     * 如果file为null，那么直接返回false
     *
     * @param file
     * @return
     */
    public static boolean createFileIfNotExists(File file) {
        try {
            if (file == null) {
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
        }
        return false;
    }

    /**
     * 从 classpath 路径下按行读取指定文件
     *
     * @param fileName
     * @return Properties 对象
     */
    public static Properties getProperties(String fileName) {
        Properties result = null;

        BufferedReader br = null;
        try {
            InputStream inputStream = FileUtils.class.getResource("/" + fileName).openStream();
            br = new BufferedReader(new InputStreamReader(inputStream, SysConst.ENCODING_UTF_8));
            result = new Properties();
            result.load(br);
        } catch (Exception e) {
            logger.error("读取文件失败", e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                logger.error("关闭流资源失败", e);
            }
        }

        return result;
    }

}
