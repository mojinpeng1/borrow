package com.mjp.borrow.service;

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
}