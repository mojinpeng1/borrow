package com.mjp.borrow.service;

import com.mjp.borrow.model.Role;

/**
 * <p>Description：</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/19 10:36
 */
public interface IRoleService {
    Role addRole(Role role);

    void deleteRole(Long roleId);
}