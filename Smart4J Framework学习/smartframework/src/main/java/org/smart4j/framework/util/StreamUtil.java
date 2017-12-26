package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 流操作工具类
 * Created by wangjianchun on 2017/11/9.
 */
public final class StreamUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

    public static String getString(InputStream is){
        StringBuilder sd = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine()) != null){
                sd.append(line);
            }
        } catch (IOException e) {
            LOGGER.error("get string failure", e);
            throw new RuntimeException(e);
        }

        return sd.toString();
    }

    /**
     * 将输入流复制到输出流
     * @param inputStream
     * @param outputStream
     */
    public static void copyStream(InputStream inputStream, OutputStream outputStream){
        try {
            int length;
            byte[] buffer = new byte[4*1024];
            while((length = inputStream.read(buffer, 0, buffer.length)) != -1){
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
        } catch (IOException e) {
            LOGGER.error("copy stream failure", e);
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                LOGGER.error("close stream failure", e);
            }
        }
    }

}
