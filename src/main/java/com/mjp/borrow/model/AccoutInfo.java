package com.mjp.borrow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description：账号信息</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/17 11:47
 */
@Entity
@Table(name = "accout_info")
@Data
public class AccoutInfo implements Serializable {
    @Id
    @Column(name = "acc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long accId;

    @OneToOne
    @JoinColumn(name = "user", nullable = false)
    private  UserInfo user;

    @Column(name = "account",nullable = false,length = 20)
    private  String account;

    @JsonIgnore
    @Column(name = "password",nullable = false,length = 20)
    private  String password;



    @Column(name = "status",nullable = false)
    private  Short status;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
      *<p>在职</p>
      */
    public static final  Short STATUS_INWORK = 0;

    /**
      *<p>离职</p>
      */
    public  static final  Short STATUS_OUTWORK = 1;
}
