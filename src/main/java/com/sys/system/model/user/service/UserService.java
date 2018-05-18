package com.sys.system.model.user.service;

import com.sys.system.model.user.entity.User;

/**
 * 用户
 * @Author yudong
 * @Date 2018年05月02日 下午5:29
 */
public interface UserService {


     User findByUserName(String name);
}
