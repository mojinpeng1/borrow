package com.mjp.borrow.service.impl;

import com.mjp.borrow.dao.IUserRoleDao;
import com.mjp.borrow.model.UserInfo;
import com.mjp.borrow.model.UserRole;
import com.mjp.borrow.service.IUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Description：</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/19 11:03
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements IUserRoleService {
    @Resource
    private IUserRoleDao userRoleDao;

    @Override
    public void configUser(List<UserRole> userRoles) {
        // TODO 配置用户, 目前的逻辑是直接保存,后期有时间可以更新保存
        if (userRoles != null && userRoles.size() > 0) {
            userRoles.forEach(userRole -> {
                userRoleDao.save(userRole);
            });
        }
    }

    @Override
    public boolean isAdmin(UserInfo userInfo) {
        UserRole byUserInfo = userRoleDao.findByUserInfo(userInfo);
        if (byUserInfo != null) {
            return true;
        }
        return false;
    }
}
