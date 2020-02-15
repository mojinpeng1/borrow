package com.mjp.borrow.dao;

import com.mjp.borrow.model.AccoutInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>Description：</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/18 14:57
 */
public interface IAccountInfoDao extends JpaRepository<AccoutInfo, Long> {
    AccoutInfo findByAccount(String loginName);
}