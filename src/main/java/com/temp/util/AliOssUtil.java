package com.temp.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 阿里云 OSS 存储工具类
 *
 * @author Hollis
 * @since 2024/04/07 下午2:26
 */
@Log4j2
@Component
public class AliOssUtil {

    /**
     * 阿里云上传文件的过期时间, 单位毫秒, 这里设置100年
     */
    private static final Long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 360L * 100L;

    @Value("${ali-oss.key:}")
    private String key;
    @Value("${ali-oss.secret:}")
    private String secret;
    @Value("${ali-oss.endpoint:}")
    private String endpoint;
    @Value("${ali-oss.bucket:}")
    private String bucket;
    @Value("${ali-oss.basePath:}")
    private String basePath;


    /**
     * 文件上传
     *
     * @param file 文件
     * @return {@link String}
     */
    public String upload(File file) {
        return this.doUpload(file, buildObjectName(file.getName()));
    }


    /**
     * 文件流上传
     *
     * @param file 文件
     * @return {@link String}
     */
    public String upload(MultipartFile file) {
        try (InputStream ips = file.getInputStream()) {
            return doUpload(ips, buildObjectName(file.getOriginalFilename()));
        } catch (Exception e) {
            log.error("[阿里云-OSS]-upload file stream error, fileName={}, error={}", file.getOriginalFilename(), e.getMessage(), e);
        }
        return null;
    }


    /**
     * 获取可下载url
     *
     * @param objectName 文件名称
     * @return {@link String}
     */
    public String getDownloadableUrl(String objectName) {
        Date expiration = new Date(new Date().getTime() + EXPIRATION_TIME);
        String attachment = "?response-content-disposition=" + "attachment";
        String attachmentEncoder;
        try {
            attachmentEncoder = encoder(attachment);
        } catch (Exception e) {
            log.error("[阿里云-OSS]-获取url失败", e);
            return null;
        }
        String key = objectName + attachment;
        GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucket, key);
        // 设置单链接限速，单位为bit，例如限速100 KB/s。
        // urlRequest.setTrafficLimit(100 * 1024 * 8);
        urlRequest.setExpiration(expiration);
        OSS ossClient = getOssClient();
        return ossClient
                .generatePresignedUrl(urlRequest)
                .toString()
                .replace(attachmentEncoder + "?", attachment + "&")
                .replace("-internal", "")
                .replace("http:", "https:");
    }


    // ----------------------- 以下为私有方法 -----------------------


    /**
     * 获取oss客户端
     *
     * @return {@link OSS}
     */
    private synchronized OSS getOssClient() {
        return new OSSClientBuilder()
                .build(endpoint, key, secret);
    }


    /**
     * 文件上传
     *
     * @param file       文件
     * @param objectName 文件名称
     * @return {@link String} 文件Url
     */
    private String doUpload(File file, String objectName) {
        try (InputStream inputStream = new FileInputStream(file)) {
            return doUpload(inputStream, objectName);
        } catch (Exception e) {
            log.error("[阿里云-OSS]-文件上传失败: fileName={}, errorMsg={}", objectName, e.getMessage(), e);
        }
        return null;
    }


    /**
     * 文件流上传
     * 同名文件覆盖
     *
     * @param inputStream 输入流
     * @param objectName  文件名称
     * @return {@link String}
     */
    private String doUpload(InputStream inputStream, String objectName) {
        OSS ossClient = null;
        try {
            // 创建OSSClient实例
            ossClient = this.getOssClient();

            // 上传文件流
            PutObjectResult putObjectResult = ossClient.putObject(bucket, objectName, inputStream);
            log.info("[阿里云-OSS]-文件上传成功, 结果：fileName={}, putObjectResult={}", objectName, JSONUtil.toJsonStr(putObjectResult));

            // 获取文件下载路径
            String fileUrl = getDownloadableUrl(objectName);
            log.info("[阿里云-OSS]-获取文件下载路径成功, fileName: {}, 下载路径: {}", objectName, fileUrl);

            return fileUrl;
        } catch (OSSException oe) {
            log.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.error("Error Message: {}, Error Code:{}, Request ID: {}, Host ID: {}",
                    oe.getErrorMessage(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());
            log.error("[阿里云-OSS]-文件上传失败，被OSS拒绝: fileName={}, errorMsg={}", objectName, oe.getMessage(), oe);
        } catch (Exception oe) {
            log.error("[阿里云-OSS]-文件上传失败，相关信息: fileName={}, errorMsg={}", objectName, oe.getMessage(), oe);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }


    /**
     * 编码器
     *
     * @param s s
     * @return {@link String}
     */
    private String encoder(String s) {
        return URLEncoder
                .encode(s, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20")
                .replaceAll("\\*", "%2A");
    }


    /**
     * 获取文件全路径
     *
     * @return java.lang.String
     */
    private String buildObjectName(String fileName) {
        Date date = new Date();
        return basePath
                + "/" + DateUtil.year(date)
                + "/" + (DateUtil.month(date) + 1)
                + "/" + DateUtil.dayOfMonth(date)
                + "/" + IdUtil.fastSimpleUUID()
                + "/" + fileName;
    }
}
