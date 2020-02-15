package com.mjp.borrow.dao;

import com.mjp.borrow.model.BorrowRecord;
import com.mjp.borrow.model.GoodsInfo;
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

public interface IBorrowRecordDao extends JpaRepository<BorrowRecord, Long> {

    @Query("from BorrowRecord a where (a.goodsInfo.updateMan = ?1 or a.borrowMan = ?2) and a.status <> 5 and a.status <> 10 ")
    List<BorrowRecord> queryBorrow(UserInfo updateMan, UserInfo borrowMan);

    @Query("from BorrowRecord  a where  a.borrowMan = ?1 and a.goodsInfo = ?2 and a.status = 0")
    BorrowRecord getByUserAndGoods(UserInfo userInfo, GoodsInfo goodsInfo);

    @Query("from BorrowRecord  a where  a.status = 5")
    List<BorrowRecord> queryBorrowRecord();
}