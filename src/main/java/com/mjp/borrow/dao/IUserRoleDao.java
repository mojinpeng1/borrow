package com.mjp.borrow.dao;

import com.mjp.borrow.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>Description：</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/19 11:04
 */
public interface IUserRoleDao extends JpaRepository<UserRole,Long> {
}