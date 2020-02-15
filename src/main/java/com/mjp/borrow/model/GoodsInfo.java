package com.mjp.borrow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description：物品信息</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/17 11:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "goods_info")
public class GoodsInfo implements Serializable {
    @Id
    @Column(name = "g_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "status", nullable = false)
    private Short status;

    @Column(name = "createtime", nullable = false)
    private Date createTime;

    @Column(name = "updatetime")
    private Date updatetime;

    @OneToOne
    @JoinColumn(name = "create_man")
    private UserInfo createMan;
    @OneToOne
    @JoinColumn(name = "update_man")
    private UserInfo updateMan;
    @OneToOne
    @JoinColumn(name = "location_man")
    private UserInfo locationMan;
    /**
     * <p>正常</p>
     */
    public static Short GOODS_STATUS_NORMAL = 0;
    /**
     * <p>借用中</p>
     */
    public static Short GOODS_STATUS_INBORROW = 1;
    /**
     * <p>已借出</p>
     */
    public static Short GOODS_STATUS_BORROW_OVER = 2;


}
