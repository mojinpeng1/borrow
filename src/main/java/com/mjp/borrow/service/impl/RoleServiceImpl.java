package com.mjp.borrow.service.impl;

import com.mjp.borrow.dao.IRoleDao;
import com.mjp.borrow.model.Role;
import com.mjp.borrow.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>Description：角色service实现 </p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/19 10:37
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    @Resource
    private IRoleDao roleDao;
    @Override
    public Role addRole(Role role) {
        return  roleDao.save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleDao.deleteById(roleId);
    }
}
