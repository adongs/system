package com.sys.system.model.user.service;


import java.util.List;

/**
 * 用户角色
 * @Author yudong
 * @Date 2018年05月02日 下午5:30
 */
public interface RoleService {

    List<String> findByUserId(int userId);

}
