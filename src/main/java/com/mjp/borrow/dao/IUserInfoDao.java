package com.mjp.borrow.dao;

import com.mjp.borrow.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>Description：</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/18 14:56
 */
public interface IUserInfoDao extends JpaRepository<UserInfo,Long> {

    UserInfo findByUserCode(String userCode);
}