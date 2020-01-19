package com.mjp.borrow.model.dto;

import cn.hutool.core.date.DateTime;
import com.mjp.borrow.model.AccoutInfo;
import com.mjp.borrow.model.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description：新建用户的对象</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/18 11:58
 */
@Data
@ApiModel(value = "新增用户对象")
public class UserDto {

    private String account;
    @ApiModelProperty(required = true, value = "密码")
    private String password;

    private String nick;
    @ApiModelProperty(required = true)
    private String userName;
    @ApiModelProperty(required = true)

    private String userCode;
    @ApiModelProperty(required = true)

    private Short gender;
    @ApiModelProperty(required = true)

    private String phone;
    @ApiModelProperty(required = true)

    private String email;

    private String phoneBack;
    @ApiModelProperty(required = true)

    private String addr;

    private String portrait;

    public UserInfo buildUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAddr(this.addr);
        userInfo.setUserName(userName);
        userInfo.setUserCode(this.userCode);
        userInfo.setGender(this.gender);
        userInfo.setPhone(this.phone);
        userInfo.setEmail(this.email);
        userInfo.setPhoneBack(this.phoneBack);
        userInfo.setCreateTime(DateTime.now());
        userInfo.setUpdateTime(DateTime.now());
        if (StringUtils.isNotBlank(this.nick)) {
            userInfo.setNick(this.nick);
        } else {
            userInfo.setNick(this.userName);
        }
        return userInfo;
    }

    public AccoutInfo buildAccount() {
        AccoutInfo accoutInfo = new AccoutInfo();
        accoutInfo.setUser(buildUser());
        accoutInfo.setAccount(this.userCode);
        accoutInfo.setPassword(this.password);

        accoutInfo.setStatus(AccoutInfo.STATUS_INWORK);
        return accoutInfo;
    }
}
