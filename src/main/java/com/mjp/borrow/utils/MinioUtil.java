package com.mjp.borrow.utils;

import com.mjp.borrow.base.exception.CommonException;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Description：minio工具类</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/22 13:19
 */
@Data
@Slf4j
@Component
public class MinioUtil {

    /**
     * 文件方式传输
     */
    public static final String APPLICATION_STREAM = "application/octet-stream";
    /**
     * <p>方法名:getConn</p>
     * <p>描述: 获取Minio连接</p>
     * <p>创建时间: 2020/1/22 13:29</p>
     *
     * @return MinioClient
     * @author mojinpeng
     */
//    @Autowired
//    private BorrowProperties borrowProperties;
    @Value("${example.borrow.minio.url}")
    private String url;
    @Value("${example.borrow.minio.account}")
    private String account;
    @Value("${example.borrow.minio.pass}")
    private String pass;

    public MinioClient getConn() {
        try {
//            MinioClient client = new MinioClient(borrowProperties.getMinio().getUrl(),
//                    borrowProperties.getMinio().getAccount(), borrowProperties.getMinio().getPass());
            MinioClient client = new MinioClient("http://101.133.136.205:9000/", "minioadmin", "minioadmin");
            return client;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * <p>方法名:checkOrCreateBucket</p>
     * <p>描述: 检查并创建bucket</p>
     * <p>创建时间: 2020/1/22 13:32</p>
     *
     * @param bucketName bucket
     * @author mojinpeng
     */
    public void checkOrCreateBucket(MinioClient conn, String bucketName) {
        try {
            boolean b = conn.bucketExists(bucketName);
            if (b) {
                log.info("bucket is already exist");
            } else {
                conn.makeBucket(bucketName);
                log.info("{} already created!", bucketName);
            }
        } catch (Exception e) {
            throw new CommonException(e.getMessage());
        }
    }


    /**
     * <p>方法名:uploadToBucket</p>
     * <p>描述: 上传文件到bucket中</p>
     * <p>创建时间: 2020/2/1 13:10</p>
     *
     * @param conn       minio连接对象
     * @param file       上传的文件  基础web文件上传
     * @param bucketName 上传的路径
     * @author mojinpeng
     */
    public boolean uploadToBucket(MinioClient conn, MultipartFile file, String bucketName) {
        if (conn == null) {
            conn = getConn();
        }
        String name = file.getOriginalFilename();
        checkOrCreateBucket(conn, bucketName);
        try {
            conn.putObject(bucketName, name, file.getInputStream(), file.getSize(), APPLICATION_STREAM);
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
            throw new CommonException("不合法的存储桶名称。");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new CommonException(" 找不到相应的签名算法。");

        } catch (InsufficientDataException e) {
            e.printStackTrace();
            throw new CommonException("在读到相应length之前就得到一个EOFException。");

        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonException("IOException : 连接异常。");

        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new CommonException("InvalidKeyException : 不合法的access key或者secret key。");

        } catch (NoResponseException e) {
            e.printStackTrace();
            throw new CommonException("NoResponseException : 服务器无响应。\n");

        } catch (XmlPullParserException e) {
            e.printStackTrace();
            throw new CommonException("org.xmlpull.v1.XmlPullParserException : 解析返回的XML异常。");

        } catch (ErrorResponseException e) {
            e.printStackTrace();
            throw new CommonException("ErrorResponseException : 执行失败。");

        } catch (InternalException e) {
            e.printStackTrace();
            throw new CommonException("内部异常。");

        } catch (InvalidArgumentException e) {
            e.printStackTrace();
            throw new CommonException("参数异常。");
        }
        return true;

    }

    /**
     * <p>方法名:removeObject</p>
     * <p>描述: 删除文件</p>
     * <p>创建时间: 2020/2/1 13:19</p>
     *
     * @param conn       连接对象
     * @param bucketName 文件夹
     * @param objectName 文件名
     * @return boolean
     * @author mojinpeng
     */
    public boolean removeObject(MinioClient conn, String bucketName, String objectName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        if (conn == null) {
            conn = getConn();
        }
        boolean b = conn.bucketExists(bucketName);
        if (b) {
            conn.removeObject(bucketName, objectName);
        } else {
            throw new CommonException(String.format("%s 该bucket不存在!", bucketName));
        }
        return true;
    }

    /**
     * <p>方法名:getObject</p>
     * <p>描述: 获取对象</p>
     * <p>创建时间: 2020/2/1 14:07</p>
     *
     * @param conn
     * @return InputStream
     * @author mojinpeng
     */
    public InputStream getObject(MinioClient conn, String bucketName, String objectName) {
        if (conn == null) {
            conn = getConn();
        }
        InputStream object = null;
        try {
            object = conn.getObject(bucketName, objectName);
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
            throw new CommonException("不合法的存储桶名称。");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new CommonException(" 找不到相应的签名算法。");

        } catch (InsufficientDataException e) {
            e.printStackTrace();
            throw new CommonException("在读到相应length之前就得到一个EOFException。");

        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonException("IOException : 连接异常。");

        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new CommonException("InvalidKeyException : 不合法的access key或者secret key。");

        } catch (NoResponseException e) {
            e.printStackTrace();
            throw new CommonException("NoResponseException : 服务器无响应。\n");

        } catch (XmlPullParserException e) {
            e.printStackTrace();
            throw new CommonException("org.xmlpull.v1.XmlPullParserException : 解析返回的XML异常。");

        } catch (ErrorResponseException e) {
            e.printStackTrace();
            throw new CommonException("ErrorResponseException : 执行失败。");

        } catch (InternalException e) {
            e.printStackTrace();
            throw new CommonException("内部异常。");

        } catch (InvalidArgumentException e) {
            e.printStackTrace();
            throw new CommonException("参数异常。");
        }
        return object;

    }
}
