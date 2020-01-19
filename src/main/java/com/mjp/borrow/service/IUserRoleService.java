package com.mjp.borrow.service;

import com.mjp.borrow.model.UserInfo;
import com.mjp.borrow.model.UserRole;

import java.util.List;

/**
 * <p>Description：</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/19 11:02
 */
public interface IUserRoleService {
    void configUser(List<UserRole> userRoles);

    /**
     * <p>方法名:isAdmin</p>
     * <p>描述: 查询用户是否管理员</p>
     * <p>创建时间: 2020/1/19 11:44</p>
     * @param userInfo 用户
     * @return Boolean
     * @author mojinpeng
     */
    boolean isAdmin(UserInfo userInfo);
}