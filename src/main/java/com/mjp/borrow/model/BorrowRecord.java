package com.mjp.borrow.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description：借用记录</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/17 13:05
 */
@Entity
@Table(name = "borrow_record")
@Data
public class BorrowRecord implements Serializable {
    @Id
    @Column(name = "br_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long brId;

    @OneToOne
    @JoinColumn(name = "goods_id",nullable = false)
    private GoodsInfo goodsInfo;

    @OneToOne
    @JoinColumn(name = "borrow_man",nullable = false)
    private UserInfo borrowMan;

    @Column(name = "borrow_time", nullable = false)
    private Date borrowTime;

    @Column(name = "borrow_reason", length = 100)
    private  String borrowReason;

    @Column(name = "expect_return", nullable = false)
    private Date expectReturn;

    @Column(name = "actual_return")
    private Date acturalReturn;

    @Column(name = "status",nullable = false)
    private Short status = 0;

    @Column(name = "out_time")
    private Date outTime;

    /**
      *<p>已创建</p>
      */
    public  static final Short STATUS_CREATED = 0;
    /**
     *<p>已确认</p>
     */
    public  static final Short STATUS_CONFIRMED = 1;
    /**
     *<p>已借出</p>
     */
    public  static final Short STATUS_BORROWED = 2;
    /**
     *<p>已归还</p>
     */
    public  static final Short STATUS_RETURNED = 5;
    /**
     *<p>已关闭</p>
     */
    public  static final Short STATUS_CLOSED = 10;


}
