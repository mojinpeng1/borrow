package com.mjp.borrow.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>Description：登录对象</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/18 16:15
 */
@Data
public class LoginUser {
    @ApiModelProperty(required = true)
    private String loginName;

    @ApiModelProperty(required = true)
    private String password;
}
