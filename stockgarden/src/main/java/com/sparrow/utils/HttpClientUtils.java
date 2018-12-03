package com.sparrow.utils;

import com.sparrow.constants.SysConst;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * HTTP 操作工具类
 *
 * @author wangjianchun
 * @date 2018-7-4
 */
public class HttpClientUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    private static final HttpClientUtils INSTANCE = new HttpClientUtils();

    /**
     * setConnectTimeout 设置连接超时时间
     * setConnectionRequestTimeout 设置请求超时时间
     * setSocketTimeout 设置读数据超时时间(单位毫秒)
     * setRedirectsEnabled 默认允许自动重定向
     */
    private static final RequestConfig REQUEST_CONFIG = RequestConfig
            .custom()
            .setConnectTimeout(10000)
            .setConnectionRequestTimeout(30000)
            .setSocketTimeout(10000)
            .setRedirectsEnabled(true)
            .build();
    /**
     * 最大尝试次数
     */
    private static final int MAX_RETRY = 3;

    private HttpClientUtils() {
    }

    public static HttpClientUtils getInstance() {
        return INSTANCE;
    }

    public String httpPost(String username, String pwd, String url, String requestXML) {
        logger.info("url: {}, requestXML: {}", url, requestXML);

        StringBuilder result = new StringBuilder();

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        ByteArrayOutputStream out = null;
        InputStream is = null;
        BufferedReader br = null;
        try {
            httpClient = getCustomHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(REQUEST_CONFIG);
            httpPost.setHeader(new BasicHeader("Content-Type", "text/html;charset=UTF-8"));
            httpPost.setHeader(new BasicHeader("accept-encoding", "gzip"));
            httpPost.setHeader(new BasicHeader("content-encoding", "gzip"));

            out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            if (StringUtils.isNotEmpty(requestXML)) {
                gzip.write(requestXML.getBytes(SysConst.ENCODING_UTF_8));
            }
            gzip.close();
            HttpEntity paramEntity = new ByteArrayEntity(out.toByteArray());
            httpPost.setEntity(paramEntity);

            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, pwd));
            HttpClientContext context = HttpClientContext.create();
            context.setCredentialsProvider(credentialsProvider);

            try {
                response = httpClient.execute(httpPost, context);
            } catch (SocketTimeoutException e1) {
                if (e1.toString().indexOf("java.net.SocketTimeoutException: Read timed out") != -1) {
                    logger.error("The server response timeout, error：{}", e1.toString());
                }
            }
            if (response != null) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    is = response.getEntity().getContent();
                    if (is != null) {
                        try {
                            br = new BufferedReader(new InputStreamReader(new GZIPInputStream(is), SysConst.ENCODING_UTF_8));
                        } catch (IOException e) {
                            if (e.toString().indexOf("java.io.IOException: Not in GZIP format") != -1) {
                                br = new BufferedReader(new InputStreamReader(is, SysConst.ENCODING_UTF_8));
                            }
                            logger.error("IOException", e);
                        }

                        if (br != null) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                result.append(line);
                            }
                            if (result.toString().startsWith("tml>")) {
                                result.insert(0, "<h");
                            }
                        }
                    }
                } else if (statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
                    // 302
                    logger.error("访问地址已经改变请更新访问地址");
                } else {
                    logger.error("操作失败，url: {}, statusCode: {}, params: {}", url, statusCode, requestXML);
                }
            }

            logger.info("responseXml: {}", result);

            out.flush();
        } catch (IOException e) {
            logger.error("执行 Http 请求出错", e);
            throw new RuntimeException(e);
        } catch (Exception e){
            logger.error("执行 Http 请求出错", e);
            throw new RuntimeException(e);
        }finally {
            FileUtils.close(out);
            FileUtils.close(is);
            FileUtils.close(br);
            close(response);
            close(httpClient);
        }

        return result.toString();
    }

    public String httpPost(String url) {
        logger.info("url: {}", url);

        String result = "";

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = getCustomHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(REQUEST_CONFIG);
            httpPost.setHeader(new BasicHeader("Content-Type", "text/html;charset=UTF-8"));

            try {
                response = httpClient.execute(httpPost);
            } catch (SocketTimeoutException e1) {
                if (e1.toString().indexOf("java.net.SocketTimeoutException: Read timed out") != -1) {
                    logger.error("The server response timeout, error：{}", e1.toString());
                }
            }
            if (response != null) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        result = EntityUtils.toString(entity);
                    }
                } else if (statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
                    // 302
                    logger.error("访问地址已经改变请更新访问地址");
                } else {
                    logger.error("操作失败，url: {}, statusCode: {}, params: {}", url, statusCode);
                }
            }
        } catch (Exception e) {
            logger.error("执行 Http 请求出错", e);
        } finally {
            close(response);
            close(httpClient);
        }

        return result;
    }

    public String httpPost(String url, Map<String, String> paramMap) {
        return httpPost(url, paramMap, SysConst.ENCODING_UTF_8);
    }

    public String httpPost(String url, Map<String, String> paramMap, String charset) {
        // 配置传递的参数
        List<BasicNameValuePair> parameters = new ArrayList<>();
        if (paramMap != null) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue() + ""));
            }
        }
        parameters.add(new BasicNameValuePair("livedCode", String.valueOf(System.currentTimeMillis())));
        return post(url, parameters, charset, 0);
    }

    /**
     * HttpPost请求，控制尝试次数
     *
     * @param url
     * @param parameters
     * @param charset
     * @param reTry
     * @return
     */
    private String post(String url, List<BasicNameValuePair> parameters, String charset, int reTry) {
        String result = "";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = getHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(REQUEST_CONFIG);
            httpPost.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=" + charset));
            HttpEntity paramEntity = new UrlEncodedFormEntity(parameters, charset);
            httpPost.setEntity(paramEntity);
            // 执行请求访问
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                }
            } else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY) {
                // 302
                logger.error("访问地址已经改变请更新访问地址");
            } else {
                logger.error("操作失败，Request URL：{}, params：{}", url, parameters.toString());
            }
        } catch (Exception e) {
            if (reTry < MAX_RETRY) {
                reTry++;
                logger.error("请求失败，尝试再次请求：{},Request URL：{}, params：{}", reTry, url, parameters.toString());
                return post(url, parameters, charset, reTry);
            } else {
                logger.error("请求异常，已超出最大尝试次数：{}，Request URL：{}, params：{},Exceptipn:{}", MAX_RETRY, url, parameters.toString(), e);
            }
        } finally {
            close(response);
            close(httpClient);
        }
        return result;
    }

    private CloseableHttpClient getHttpClient() {
        return HttpClients.createDefault();
    }

    private CloseableHttpClient getCustomHttpClient() {
        /**
         * HttpRequestRetryHandler这是发送请求前连接失败的情况处理，
         * 而ServiceUnavailableRetryStrategy 是发送请求成功后根据返回状态做处理。
         * 两个不是一回事。如果需要根据返回结果做统一处理，需要配置这个参数。
         * HttpClients .custom() .setServiceUnavailableRetryStrategy(serviceUnavailableRetryStrategy );
         */

        //最大重试次数
        int maxRetries = 3;
        //重试间隔，单位：毫秒
        int retryInterval = 2000;
        CloseableHttpClient httpClient = HttpClients.custom()
                .setServiceUnavailableRetryStrategy(new DefaultServiceUnavailableRetryStrategy(maxRetries, retryInterval))
                .setRetryHandler(new DefaultHttpRequestRetryHandler()).build();
        return httpClient;
    }

    private void close(CloseableHttpResponse response) {
        try {
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close(CloseableHttpClient httpClient) {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            logger.error("关闭 HttpClient 出错", e);
        }
    }

}
