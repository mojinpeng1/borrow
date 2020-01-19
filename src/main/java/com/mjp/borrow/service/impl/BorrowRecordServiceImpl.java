package com.mjp.borrow.service.impl;

import cn.hutool.core.date.DateTime;
import com.mjp.borrow.base.exception.CommonException;
import com.mjp.borrow.dao.IBorrowRecordDao;
import com.mjp.borrow.dao.IGoodsInfoDao;
import com.mjp.borrow.model.BorrowRecord;
import com.mjp.borrow.model.GoodsInfo;
import com.mjp.borrow.model.UserInfo;
import com.mjp.borrow.service.IBorrowRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Description：</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/19 11:36
 */
@Service("borrowRecordService")
@Transactional(rollbackFor = Exception.class)
public class BorrowRecordServiceImpl implements IBorrowRecordService {
    @Resource
    private IBorrowRecordDao borrowRecordDao;

    @Resource
    private IGoodsInfoDao goodsInfoDao;

    @Override
    public BorrowRecord addOne(BorrowRecord borrowRecord) {
        return borrowRecordDao.save(borrowRecord);
    }

    @Override
    public List<BorrowRecord> queryAll() {
        return borrowRecordDao.findAll();
    }

    @Override
    public List<BorrowRecord> queryBorrow(UserInfo user) {

        return borrowRecordDao.queryBorrow(user, user);
    }

    @Override
    public void reback(Long brid, UserInfo user) {
        BorrowRecord borrowRecord = borrowRecordDao.getOne(brid);
        if (borrowRecord != null) {
            borrowRecord.setActuralReturn(DateTime.now());
            borrowRecord.setStatus(BorrowRecord.STATUS_RETURNED);
            borrowRecordDao.save(borrowRecord);
            // 更新物品信息
            GoodsInfo goodsInfo = borrowRecord.getGoodsInfo();
            goodsInfo.setUpdatetime(DateTime.now());
            goodsInfo.setLocationMan(goodsInfo.getUpdateMan());
            goodsInfoDao.save(goodsInfo);
            return;
        }
        throw new CommonException("未找到记录!");
    }

    @Override
    public void confirmOut(Long brid, UserInfo user) {
        BorrowRecord borrowRecord = borrowRecordDao.getOne(brid);
        if (borrowRecord != null) {
            if (BorrowRecord.STATUS_CONFIRMED.equals(borrowRecord.getStatus())) {
                borrowRecord.setStatus(BorrowRecord.STATUS_BORROWED);
            } else {
                throw new CommonException("该记录状态未在已确认状态下,请确认信息!");
            }
            // TODO 这个地方可以考虑管理员可以直接通过
            GoodsInfo goodsInfo = borrowRecord.getGoodsInfo();
            if (!user.equals(goodsInfo.getUpdateMan())) {
                throw new CommonException("当前登录人员非物品维护人员,无权限借出!");
            }
            borrowRecord.setOutTime(DateTime.now());
            borrowRecordDao.save(borrowRecord);
            goodsInfo.setLocationMan(borrowRecord.getBorrowMan());
            goodsInfo.setUpdatetime(DateTime.now());
            goodsInfoDao.save(goodsInfo);
            return;
        }
        throw new CommonException("未找到记录!");
    }

    @Override
    public void confirm(Long brid, UserInfo user) {
        BorrowRecord borrowRecord = borrowRecordDao.getOne(brid);
        if (borrowRecord != null) {
            if (BorrowRecord.STATUS_CREATED.equals(borrowRecord.getStatus())) {
                borrowRecord.setStatus(BorrowRecord.STATUS_CONFIRMED);
            } else {
                throw new CommonException("该记录状态可能已确认,请确认信息!");
            }
            if (!user.equals(borrowRecord.getBorrowMan())) {
                throw new CommonException("当前登录人员非创建人,不允许直接确认!");
            }
            borrowRecordDao.save(borrowRecord);
            return;
        }
        throw new CommonException("未找到记录!");
    }

    @Override
    public void closeBorrow(Long brid, UserInfo user) {
        BorrowRecord borrowRecord = borrowRecordDao.getOne(brid);
        if (borrowRecord != null) {
            if (BorrowRecord.STATUS_CREATED.equals(borrowRecord.getStatus()) ||
                    BorrowRecord.STATUS_CONFIRMED.equals(borrowRecord.getStatus())) {
                borrowRecord.setStatus(BorrowRecord.STATUS_CLOSED);
            } else {
                throw new CommonException("该记录状态可能已确认,请确认信息!");
            }
            if (!user.equals(borrowRecord.getBorrowMan())) {
                throw new CommonException("当前登录人员非创建人,不允许直接关闭!");
            }
            borrowRecordDao.save(borrowRecord);
            return;
        }
        throw new CommonException("未找到记录!");
    }
}
