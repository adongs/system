package com.sys.system.model.user.service.impl;

import com.sys.system.model.user.mapper.RoleMapper;
import com.sys.system.model.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author yudong
 * @Date 2018年05月02日 下午5:34
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户Id查询用户角色
     * @param userId
     * @return
     */
    @Override
    public List<String> findByUserId(int userId)
    {
     return roleMapper.findByUserId(userId);
    }
}
