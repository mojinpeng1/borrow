package com.mjp.borrow.service;

import com.mjp.borrow.model.BorrowRecord;
import com.mjp.borrow.model.UserInfo;

import java.util.List;

/**
 * <p>Description：</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/19 11:35
 */
public interface IBorrowRecordService {
    BorrowRecord addOne(BorrowRecord borrowRecord);

    List<BorrowRecord> queryAll();

    List<BorrowRecord> queryBorrow(UserInfo user);

    void reback(Long brid, UserInfo user);

    void confirmOut(Long brid, UserInfo user);

    void confirm(Long brid, UserInfo user);

    void closeBorrow(Long brid, UserInfo user);
}