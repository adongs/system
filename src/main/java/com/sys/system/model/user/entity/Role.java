package com.sys.system.model.user.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 角色
 *
 * @Author yudong
 * @Date 2018年05月14日 上午7:11
 */
@Data
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;


}
