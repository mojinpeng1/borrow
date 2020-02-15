package com.mjp.borrow.service.impl;

import com.mjp.borrow.dao.IGoodsInfoDao;
import com.mjp.borrow.model.GoodsInfo;
import com.mjp.borrow.service.IGoodsInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Description：</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/18 18:02
 */
@Service("goodsInfoService")
public class GoodsInfoServiceImpl implements IGoodsInfoService {
    @Resource
    private IGoodsInfoDao goodsInfoDao;

    @Override
    public GoodsInfo addGoods(GoodsInfo goodsInfo) {
        goodsInfo.setStatus(GoodsInfo.GOODS_STATUS_NORMAL);
        GoodsInfo save = goodsInfoDao.save(goodsInfo);
        return save;
    }

    @Override
    public boolean checkGoods(String goodsCode) {
        GoodsInfo byCode = goodsInfoDao.findByCode(goodsCode);
        if (byCode == null) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteGoods(long goodsId) {
        goodsInfoDao.deleteById(goodsId);
    }

    @Override
    public void updateGoods(GoodsInfo goodsInfo) {
        goodsInfoDao.save(goodsInfo);
    }

    @Override
    public List<GoodsInfo> getList() {
        List<GoodsInfo> all = goodsInfoDao.findAll();
        return all;
    }
}
