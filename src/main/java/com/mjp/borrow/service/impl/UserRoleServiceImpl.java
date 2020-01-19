package com.mjp.borrow.service.impl;

import com.mjp.borrow.dao.IUserRoleDao;
import com.mjp.borrow.model.UserRole;
import com.mjp.borrow.service.IUserInfoService;
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
        // TODO 配置用户
    }
}
