package com.mjp.borrow.dao;

import com.mjp.borrow.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>Description：</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/19 10:38
 */
public interface IRoleDao extends JpaRepository<Role,Long> {
}