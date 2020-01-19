package com.mjp.borrow.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description：角色</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/17 11:44
 */
@Entity
@Table(name = "role")
@Data
public class Role implements Serializable {
    @Id
    @Column(name = "r_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long rid;

    @Column(name = "role_name",nullable = false, length = 20)
    private String roleName;

    @Column(name = "create_time",nullable = false)
    private Date createTime;
}
