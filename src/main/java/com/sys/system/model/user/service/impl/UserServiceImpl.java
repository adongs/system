package com.sys.system.model.user.service.impl;

import com.sys.system.model.user.entity.User;
import com.sys.system.model.user.mapper.UserMapper;
import com.sys.system.model.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author yudong
 * @Date 2018年05月02日 下午5:33
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名称查询用户信息
     * @param name
     * @return
     */
    @Override
    public User findByUserName(String name) {
        return userMapper.selectOneByName(name);
    }
}
