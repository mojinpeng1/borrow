package com.mjp.borrow.service;

import com.mjp.borrow.model.GoodsInfo;

/**
 * <p>Description：</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/18 18:01
 */
public interface IGoodsInfoService {

    GoodsInfo addGoods(GoodsInfo goodsInfo);

    boolean checkGoods(String goodsCode);

    void deleteGoods(long goodsId);

    void updateGoods(GoodsInfo goodsInfo);
}