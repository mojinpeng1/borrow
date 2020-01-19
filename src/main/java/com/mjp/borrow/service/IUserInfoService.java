package com.mjp.borrow.service;

import com.mjp.borrow.model.AccoutInfo;
import com.mjp.borrow.model.UserInfo;
import com.mjp.borrow.model.dto.LoginUser;
import com.mjp.borrow.model.dto.UserDto;

/**
 * <p>Description：userinfo接口</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/18 14:51
 */

public interface IUserInfoService {
    UserInfo addUser(UserDto userDto);

    UserInfo checkUser(String userCode);

    AccoutInfo login(LoginUser loginUser);

    void updateUser(UserInfo userInfo);
}