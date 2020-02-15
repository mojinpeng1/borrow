package com.mjp.borrow.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description：</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/22 13:56
 */


@Data
public class MinioProperties {
    private String url;
    private String account;
    private String pass;
}
