package com.mjp.borrow.base.config;

import com.mjp.borrow.properties.BorrowProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description： 属性文件读取配置信息</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/2/1 16:45
 */
@Configuration
// 读取配置类
@EnableConfigurationProperties(BorrowProperties.class)
public class PropertiesConfig {
}
