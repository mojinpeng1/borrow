package com.mjp.borrow.dao;

import com.mjp.borrow.model.BorrowRecord;
import com.mjp.borrow.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>Description：</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/19 11:37
 */

public interface IBorrowRecordDao extends JpaRepository<BorrowRecord,Long> {

    @Query("from BorrowRecord a where a.goodsInfo.updateMan = ?1 or a.borrowMan = ?2")
    List<BorrowRecord> queryBorrow(UserInfo updateMan, UserInfo borrowMan);
}