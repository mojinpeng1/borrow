package com.mjp.borrow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description：用户信息</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/17 11:35
 */
@Table(name = "user_info")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long userId;

    @Column(name = "user_name", nullable = false,length = 20)
    private String userName;

    @Column(name = "user_code",nullable = false,length = 20)
    private  String userCode;
    @Column(name = "nick",nullable = false,length = 20)
    private String nick;
    @Column(name = "gender",nullable = false)
    private Short gender;

    @Column(name = "phone",nullable = false,length = 15)
    private  String phone;

    @Column(name = "email",nullable = false,length = 20)
    private  String email;

    @Column(name = "phone_back",length = 15)
    private String phoneBack;

    @Column(name = "addr",length = 50, nullable = false)
    private String addr;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "update_time")
    private  Date updateTime;

    @Column(name = "portrait", length = 100)
    private  String portrait;
}
