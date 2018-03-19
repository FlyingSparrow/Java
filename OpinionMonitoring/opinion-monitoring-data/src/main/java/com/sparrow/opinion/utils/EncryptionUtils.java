package com.sparrow.opinion.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * <p>Title: EncryptionUtils</p>
 * <p>Description: 加密解密工具类</p>
 *
 * @author wjc
 * @date 2016年12月29日
 */
public class EncryptionUtils {

    public static String base64Encode(String data) {
        return Base64.encodeBase64String(data.getBytes());
    }

    public static byte[] base64Decode(String data) {
        return Base64.decodeBase64(data.getBytes());
    }

    public static String md5(String data) {
        return DigestUtils.md5Hex(data);
    }

    public static String sha1(String data) {
        return DigestUtils.sha1Hex(data);
    }

    public static String sha256Hex(String data) {
        return DigestUtils.sha256Hex(data);
    }

}
