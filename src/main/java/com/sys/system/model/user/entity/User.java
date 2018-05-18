package com.sys.system.model.user.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户实体
 * @Author yudong
 * @Date 2018年05月02日 下午5:31
 */
@Data
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "用户名不能为空")
    @Column(name = "name")
    private String name;

    @NotNull(message = "密码不能为空")
    @Column(name = "password")
    private String password;
}
