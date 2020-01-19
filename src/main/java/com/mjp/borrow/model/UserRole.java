package com.mjp.borrow.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description：用户角色</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/17 11:51
 */
@Data
@Table(name = "user_role")
@Entity
public class UserRole implements Serializable {
    @Id
    @Column(name = "ur_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long urId;
    @JoinColumn(name = "user_id",nullable = false)
    private UserInfo userInfo;

    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

    @JoinColumn(name = "config_man",nullable = false)
    private UserInfo configMan;

    @Column(name = "config_time",nullable = false)
    private Date configTime;
}
