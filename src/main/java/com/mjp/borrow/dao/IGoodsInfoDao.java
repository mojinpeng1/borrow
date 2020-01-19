package com.mjp.borrow.dao;

import com.mjp.borrow.model.GoodsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>Description：</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/18 18:03
 */
public interface IGoodsInfoDao extends JpaRepository<GoodsInfo,Long> {
    GoodsInfo findByCode(String goodsCode);
}