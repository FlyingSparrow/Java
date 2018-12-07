package com.sparrow.utils;

import com.sparrow.base.vo.UploadFile;
import com.sparrow.constants.SysConst;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * <p>Title: FileUtil</p>
 * <p>Description: 文件操作工具类</p>
 *
 * @author wjc
 * @date 2018/12/5
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static void writeFile(String filePath, String content) {
        if (StringUtils.isEmpty(filePath)) {
            logger.info("filePath is null or filePath is empty");
            return;
        }
        writeFile(new File(filePath), content);
    }

    public static void writeFile(File file, String content) {
        BufferedWriter bw = null;
        try {
            if (file == null || StringUtils.isEmpty(content)) {
                return;
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.setWritable(true, false);
                file.setReadable(true, false);
                file.createNewFile();
            }
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),
                    SysConst.ENCODING_UTF_8));
            bw.write(content);
            bw.flush();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(bw);
        }
    }

    public static void copyFile(InputStream source, File target) {
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
            logger.error(e.getMessage(), e);
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
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(br);
        }
        return sd.toString();
    }

    public static void deleteFiles(File directory) {
        if (directory != null && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files == null) {
                return;
            }
            for (File file : files) {
                file.delete();
            }
        }
    }

    public static void modifyFilePermission(File file) {
        if (!file.exists()) {
            file.mkdirs();
            if (!com.sparrow.utils.StringUtil.isWindows()) {
                //如果不是windows系统，需要修改文件权限
                try {
                    Runtime.getRuntime().exec("sudo chmod 777 " + file.getAbsolutePath());
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 从 classpath 路径下按行读取指定文件的内容
     *
     * @param fileName
     * @return 按行读取的内容列表
     */
    public static List<String> getContentFromClassPath(String fileName) {
        List<String> result = new ArrayList<String>();

        BufferedReader br = null;
        try {
            InputStream inputStream = FileUtil.class.getResource("/" + fileName).openStream();
            br = new BufferedReader(new InputStreamReader(inputStream, SysConst.ENCODING_UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(br);
        }
        return result;
    }

    /**
     * 从 classpath 路径下按行读取指定文件的内容
     *
     * @param fileName
     * @return 按行读取的内容列表
     */
    public static String readContentFromClassPath(String fileName) {
        StringBuilder result = new StringBuilder();

        BufferedReader br = null;
        try {
            InputStream inputStream = FileUtil.class.getResource("/" + fileName).openStream();
            br = new BufferedReader(new InputStreamReader(inputStream, SysConst.ENCODING_UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(br);
        }
        return result.toString();
    }

    public static void close(Reader br) {
        try {
            if (br != null) {
                br.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void close(OutputStream bos) {
        try {
            if (bos != null) {
                bos.close();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void close(InputStream bis) {
        try {
            if (bis != null) {
                bis.close();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void close(Writer bw) {
        try {
            if (bw != null) {
                bw.close();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 上传文件，并获取上传文件（单文件上传）
     *
     * @param request    [requewt请求]
     * @param folderPath [文件保存路]
     * @return [文件自定义实体类]
     */
    public static UploadFile getFile(HttpServletRequest request, String folderPath) {
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        try {
            // 判断 request 是否有文件上传,即多部分请求
            if (!multipartResolver.isMultipart(request)) {
                return null;
            }

            // 新建目录
            File directory = new File(folderPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                UploadFile uploadFile = transferFile(folderPath, multiRequest.getFile(iter.next()));
                if (uploadFile != null) {
                    return uploadFile;
                }
            }
        } catch (Exception e) {
            logger.error("上传文件出错", e);
        }
        return null;
    }

    /**
     * 上传文件，并获取上传文件列表（多文件上传）
     *
     * @param request    [requewt请求]
     * @param folderPath [文件保存路]
     * @return [文件自定义实体类]
     */
    public static List<UploadFile> getFiles(HttpServletRequest request, String folderPath) {
        List<UploadFile> files = new ArrayList<>();
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (!multipartResolver.isMultipart(request)) {
            return files;
        }

        // 新建目录
        File directory = new File(folderPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        // 转换成多部分request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        // 取得request中的所有文件名
        Iterator<String> iter = multiRequest.getFileNames();
        while (iter.hasNext()) {
            List<MultipartFile> multipartFiles = multiRequest.getFiles(iter.next());
            multipartFiles.stream().forEach(multipartFile -> {
                try {
                    UploadFile f = transferFile(folderPath, multipartFile);
                    if (f != null) {
                        files.add(f);
                    }
                } catch (IOException e) {
                    logger.error("上传文件出错", e);
                }
            });
        }
        return files;
    }

    /**
     * 文件写入磁盘
     *
     * @param folderPath [description]
     * @param file       [description]
     * @return [description]
     * @throws IOException [description]
     */
    private static UploadFile transferFile(String folderPath, MultipartFile file) throws IOException {
        if (file == null) {
            return null;
        }
        //原名称 带后缀
        String originalFileName = file.getOriginalFilename();
        if (StringUtils.isEmpty(originalFileName.trim())) {
            return null;
        }

        // 取得当前上传文件的文件名称
        String fileMD5 = DigestUtils.md5Hex(file.getBytes());
        //原名称
        String fileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        //后缀名
        String suffixName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        //单位：M
        double size = (file.getSize() * 1.0) / (1024 * 1.0) / (1024 * 1.0);
        BigDecimal bg = new BigDecimal(size);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        String fileSize = f1 + "";

        // 如果名称不为"",说明该文件存在，否则说明该文件不存在
        // 重命名上传后的文件名
        String newName = UUID.randomUUID().toString() + "." + suffixName;
        File localFile = new File(folderPath + "/" + newName);
        org.apache.commons.io.FileUtils.copyInputStreamToFile(file.getInputStream(), localFile);
        UploadFile uploadFile = new UploadFile()
                .setFile(localFile)
                .setFullFileName(newName)
                .setFileName(fileName)
                .setOriginalFileName(originalFileName)
                .setSuffixName(suffixName)
                .setFileSize(fileSize)
                .setFileMD5(fileMD5);

        return uploadFile;
    }

    /**
     * 删除文件
     */
    public static boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除文件与目录
     *
     * @param filePath
     * @return
     */
    public static boolean deleteFolder(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 判断目录或文件是否存在
        if (!file.exists()) {
            // 不存在返回 false
            return flag;
        }
        // 判断是否为文件
        if (file.isFile()) {
            // 为文件时调用删除文件方法
            return deleteFile(filePath);
        } else { // 为目录时调用删除目录方法
            return deleteDirectory(filePath);
        }
    }

    /**
     * 删除目录
     *
     * @param filePath
     * @return
     */
    public static boolean deleteDirectory(String filePath) {
        boolean flag = false;
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        File dirFile = new File(filePath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return flag;
        }
        flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName, File file) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            if (!file.exists()) {
                throw new RuntimeException("文件不存在！");
            }
            if (!file.isFile()) {
                throw new RuntimeException("非文件类型！");
            }

            String encodedFileName = new String(fileName.getBytes(), SysConst.ENCODING_ISO_8859_1);
            if (com.sparrow.utils.StringUtil.isIE(request)) {
                encodedFileName = URLEncoder.encode(fileName, SysConst.ENCODING_UTF_8);
            }

            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("content-disposition", "attachment; filename=" + encodedFileName);

            byte[] buffer = new byte[4096];
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(response.getOutputStream());
            int length = -1;
            while ((length = bis.read(buffer, 0, buffer.length)) != -1) {
                bos.write(buffer, 0, length);
            }
            bos.flush();

            logger.info("文件下载完毕！");
        } catch (IOException e) {
            logger.error("下载文件出错", e);
            throw new RuntimeException(e);
        } finally {
            close(bis);
            close(bos);
        }
    }

    /**
     * 复制文件
     *
     * @param sourcePath
     * @param destPath
     * @throws IOException
     */
    public static boolean copyFileUsingFileStreams(String sourcePath, String destPath) throws IOException {
        File source = new File(sourcePath);
        File dest = new File(destPath);
        if (!source.exists()) {
            throw new IOException("文件复制失败：源文件（" + source + "） 不存在");
        }
        if (dest.isDirectory()) {
            throw new IOException("文件复制失败：复制路径（" + dest + "） 错误");
        }

        File parent = dest.getParentFile();
        // 创建复制路径
        if (!parent.exists()) {
            parent.mkdirs();
        }
        // 创建复制文件
        if (!dest.exists()) {
            dest.createNewFile();
        }

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(source));
            bos = new BufferedOutputStream(new FileOutputStream(dest));
            byte[] buffer = new byte[4096];
            int length;
            while ((length = bis.read(buffer, 0, buffer.length)) != -1) {
                bos.write(buffer, 0, length);
            }

            if (dest.length() != dest.length()) {
                return false;
            } else {
                return true;
            }
        } finally {
            close(bos);
            close(bis);
        }
    }

    /**
     * 复制文件
     *
     * @param sourcePath
     * @param destPath
     * @throws IOException
     */
    public static void copyFileUsingJava7Files(String sourcePath, String destPath) throws IOException {
        File source = new File(sourcePath);
        File dest = new File(destPath);
        Files.copy(source.toPath(), dest.toPath());
    }

    /**
     * 上传文件
     * @param fileBytes
     * @param filePath
     * @param fileName
     */
    public static void uploadFile(byte[] fileBytes, String filePath, String fileName) {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath+fileName);
            out.write(fileBytes);
            out.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            try {
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }

    }

}
