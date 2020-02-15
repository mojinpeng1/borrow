package com.mjp.borrow.service;

import com.mjp.borrow.model.BorrowRecord;
import com.mjp.borrow.model.GoodsInfo;
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

    /**
     * <p>方法名:getByUserAndGoods</p>
     * <p>描述: 根据用户和物品查询是否存在已经添加的记录</p>
     * <p>创建时间: 2020/2/7 13:29</p>
     *
     * @param userInfo  用户
     * @param goodsInfo 物品
     * @return BorrowRecord
     * @author mojinpeng
     */
    BorrowRecord getByUserAndGoods(UserInfo userInfo, GoodsInfo goodsInfo);


    /**
     * <p>方法名:queryBorrowRecord</p>
     * <p>描述: 查询存在的借用记录</p>
     * <p>创建时间: 2020/2/7 18:42</p>
     *
     * @author mojinpeng
     */
    List<BorrowRecord> queryBorrowRecord();
}