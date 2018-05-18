package com.sys.system.security;

import com.sys.system.model.user.entity.User;
import com.sys.system.model.user.service.impl.RoleServiceImpl;
import com.sys.system.model.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 从数据库中获取数据
 * @Author yudong
 * @Date 2018年05月14日 上午8:10
 */
@Component
public class SystemUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userService.findByUserName(s);
        if(user == null){
            throw new UsernameNotFoundException("用户名："+ s + "不存在！");
        }
        Collection<SimpleGrantedAuthority> collection = new HashSet<>();
        Iterator<String> iterator =  roleService.findByUserId(user.getId()).iterator();
        while (iterator.hasNext()){
            collection.add(new SimpleGrantedAuthority(iterator.next()));
        }
        return new org.springframework.security.core.userdetails.User(s,user.getPassword(),collection);

    }
}
