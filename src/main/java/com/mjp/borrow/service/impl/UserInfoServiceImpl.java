package com.mjp.borrow.service.impl;

import com.mjp.borrow.base.exception.CommonException;
import com.mjp.borrow.dao.IAccountInfoDao;
import com.mjp.borrow.dao.IUserInfoDao;
import com.mjp.borrow.model.AccoutInfo;
import com.mjp.borrow.model.UserInfo;
import com.mjp.borrow.model.dto.LoginUser;
import com.mjp.borrow.model.dto.UserDto;
import com.mjp.borrow.service.IUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>Description：用户实现</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/18 14:53
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService {
    @Resource
    private IUserInfoDao userInfoDao;
    @Resource
    private IAccountInfoDao accountInfoDao;

    @Override
    public UserInfo addUser(UserDto userDto) {
        UserInfo userInfo = userInfoDao.save(userDto.buildUser());
        AccoutInfo accoutInfo = userDto.buildAccount();
        accoutInfo.setUser(userInfo);
        accountInfoDao.save(accoutInfo);
        return userInfo;

    }

    @Override
    public UserInfo checkUser(String userCode) {
        UserInfo userInfo = userInfoDao.findByUserCode(userCode);
        return userInfo;
    }

    @Override
    public AccoutInfo login(LoginUser loginUser) {
        AccoutInfo byAccount = accountInfoDao.findByAccount(loginUser.getLoginName());
        if (byAccount.getPassword().equals(loginUser.getPassword())) {
            return byAccount;
        } else {
            throw new CommonException("登录失败！");
        }
    }

    @Override
    public void updateUser(UserInfo userInfo) {
        userInfoDao.save(userInfo);
    }
}
