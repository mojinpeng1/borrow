package com.mjp.borrow.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description：minio属性读取</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/22 13:49
 */
@ConfigurationProperties(prefix = "example.borrow")
public class BorrowProperties {
    @Getter
    @Setter
    private MinioProperties minio;
}
